package art.deerborg.bank.bank.service.impl;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.model.mapper.AccountMapper;
import art.deerborg.bank.bank.model.util.excepitons.InsufficientFundsException;
import art.deerborg.bank.bank.model.util.excepitons.InvalidBalanceException;
import art.deerborg.bank.bank.repository.AccountRepository;
import art.deerborg.bank.bank.service.AccountService;
import art.deerborg.bank.bank.util.FinanceHelper;
import art.deerborg.bank.common.util.exceptions.NotFoundIdException;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import art.deerborg.bank.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;


    @Override
    public ResponseEntity<ApiResponse<AccountResponse>> addAccount(AccountEntity account) {

        account.setBankCode("1300011");
        account.setBalance(0.0);
        account.setIban(FinanceHelper.generateIban(account.getBankCode()));
        account.setFullName(customerRepository.findById(account.getCustomer().getId()).orElseThrow().getFullName());

        return new ResponseEntity<>(ApiResponseHelper.CREATE(accountMapper
                .toAccountResponse(accountRepository.save(account))), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<List<AccountDetailResponse>>> getAllAccounts() {
        return new ResponseEntity<>(ApiResponseHelper.OK(accountRepository.findAll().stream()
                .map(accountMapper::toDetailResponse).toList()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> addBalance(AccountChangeBalanceRequest request) {
        AccountEntity account = accountRepository.findById(request.getId()).orElseThrow(NotFoundIdException::new);

        if(request.getBalance() <= 0){
            throw new InvalidBalanceException();
        }

        account.setBalance(account.getBalance() + request.getBalance());

        AccountUpdateBalanceResponse updateBalanceResponse = accountMapper
                .toAccountUpdateBalanceResponse(accountRepository.save(account));

        return new ResponseEntity<>(ApiResponseHelper.UPDATED(updateBalanceResponse),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> withdrawBalance(AccountChangeBalanceRequest request) {
        AccountEntity account = accountRepository.findById(request.getId()).orElseThrow(NotFoundIdException::new);
        if(account.getBalance() <= 0 || account.getBalance() < request.getBalance()){
            throw new InsufficientFundsException();
        }
        account.setBalance(account.getBalance() - request.getBalance());
        AccountUpdateBalanceResponse response = accountMapper.toAccountUpdateBalanceResponse(accountRepository.save(account));
        return new ResponseEntity<>(ApiResponseHelper.UPDATED(response),HttpStatus.OK);
    }

    @Override // Will be refactored
    public AccountEntity sendMoney(AccountEntity account) {
        AccountEntity senderAccount = accountRepository.findById(account.getId()).orElseThrow(NotFoundIdException::new);
        AccountEntity sendingAccount = accountRepository.findByIban(account.getIban());

        if(senderAccount.getBalance() <= 0){
            throw new InsufficientFundsException();
        }
        if(senderAccount.getBalance() < account.getBalance()){
            throw new InsufficientFundsException();
        }

        sendingAccount.setBalance(account.getBalance() + sendingAccount.getBalance());
        senderAccount.setBalance(senderAccount.getBalance() - account.getBalance());

        accountRepository.save(sendingAccount);
        return accountRepository.save(senderAccount);
    }
}
