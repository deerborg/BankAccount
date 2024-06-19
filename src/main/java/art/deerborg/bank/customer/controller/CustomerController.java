package art.deerborg.bank.customer.controller;

import art.deerborg.bank.auth.service.JwtService;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.repository.CustomerRepository;
import art.deerborg.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    //private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomerRepository repository;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        return service.createCustomer(request);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@Valid @RequestBody CustomerUpdateRequest request) {
        return service.updateCustomer(request);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDetailResponse>> getCustomer(@Valid @PathVariable("id") String customerId) {
        return service.getCustomerById(customerId);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDetailResponse>>> getAllCustomers() {
        return service.getAllCustomers();
    }
    @GetMapping("/get-customer")
    public ResponseEntity<ApiResponse<String>> customer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return new ResponseEntity<>(ApiResponseHelper.OK(repository.findByEmail(email).get().getId()),HttpStatus.OK);
    }
    /*

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> generateToken(@RequestBody CustomerEntity customer){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword()));
        if(authentication.isAuthenticated()){
            //return jwtService.generateToken(customer.getEmail());
            return new ResponseEntity<>(ApiResponseHelper.OK("Bearer "+jwtService.generateToken(customer.getEmail())), HttpStatus.OK);
        }
        throw new RuntimeException("Authentication failed");
    }

     */
}
