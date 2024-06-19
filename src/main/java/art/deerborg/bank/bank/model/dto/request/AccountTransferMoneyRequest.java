package art.deerborg.bank.bank.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransferMoneyRequest {
    private String id;
    private String iban;
    private Double balance;
}
