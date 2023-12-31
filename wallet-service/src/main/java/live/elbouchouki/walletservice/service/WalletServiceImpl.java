package live.elbouchouki.walletservice.service;

import live.elbouchouki.core.constant.CoreConstants;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.dto.wallet.WalletCreateRequest;
import live.elbouchouki.core.dto.wallet.WalletResponse;
import live.elbouchouki.core.dto.wallet.WalletUpdateRequest;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.core.exception.NotFoundException;
import live.elbouchouki.walletservice.mapper.WalletMapper;
import live.elbouchouki.walletservice.model.Wallet;
import live.elbouchouki.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final String ELEMENT_NAME = "Wallet";
    private final String ELEMENT_ID = "id";

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public WalletResponse create(WalletCreateRequest request) throws AlreadyExistsException {

        // TODO: Check if client exists
        // TODO: Must call client-microservice

        return walletMapper.toResponse(
                walletRepository.save(
                        walletMapper.toModel(request)
                )
        );
    }

    @Override
    public WalletResponse update(String id, WalletUpdateRequest request) throws NotFoundException, AlreadyExistsException {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                        null
                ));

        walletMapper.updateModel(request, wallet);

        return walletMapper.toResponse(
                walletRepository.save(wallet)
        );
    }

    @Override
    public WalletResponse findById(String id) throws NotFoundException {
        return walletMapper.toResponse(
                walletRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                                null
                        ))
        );
    }

    @Override
    public PagingResponse<WalletResponse> findAll(int page, int size) {
        return walletMapper.toPagingResponse(
                walletRepository.findAll(
                        PageRequest.of(page, size)
                )
        );
    }

    @Override
    public boolean existsById(String id) {
        return walletRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        if (!walletRepository.existsById(id))
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_NAME, ELEMENT_ID, id,},
                    null
            );
        walletRepository.deleteById(id);
    }
}
