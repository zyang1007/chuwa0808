package com.chuwa.redbook.DTO.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogInDTO {

    @JsonProperty("accountOrEmail")
    private String accountOrEmail;

    private String password;

    public LogInDTO() {
    }

    public LogInDTO(String accountOrEmail, String password) {
        this.accountOrEmail = accountOrEmail;
        this.password = password;
    }

    public String getAccountOrEmail() {
        return accountOrEmail;
    }

    public void setAccountOrEmail(String accountOrEmail) {
        this.accountOrEmail = accountOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
