package art.deerborg.bank.bank.service.impl;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.request.AccountTransferMoneyRequest;
import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.model.mapper.AccountMapper;
import art.deerborg.bank.bank.model.util.excepitons.InsufficientFundsException;
import art.deerborg.bank.bank.model.util.excepitons.InvalidBalanceException;
import art.deerborg.bank.bank.repository.AccountRepository;
import art.deerborg.bank.bank.service.AccountService;
import art.deerborg.bank.bank.util.FinanceHelper;
import art.deerborg.bank.bank.util.exception.IbanConflictException;
import art.deerborg.bank.bank.util.exception.NotFoundIban;
import art.deerborg.bank.common.util.exceptions.NotFoundIdException;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Override
    public ResponseEntity<ApiResponse<AccountBalanceAndIbanResponse>> addAccount(AccountEntity account) {

        if(accountRepository.existsByCustomer(account.getCustomer())){
            AccountEntity response = accountRepository.findByCustomer_Id(account.getCustomer().getId());
            return new ResponseEntity<>(ApiResponseHelper.OK(accountMapper.toAccountBalanceAndIbanResponse(response)),HttpStatus.OK);
        }

        account.setBankCode("1300011");
        account.setBalance(0.0);

        account.setIban(FinanceHelper.generateIban(account.getBankCode()));

        return new ResponseEntity<>(ApiResponseHelper.CREATE(accountMapper
                .toAccountBalanceAndIbanResponse(accountRepository.save(account))), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> addBalance(AccountChangeBalanceRequest request) {
        AccountEntity account = accountRepository.findById(request.getId()).orElseThrow(NotFoundIdException::new);

        if(request.getBalance() <= 0){
            throw new InvalidBalanceException();
        }
        if(account.getBalance() >= 1000000.0){
            account.setBalance(1000000.0);

            AccountUpdateBalanceResponse updateBalanceResponse = accountMapper
                    .toAccountUpdateBalanceResponse(accountRepository.save(account));

            return new ResponseEntity<>(ApiResponseHelper.UPDATED(updateBalanceResponse),HttpStatus.OK);
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

    @Override
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> sendMoney(AccountTransferMoneyRequest request) {

        AccountEntity senderAccount = accountRepository.findById(request.getId()).orElseThrow(NotFoundIdException::new);
        AccountEntity sendingAccount = accountRepository.findByIban(request.getIban()).orElseThrow(NotFoundIban::new);

        if(Objects.equals(sendingAccount.getIban(), senderAccount.getIban())){
            throw new IbanConflictException();
        }

        if(senderAccount.getBalance() <= 0){
            throw new InsufficientFundsException();
        }
        if(senderAccount.getBalance() < request.getBalance()){
            throw new InsufficientFundsException();
        }

        sendingAccount.setBalance(request.getBalance() + sendingAccount.getBalance());
        senderAccount.setBalance(senderAccount.getBalance() - request.getBalance());

        accountRepository.save(sendingAccount);
        return new ResponseEntity<>(ApiResponseHelper.UPDATED(accountMapper.toAccountUpdateBalanceResponse(senderAccount)),HttpStatus.OK);
    }
}
