package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    @NotEmpty
    @Size(min = 3, max = 12)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 12)
    private String id;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;
}
