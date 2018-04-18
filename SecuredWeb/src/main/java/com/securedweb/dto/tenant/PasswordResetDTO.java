package com.securedweb.dto.tenant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PasswordResetDTO {

    private String password;
    private String confirmPassword;
    private String token;

}