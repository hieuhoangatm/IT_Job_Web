package com.dinhhieu.jobitweb.domain.response;

import com.dinhhieu.jobitweb.util.Enum.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ResUpdateUserDTO {
    private long id;
    private String name;
    private GenderEnum gender;
    private String address;

    private int age;

    private Instant updatedAt;
}
