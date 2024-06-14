package art.deerborg.bank.customer.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @NotNull
    @Email
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String password;
}
