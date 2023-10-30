package live.elbouchouki.walletservice.service;

import live.elbouchouki.core.dto.wallet.WalletCreateRequest;
import live.elbouchouki.core.dto.wallet.WalletResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;

public interface WalletService {
    WalletResponse create(WalletCreateRequest request) throws AlreadyExistsException;

    WalletResponse update(String id, WalletCreateRequest request) throws NotFoundException, AlreadyExistsException;

    WalletResponse findById(String id) throws NotFoundException;

    PagingResponse<WalletResponse> findAll(int page, int size);


    boolean existsById(String id);

    void deleteById(String id) throws NotFoundException;
}
