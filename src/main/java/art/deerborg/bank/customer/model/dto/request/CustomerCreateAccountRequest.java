package art.deerborg.bank.customer.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateAccountRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    private String id;
}
