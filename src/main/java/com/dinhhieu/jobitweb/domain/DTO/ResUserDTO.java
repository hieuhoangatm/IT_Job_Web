package com.dinhhieu.jobitweb.domain.DTO;

import com.dinhhieu.jobitweb.util.Enum.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDTO {
    private long id;
    private String email;
    private String name;


    private GenderEnum gender;

    private String address;
    private int age;
    private Instant updatedAt;
    private Instant createdAt;

}
