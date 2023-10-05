package live.elbouchouki.customerservice.service;

import live.elbouchouki.core.constant.CoreConstants;
import live.elbouchouki.core.dto.customer.CustomerCreateRequest;
import live.elbouchouki.core.dto.customer.CustomerResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;
import live.elbouchouki.customerservice.mapper.CustomerMapper;
import live.elbouchouki.customerservice.model.Customer;
import live.elbouchouki.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final String ELEMENT_NAME = "Customer";
    private final String ELEMENT_ID = "id";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse create(CustomerCreateRequest request) throws AlreadyExistsException {
        if (customerRepository.countByEmail(request.email()) > 0) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_NAME, "email", request.email(),},
                    null
            );
        }
        return customerMapper.toResponse(
                customerRepository.save(
                        customerMapper.toModel(request)
                )
        );
    }

    @Override
    public CustomerResponse update(String id, CustomerCreateRequest request) throws NotFoundException, AlreadyExistsException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                        null
                ));


        if (
                request.email() != null
                        && !customer.getEmail().equals(request.email())
                        && customerRepository.countByEmail(request.email()) > 0
        )
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_NAME, "email", request.email(),},
                    null
            );

        customerMapper.updateModel(request, customer);

        return customerMapper.toResponse(
                customerRepository.save(customer)
        );
    }

    @Override
    public CustomerResponse findById(String id) throws NotFoundException {
        return customerMapper.toResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                                null
                        ))
        );
    }

    @Override
    public PagingResponse<CustomerResponse> findAll(int page, int size) {
        return customerMapper.toPagingResponse(
                customerRepository.findAll(
                        PageRequest.of(page, size)
                )
        );
    }

    @Override
    public PagingResponse<CustomerResponse> search(int page, int size, String firstName, String lastName, String email) {
        return customerMapper.toPagingResponse(
                customerRepository.findAll(
                        Example.of(
                                Customer.builder()
                                        .firstName(firstName)
                                        .lastName(lastName)
                                        .email(email)
                                        .build(),
                                SEARCH_MATCH_ALL
                        ),
                        PageRequest.of(page, size)
                )
        );
    }

    @Override
    public boolean existsById(String id) {
        return customerRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        if (!customerRepository.existsById(id))
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                    null
            );
        customerRepository.deleteById(id);
    }


    private static final ExampleMatcher SEARCH_MATCH_ALL = ExampleMatcher.matchingAll()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
}
