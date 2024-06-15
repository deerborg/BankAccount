package art.deerborg.bank.bank.model.mapper;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity fromAccountAddBalanceRequest(AccountChangeBalanceRequest accountChangeBalanceRequest);

    AccountUpdateBalanceResponse toAccountUpdateBalanceResponse(AccountEntity account);
    AccountResponse toAccountResponse(AccountEntity account);
    AccountDetailResponse toDetailResponse(AccountEntity account);
}
