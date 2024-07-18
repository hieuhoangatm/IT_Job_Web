package com.dinhhieu.jobitweb.domain.DTO;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
@Data
@Builder
public class ReqLoginDTO {

    @NotBlank(message = "username không được để trống")
    private String username;

    @NotBlank(message = "password không được để trống")
    private String password;
}
