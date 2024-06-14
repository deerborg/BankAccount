package art.deerborg.bank.customer.model.mapper;

import art.deerborg.bank.customer.model.dto.request.CustomerCreateRequest;
import art.deerborg.bank.customer.model.dto.request.CustomerUpdateRequest;
import art.deerborg.bank.customer.model.dto.response.CustomerDetailResponse;
import art.deerborg.bank.customer.model.dto.response.CustomerResponse;
import art.deerborg.bank.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity fromCustomerCreateRequest(CustomerCreateRequest customerCreateRequest);
    CustomerEntity fromCustomerUpdateRequest(CustomerUpdateRequest customerUpdateRequest);



    CustomerResponse toCustomerResponse(CustomerEntity entity);
    CustomerDetailResponse toCustomerDetailResponse(CustomerEntity entity);

}