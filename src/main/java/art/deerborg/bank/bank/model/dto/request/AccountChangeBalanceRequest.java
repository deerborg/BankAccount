package art.deerborg.bank.bank.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountChangeBalanceRequest {
    private String id;
    private Double balance;
}
