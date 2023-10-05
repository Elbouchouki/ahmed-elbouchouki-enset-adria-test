package live.elbouchouki.customerservice.mapper;

import live.elbouchouki.core.dto.customer.CustomerCreateRequest;
import live.elbouchouki.core.dto.customer.CustomerResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.customerservice.model.Customer;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CustomerMapper {
    Customer toModel(CustomerCreateRequest request);

    List<Customer> toModelList(List<CustomerCreateRequest> requestList);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customerList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(CustomerCreateRequest request, @MappingTarget Customer customer);

    @Mapping(target = "page", expression = "java(model.getNumber())")
    @Mapping(target = "size", expression = "java(model.getSize())")
    @Mapping(target = "totalPages", expression = "java(model.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(model.getNumberOfElements())")
    @Mapping(target = "records", expression = "java(toResponseList(model.getContent()))")
    PagingResponse<CustomerResponse> toPagingResponse(Page<Customer> model);
}
