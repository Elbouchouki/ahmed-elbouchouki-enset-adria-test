package live.elbouchouki.transferservice.controller;

import jakarta.validation.Valid;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.dto.transfer.TransferCreateRequest;
import live.elbouchouki.core.dto.transfer.TransferResponse;
import live.elbouchouki.core.dto.transfer.TransferUpdateRequest;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.transferservice.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Validated
public class TransferController {
    private final TransferService transferService;

    @GetMapping
    public ResponseEntity<PagingResponse<TransferResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                transferService.findAll(page, size)
        );
    }

    @GetMapping("/transfer/{id}")
    public ResponseEntity<TransferResponse> findById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                transferService.findById(id)
        );
    }

    @GetMapping("/transfer/{id}/exists")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                transferService.existsById(id)
        );
    }

    @DeleteMapping("/transfer/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") String id
    ) {
        transferService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<TransferResponse> create(
            @Valid @RequestBody TransferCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        transferService.create(request)
                );
    }

    @PatchMapping("/transfer/{id}")
    public ResponseEntity<TransferResponse> update(
            @PathVariable("id") String id,
            @Valid @RequestBody TransferUpdateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.ok(
                transferService.update(id, request)
        );
    }

}
