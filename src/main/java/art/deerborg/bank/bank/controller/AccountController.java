package art.deerborg.bank.bank.controller;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.request.AccountTransferMoneyRequest;
import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.service.AccountService;
import art.deerborg.bank.common.util.result.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountBalanceAndIbanResponse>> addAccount(@RequestBody AccountEntity account) {
        return accountService.addAccount(account);
    }
    @PutMapping("/add-balance")
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> addBalance(@RequestBody AccountChangeBalanceRequest request) {
        return accountService.addBalance(request);
    }
    @PutMapping("/withdraw")
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> withdrawBalance(@RequestBody AccountChangeBalanceRequest request){
        return accountService.withdrawBalance(request);
    }
    @PutMapping("/transfer")
    public ResponseEntity<ApiResponse<AccountUpdateBalanceResponse>> sendMoney(@RequestBody AccountTransferMoneyRequest account){
        return accountService.sendMoney(account);
    }
}
