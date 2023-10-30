package live.elbouchouki.transferservice.service;

import live.elbouchouki.core.constant.CoreConstants;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.dto.transfer.TransferCreateRequest;
import live.elbouchouki.core.dto.transfer.TransferResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;
import live.elbouchouki.transferservice.mapper.TransferMapper;
import live.elbouchouki.transferservice.model.Transfer;
import live.elbouchouki.transferservice.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final String ELEMENT_NAME = "Transfer";
    private final String ELEMENT_ID = "id";

    private final TransferRepository customerRepository;
    private final TransferMapper customerMapper;

    @Override
    public TransferResponse create(TransferCreateRequest request) throws AlreadyExistsException {

        // check if client exists

        // check if source exits

        // check if source has enough balance

        // check if destination exists

        return customerMapper.toResponse(
                customerRepository.save(
                        customerMapper.toModel(request)
                )
        );
    }

    @Override
    public TransferResponse update(String id, TransferCreateRequest request) throws NotFoundException, AlreadyExistsException {
        Transfer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                        null
                ));

        customerMapper.updateModel(request, customer);

        return customerMapper.toResponse(
                customerRepository.save(customer)
        );
    }

    @Override
    public TransferResponse findById(String id) throws NotFoundException {
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
    public PagingResponse<TransferResponse> findAll(int page, int size) {
        return customerMapper.toPagingResponse(
                customerRepository.findAll(
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
