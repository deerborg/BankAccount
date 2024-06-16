package art.deerborg.bank.auth.service;

import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.repository.CustomerRepository;
import art.deerborg.bank.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
    public Optional<CustomerEntity> getByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
