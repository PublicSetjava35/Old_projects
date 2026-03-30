package org.example.ClientUserKafka.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String passport;
    public UserRegistrationDTO(String email, String password, String passport) {
        this.email = email;
        this.password = password;
        this.passport = passport;
    }
    public UserRegistrationDTO() {}
}