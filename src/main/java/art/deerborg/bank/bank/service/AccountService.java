package art.deerborg.bank.bank.service;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.common.util.result.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<ApiResponse<AccountResponse>> addAccount(AccountEntity account);
    ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> addBalance(AccountChangeBalanceRequest request);
    ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> withdrawBalance(AccountChangeBalanceRequest request);
    AccountEntity sendMoney(AccountEntity account);
    ResponseEntity<ApiResponse<List<AccountDetailResponse>>> getAllAccounts();
}
