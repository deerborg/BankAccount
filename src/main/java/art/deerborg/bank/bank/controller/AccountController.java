package art.deerborg.bank.bank.controller;

import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public AccountEntity addAccount(@RequestBody AccountEntity account) {
        return accountService.addAccount(account);
    }
    @GetMapping
    public List<AccountEntity> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @PutMapping("/add-balance")
    public AccountEntity addBalance(@RequestBody AccountEntity account) {
        return accountService.addBalance(account);
    }
    @PutMapping("/withdraw")
    public AccountEntity withdrawBalance(@RequestBody AccountEntity account){
        return accountService.withdrawBalance(account);
    }
    @PutMapping("/transfer")
    public AccountEntity sendMoney(@RequestBody AccountEntity account){
        return accountService.sendMoney(account);
    }
}
