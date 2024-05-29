package org.zerock.b01.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String id;
    private String name;
    private String password;
}
