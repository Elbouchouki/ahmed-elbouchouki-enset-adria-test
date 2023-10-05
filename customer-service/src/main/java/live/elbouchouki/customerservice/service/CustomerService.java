package live.elbouchouki.customerservice.service;

import live.elbouchouki.core.dto.customer.CustomerCreateRequest;
import live.elbouchouki.core.dto.customer.CustomerResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;

public interface CustomerService {
    CustomerResponse create(CustomerCreateRequest request) throws AlreadyExistsException;

    CustomerResponse update(String id, CustomerCreateRequest request) throws NotFoundException, AlreadyExistsException;

    CustomerResponse findById(String id) throws NotFoundException;

    PagingResponse<CustomerResponse> findAll(int page, int size);

    PagingResponse<CustomerResponse> search(int page, int size, String firstName, String lastName, String email);

    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
