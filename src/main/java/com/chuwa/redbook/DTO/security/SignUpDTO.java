package com.chuwa.redbook.DTO.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpDTO {

    private String name;
    private String email;

    @JsonProperty(value = "account")
    private String account;

    private String password;

    public SignUpDTO(String name, String email, String account, String password) {
        this.name = name;
        this.email = email;
        this.account = account;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
