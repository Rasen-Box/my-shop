package com.shop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String role;
}
