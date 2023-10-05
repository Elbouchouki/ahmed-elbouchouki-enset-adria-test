package live.elbouchouki.core.dto.customer;

import jakarta.validation.constraints.Email;

public record CustomerUpdateRequest(
        String firstName,
        String lastName,
        @Email String email
) {
}
