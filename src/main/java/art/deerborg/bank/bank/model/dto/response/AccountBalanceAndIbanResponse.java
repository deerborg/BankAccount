package art.deerborg.bank.bank.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBalanceAndIbanResponse {
    private String id;
    private String iban;
    private Double balance;
}
