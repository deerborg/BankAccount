package art.deerborg.bank.customer.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
