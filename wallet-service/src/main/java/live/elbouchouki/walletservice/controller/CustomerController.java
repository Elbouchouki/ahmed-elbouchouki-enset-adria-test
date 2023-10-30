package live.elbouchouki.billingservice.controller;

import jakarta.validation.Valid;
import live.elbouchouki.core.dto.customer.WalletCreateRequest;
import live.elbouchouki.core.dto.customer.WalletResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.customerservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
public class WalletController {
    private final WalletService customerService;

    @GetMapping
    public ResponseEntity<PagingResponse<WalletResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                customerService.findAll(page, size)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<PagingResponse<WalletResponse>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "", required = false) String firstName,
            @RequestParam(defaultValue = "", required = false) String lastName,
            @RequestParam(defaultValue = "", required = false) String email
    ) {
        return ResponseEntity.ok(
                customerService.search(page, size, firstName, lastName, email)
        );
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<WalletResponse> findById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                customerService.findById(id)
        );
    }

    @GetMapping("/customer/{id}/exists")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                customerService.existsById(id)
        );
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") String id
    ) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<WalletResponse> create(
            @Valid @RequestBody WalletCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        customerService.create(request)
                );
    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<WalletResponse> update(
            @PathVariable("id") String id,
            @Valid @RequestBody WalletCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.ok(
                customerService.update(id, request)
        );
    }

}
