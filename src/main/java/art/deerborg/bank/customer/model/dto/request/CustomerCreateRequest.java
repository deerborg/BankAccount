package art.deerborg.bank.customer.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String phone;
    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}
