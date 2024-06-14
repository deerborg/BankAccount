package art.deerborg.bank.bank.model.entity;

import art.deerborg.bank.customer.model.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String fullName;
    private String bankCode;
    @Column(unique = true)
    private String iban;
    private Double balance;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
