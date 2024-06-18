package art.deerborg.bank.bank.model.mapper;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T08:20:26+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountEntity fromAccountAddBalanceRequest(AccountChangeBalanceRequest accountChangeBalanceRequest) {
        if ( accountChangeBalanceRequest == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountChangeBalanceRequest.getId() );
        accountEntity.setBalance( accountChangeBalanceRequest.getBalance() );

        return accountEntity;
    }

    @Override
    public AccountUpdateBalanceResponse toAccountUpdateBalanceResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountUpdateBalanceResponse accountUpdateBalanceResponse = new AccountUpdateBalanceResponse();

        accountUpdateBalanceResponse.setFullName( account.getFullName() );
        accountUpdateBalanceResponse.setBalance( account.getBalance() );

        return accountUpdateBalanceResponse;
    }

    @Override
    public AccountResponse toAccountResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setFullName( account.getFullName() );

        return accountResponse;
    }

    @Override
    public AccountDetailResponse toDetailResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();

        accountDetailResponse.setId( account.getId() );
        accountDetailResponse.setFullName( account.getFullName() );
        accountDetailResponse.setBankCode( account.getBankCode() );
        accountDetailResponse.setIban( account.getIban() );
        accountDetailResponse.setBalance( account.getBalance() );

        return accountDetailResponse;
    }
}
