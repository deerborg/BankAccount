package art.deerborg.bank.customer.model.entity;

import art.deerborg.bank.bank.model.entity.AccountEntity;
import art.deerborg.bank.bank.model.entity.Description;
import art.deerborg.bank.common.model.entity.BaseEntity;
import art.deerborg.bank.customer.model.entity.enums.CustomerType;
import art.deerborg.bank.customer.model.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity extends BaseEntity implements UserDetails{
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
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    private String password;
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private AccountEntity account;
    @OneToMany(cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Description> accounts;

    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
