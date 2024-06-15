package art.deerborg.bank.auth.service;

import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {
    private final CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerService.getByEmail(email);
    }
}
