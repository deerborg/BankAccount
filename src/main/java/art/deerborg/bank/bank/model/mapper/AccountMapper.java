package art.deerborg.bank.bank.model.mapper;

import art.deerborg.bank.bank.model.dto.request.AccountChangeBalanceRequest;
import art.deerborg.bank.bank.model.dto.request.AccountTransferMoneyRequest;
import art.deerborg.bank.bank.model.dto.response.AccountBalanceAndIbanResponse;
import art.deerborg.bank.bank.model.dto.response.AccountDetailResponse;
import art.deerborg.bank.bank.model.dto.response.AccountUpdateBalanceResponse;
import art.deerborg.bank.bank.model.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity fromAccountAddBalanceRequest(AccountChangeBalanceRequest accountChangeBalanceRequest);
    AccountEntity fromAccountTransferMoneyRequest(AccountTransferMoneyRequest accountTransferMoneyRequest);

    AccountUpdateBalanceResponse toAccountUpdateBalanceResponse(AccountEntity account);
    AccountDetailResponse toDetailResponse(AccountEntity account);
    @Mapping(source = "id",target = "id")
    AccountBalanceAndIbanResponse toAccountBalanceAndIbanResponse(AccountEntity account);
}
