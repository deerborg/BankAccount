package art.deerborg.bank.bank.service;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.request.AccountTransferMoneyRequest;
import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.common.util.result.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<ApiResponse<AccountBalanceAndIbanResponse>> addAccount(AccountEntity account);

    ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> addBalance(AccountChangeBalanceRequest request);

    ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> withdrawBalance(AccountChangeBalanceRequest request);

    ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> sendMoney(AccountTransferMoneyRequest request);

    /*
    ResponseEntity<ApiResponse<List<AccountDetailResponse>>> getAllAccounts();
     */
}
