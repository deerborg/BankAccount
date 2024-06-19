package art.deerborg.bank.bank.model.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetailResponse {
    private String id;
    private String bankCode;
    private String iban;
    private Double balance;
}
