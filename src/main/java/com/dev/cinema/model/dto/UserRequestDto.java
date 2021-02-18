package com.dev.cinema.model.dto;

import com.dev.cinema.lib.ValidEmail;
import com.dev.cinema.lib.ValidPassword;

@ValidPassword(field = "password", fieldMatch = "repeatPassword")
public class UserRequestDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
