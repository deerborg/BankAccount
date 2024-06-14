package art.deerborg.bank.bank.service.impl;

import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.repository.AccountRepository;
import art.deerborg.bank.bank.service.AccountService;
import art.deerborg.bank.bank.util.FinanceHelper;
import art.deerborg.bank.common.util.exceptions.NotFoundIdException;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AmqpTemplate amqpTemplate;
    private final DirectExchange exchange;

    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    @Value("${sample.rabbitmq.queue}")
    String queueName;

    @Override
    public AccountEntity addAccount(AccountEntity account) {
        account.setBankCode("1300011");
        account.setBalance(0.0);
        account.setIban(FinanceHelper.generateIban(account.getBankCode()));
        account.setFullName(customerRepository.findById(account.getCustomer().getId()).orElseThrow().getFullName());
        return accountRepository.save(account);
    }

    @Override
    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity addBalance(AccountEntity account) {
        AccountEntity accountEntity = accountRepository.findById(account.getId()).orElseThrow(NotFoundIdException::new);
        accountEntity.setBalance(accountEntity.getBalance() + account.getBalance());
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity withdrawBalance(AccountEntity account) {
        AccountEntity accountEntity = accountRepository.findById(account.getId()).orElseThrow(NotFoundIdException::new);
        if(accountEntity.getBalance() <= account.getBalance() || accountEntity.getBalance() == 0) {
            throw new RuntimeException("Bakiye yetersiz");
        }
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity sendMoney(AccountEntity account) {
        AccountEntity senderAccount = accountRepository.findById(account.getId()).orElseThrow(NotFoundIdException::new);
        AccountEntity sendingAccount = accountRepository.findByIban(account.getIban());
        if(senderAccount.getBalance() <= 0){
            throw new RuntimeException("Bakiye yetersiz");
        }
        if(senderAccount.getBalance() < account.getBalance()){
            throw new RuntimeException("Bakiye yetersiz");
        }
        sendingAccount.setBalance(account.getBalance());
        senderAccount.setBalance(senderAccount.getBalance() - account.getBalance());

        accountRepository.save(sendingAccount);
        return accountRepository.save(senderAccount);
    }
}
