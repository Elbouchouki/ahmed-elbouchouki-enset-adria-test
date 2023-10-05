package live.elbouchouki.customerservice.controller;

import jakarta.validation.Valid;
import live.elbouchouki.core.dto.customer.CustomerCreateRequest;
import live.elbouchouki.core.dto.customer.CustomerResponse;
import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.exception.AlreadyExistsException;
import live.elbouchouki.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<PagingResponse<CustomerResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                customerService.findAll(page, size)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<PagingResponse<CustomerResponse>> search(
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

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                customerService.findById(id)
        );
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(
                customerService.existsById(id)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") String id
    ) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        customerService.create(request)
                );
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable("id") String id,
            @Valid @RequestBody CustomerCreateRequest request
    ) throws AlreadyExistsException {
        return ResponseEntity.ok(
                customerService.update(id, request)
        );
    }

}
