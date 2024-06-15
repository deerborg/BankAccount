package art.deerborg.bank.customer.model.mapper;

import art.deerborg.bank.customer.model.dto.request.CustomerCreateAccountRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-15T03:03:32+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerEntity fromCustomerCreateRequest(CustomerCreateRequest customerCreateRequest) {
        if ( customerCreateRequest == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstName( customerCreateRequest.getFirstName() );
        customerEntity.setLastName( customerCreateRequest.getLastName() );
        customerEntity.setBirthDate( customerCreateRequest.getBirthDate() );
        customerEntity.setEmail( customerCreateRequest.getEmail() );
        customerEntity.setPhone( customerCreateRequest.getPhone() );
        customerEntity.setPassword( customerCreateRequest.getPassword() );

        return customerEntity;
    }

    @Override
    public CustomerEntity fromCustomerUpdateRequest(CustomerUpdateRequest customerUpdateRequest) {
        if ( customerUpdateRequest == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( customerUpdateRequest.getId() );
        customerEntity.setEmail( customerUpdateRequest.getEmail() );
        customerEntity.setPhone( customerUpdateRequest.getPhone() );
        customerEntity.setPassword( customerUpdateRequest.getPassword() );

        return customerEntity;
    }

    @Override
    public CustomerEntity fromCustomerCreateAccountRequest(CustomerCreateAccountRequest customerCreateAccountRequest) {
        if ( customerCreateAccountRequest == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( customerCreateAccountRequest.getId() );

        return customerEntity;
    }

    @Override
    public CustomerResponse toCustomerResponse(CustomerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId( entity.getId() );
        customerResponse.setFullName( entity.getFullName() );

        return customerResponse;
    }

    @Override
    public CustomerDetailResponse toCustomerDetailResponse(CustomerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDetailResponse customerDetailResponse = new CustomerDetailResponse();

        customerDetailResponse.setId( entity.getId() );
        customerDetailResponse.setFullName( entity.getFullName() );
        customerDetailResponse.setBirthDate( entity.getBirthDate() );
        customerDetailResponse.setEmail( entity.getEmail() );
        customerDetailResponse.setPhone( entity.getPhone() );
        customerDetailResponse.setPassword( entity.getPassword() );

        return customerDetailResponse;
    }
}
