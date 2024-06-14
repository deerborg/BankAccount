package art.deerborg.bank.customer.model.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class CustomerDetailResponse {
    private String id;
    private String fullName;
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    private String email;
    private String phone;
    private String password;
}
