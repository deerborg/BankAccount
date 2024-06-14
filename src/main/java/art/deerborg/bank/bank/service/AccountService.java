package art.deerborg.bank.bank.service;

import art.deerborg.bank.bank.model.entity.AccountEntity;

import java.util.List;

public interface AccountService {
    AccountEntity addAccount(AccountEntity account);
    AccountEntity addBalance(AccountEntity account);
    AccountEntity withdrawBalance(AccountEntity account);
    AccountEntity sendMoney(AccountEntity account);
    List<AccountEntity> getAllAccounts();
}
