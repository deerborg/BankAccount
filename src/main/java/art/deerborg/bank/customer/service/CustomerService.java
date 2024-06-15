package art.deerborg.bank.customer.service;

import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateAccountRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(CustomerCreateRequest request);

    ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(CustomerUpdateRequest request);

    ResponseEntity<ApiResponse<List<CustomerDetailResponse>>> getAllCustomers();

    ResponseEntity<ApiResponse<CustomerDetailResponse>> getCustomerById(String customerId);

    CustomerEntity getByEmail(String email);

}
