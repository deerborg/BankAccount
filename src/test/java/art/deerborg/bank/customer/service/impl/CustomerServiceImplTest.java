package art.deerborg.bank.customer.service.impl;

import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import art.deerborg.bank.customer.model.entity.enums.Role;
import art.deerborg.bank.customer.model.mapper.CustomerMapper;
import art.deerborg.bank.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerServiceImpl customerService;


    @BeforeEach
    public void setUp() {
        this.customerRepository = Mockito.mock(CustomerRepository.class);
        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.customerMapper = Mockito.mock(CustomerMapper.class);

        this.customerService = new CustomerServiceImpl(customerMapper,customerRepository,passwordEncoder);
    }

    @Test
    void Should_Success_Mapped_For_Request_And_Return_Response_When_CreateCustomer(){

        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest();
        customerCreateRequest.setPassword("1511");
        customerCreateRequest.setEmail("mail@mail.com");
        customerCreateRequest.setFirstName("John");
        customerCreateRequest.setLastName("Doe");
        customerCreateRequest.setBirthDate(LocalDate.now());
        customerCreateRequest.setPhone("0555");

        CustomerEntity customer = new CustomerEntity();

        customer.setFullName(customerCreateRequest.getFirstName() + " " + customerCreateRequest.getLastName());

        when(customerMapper.fromCustomerCreateRequest(customerCreateRequest)).thenReturn(new CustomerEntity());
        assertEquals(customer.getFullName(),customerCreateRequest.getFirstName() + " " + customerCreateRequest.getLastName());


        String password = customer.getPassword();
        when(passwordEncoder.encode(customer.getPassword())).thenReturn(password);


        CustomerResponse responseEntity = customerMapper.toCustomerResponse(customer);
        when(customerMapper.toCustomerResponse(customerRepository.save(customer))).thenReturn(responseEntity);

        ResponseEntity<ApiResponse<CustomerResponse>> response = customerService.createCustomer(customerCreateRequest);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        verify(customerMapper,times(1)).fromCustomerCreateRequest(customerCreateRequest);

        verify(customerMapper,times(1)).toCustomerResponse(customer);

        verify(customerRepository,times(1)).save(customer);

        verify(passwordEncoder,times(1)).encode(customerCreateRequest.getPassword());
    }

    @Test
    void Should_Success_Give_Id_When_GetAuthenticatedCustomerId() {

        String email = "mail@mail.com";
        String password = "1511";

        CustomerEntity customer = new CustomerEntity();

        customer.setEmail(email);
        customer.setPassword(password);
        customer.setId(UUID.randomUUID().toString());

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        ResponseEntity<ApiResponse<String>> response = customerService.getAuthenticatedCustomerId();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getId(),response.getBody().getData());

        verify(customerRepository, times(1)).findByEmail(email);

    }
}