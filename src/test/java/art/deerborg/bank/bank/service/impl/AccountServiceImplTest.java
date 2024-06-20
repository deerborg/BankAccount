package art.deerborg.bank.bank.service.impl;

import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.model.mapper.AccountMapper;
import art.deerborg.bank.bank.repository.AccountRepository;
import art.deerborg.bank.bank.service.AccountService;
import art.deerborg.bank.bank.util.FinanceHelper;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountMapper = Mockito.mock(AccountMapper.class);
        accountService = new AccountServiceImpl(accountRepository, accountMapper);
    }

    @Test
    void Should_Success_Mapped_Response_And_Request_When_AddAccount() {

        CustomerEntity customer = new CustomerEntity();
        customer.setId(UUID.randomUUID().toString());

        AccountEntity account = new AccountEntity();

        account.setBalance(0.0);
        account.setIban(FinanceHelper.generateIban("1300011"));
        account.setId(UUID.randomUUID().toString());
        account.setCustomer(customer);

        when(accountRepository.existsByCustomer(account.getCustomer())).thenReturn(false);

        AccountBalanceAndIbanResponse response = new AccountBalanceAndIbanResponse();
        when(accountMapper.toAccountBalanceAndIbanResponse(account)).thenReturn(response);

        when(accountRepository.save(account)).thenReturn(account);

        ResponseEntity<ApiResponse<AccountBalanceAndIbanResponse>> result = accountService.addAccount(account);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());


        verify(accountMapper, times(1)).toAccountBalanceAndIbanResponse(account);
        verify(accountRepository, times(1)).save(account);
        verify(accountRepository, times(1)).existsByCustomer(account.getCustomer());
    }

    @Test
    void If_Exist_Customer_When_Return_Only_Response() {

        CustomerEntity customer = new CustomerEntity();
        customer.setId(UUID.randomUUID().toString());
        AccountEntity account = new AccountEntity();
        account.setCustomer(customer);

        when(accountRepository.existsByCustomer(account.getCustomer())).thenReturn(true);
        AccountEntity response = new AccountEntity();
        when(accountRepository.findByCustomer_Id(account.getCustomer().getId())).thenReturn(response);

        AccountBalanceAndIbanResponse accountBalanceAndIbanResponse = new AccountBalanceAndIbanResponse();
        when(accountMapper.toAccountBalanceAndIbanResponse(response)).thenReturn(accountBalanceAndIbanResponse);


        ResponseEntity<ApiResponse<AccountBalanceAndIbanResponse>> result = accountService.addAccount(account);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(accountMapper, times(1)).toAccountBalanceAndIbanResponse(response);
        verify(accountRepository,times(1)).findByCustomer_Id(account.getCustomer().getId());
    }



}