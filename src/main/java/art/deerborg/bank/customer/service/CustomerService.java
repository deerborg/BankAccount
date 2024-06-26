package art.deerborg.bank.customer.service;

import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(CustomerCreateRequest request);

    ResponseEntity<ApiResponse<String>> getAuthenticatedCustomerId();

    /*
    ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(CustomerUpdateRequest request);

    ResponseEntity<ApiResponse<List<CustomerDetailResponse>>> getAllCustomers();

    ResponseEntity<ApiResponse<CustomerDetailResponse>> getCustomerById(String customerId);
     */

}
