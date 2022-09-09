package com.spring.project.dto.account;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class PersonDTO {

    @NotEmpty(message = "Username shouldn't be empty")
    private String username;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    @NotEmpty(message = "We should confirm your password")
    private String confirmPassword;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
