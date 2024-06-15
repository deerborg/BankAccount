package art.deerborg.bank.bank.controller;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountResponse;
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
    public ResponseEntity<ApiResponse<AccountResponse>> addAccount(@RequestBody AccountEntity account) {
        return accountService.addAccount(account);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDetailResponse>>> getAllAccounts() {
        return accountService.getAllAccounts();
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
    public AccountEntity sendMoney(@RequestBody AccountEntity account){
        return accountService.sendMoney(account);
    }
}
