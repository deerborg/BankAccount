package art.deerborg.bank.bank.model.mapper;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.request.AccountTransferMoneyRequest;
import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T14:28:08+0300",
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
    public AccountEntity fromAccountTransferMoneyRequest(AccountTransferMoneyRequest accountTransferMoneyRequest) {
        if ( accountTransferMoneyRequest == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( accountTransferMoneyRequest.getId() );
        accountEntity.setIban( accountTransferMoneyRequest.getIban() );
        accountEntity.setBalance( accountTransferMoneyRequest.getBalance() );

        return accountEntity;
    }

    @Override
    public AccountUpdateBalanceResponse toAccountUpdateBalanceResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountUpdateBalanceResponse accountUpdateBalanceResponse = new AccountUpdateBalanceResponse();

        accountUpdateBalanceResponse.setBalance( account.getBalance() );

        return accountUpdateBalanceResponse;
    }

    @Override
    public AccountDetailResponse toDetailResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountDetailResponse accountDetailResponse = new AccountDetailResponse();

        accountDetailResponse.setId( account.getId() );
        accountDetailResponse.setBankCode( account.getBankCode() );
        accountDetailResponse.setIban( account.getIban() );
        accountDetailResponse.setBalance( account.getBalance() );

        return accountDetailResponse;
    }

    @Override
    public AccountBalanceAndIbanResponse toAccountBalanceAndIbanResponse(AccountEntity account) {
        if ( account == null ) {
            return null;
        }

        AccountBalanceAndIbanResponse accountBalanceAndIbanResponse = new AccountBalanceAndIbanResponse();

        accountBalanceAndIbanResponse.setId( account.getId() );
        accountBalanceAndIbanResponse.setIban( account.getIban() );
        accountBalanceAndIbanResponse.setBalance( account.getBalance() );

        return accountBalanceAndIbanResponse;
    }
}
