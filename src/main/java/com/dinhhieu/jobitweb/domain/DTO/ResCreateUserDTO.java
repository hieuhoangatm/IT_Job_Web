package com.dinhhieu.jobitweb.domain.DTO;

import com.dinhhieu.jobitweb.util.Enum.GenderEnum;
import com.dinhhieu.jobitweb.util.SecurityUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ResCreateUserDTO {
    private long id;
    private String name;
    private String email;
    private int age;
    private GenderEnum gender;
    private String address;
    private String refreshToken;
    private Instant createdAt;
    private String createdBy;

}
