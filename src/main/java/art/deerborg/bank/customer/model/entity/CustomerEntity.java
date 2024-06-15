package art.deerborg.bank.customer.model.entity;

import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.customer.model.entity.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private boolean activeAccount;

    private String password;
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private AccountEntity account;
}
