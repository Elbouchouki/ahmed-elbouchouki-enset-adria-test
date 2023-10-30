package live.elbouchouki.transferservice.service;

import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.dto.transfer.TransferCreateRequest;
import live.elbouchouki.core.dto.transfer.TransferResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;

public interface TransferService {
    TransferResponse create(TransferCreateRequest request) throws AlreadyExistsException;

    TransferResponse update(String id, TransferCreateRequest request) throws NotFoundException, AlreadyExistsException;

    TransferResponse findById(String id) throws NotFoundException;

    PagingResponse<TransferResponse> findAll(int page, int size);


    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
