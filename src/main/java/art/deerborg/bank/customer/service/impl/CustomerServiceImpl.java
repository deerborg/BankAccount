package art.deerborg.bank.customer.service.impl;


import art.deerborg.bank.auth.service.JwtService;
import art.deerborg.bank.common.util.exceptions.NotFoundIdException;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.model.entity.enums.Role;
import art.deerborg.bank.customer.model.mapper.CustomerMapper;
import art.deerborg.bank.customer.repository.CustomerRepository;
import art.deerborg.bank.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;


    @Override
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(CustomerCreateRequest request) {
        CustomerEntity entity = mapper.fromCustomerCreateRequest(request);

        entity.setFullName(request.getFirstName() + " " + request.getLastName());

        entity.setPassword(passwordEncoder.encode(request.getPassword()));

        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setRoles(List.of(Role.CUSTOMER));

        CustomerResponse response = mapper.toCustomerResponse(customerRepository.save(entity));

        return new ResponseEntity<>(ApiResponseHelper.CREATE(response), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> getAuthenticatedCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return new ResponseEntity<>(ApiResponseHelper.OK(customerRepository.findByEmail(email).get().getId()),HttpStatus.OK);
    }

}
