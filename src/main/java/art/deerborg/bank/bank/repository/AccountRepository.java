package art.deerborg.bank.bank.repository;

import art.deerborg.bank.bank.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,String> {
    AccountEntity findByIban(String iban);
}
