package art.deerborg.bank.customer.controller;

import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

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

}
