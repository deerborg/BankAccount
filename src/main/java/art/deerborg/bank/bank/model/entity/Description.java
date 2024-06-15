package art.deerborg.bank.bank.model.entity;

import art.deerborg.bank.customer.model.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "descriptions")
@Getter
@Setter
public class Description {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
