package com.dev.cinema.model.dto;

import com.dev.cinema.lib.ValidEmail;
import com.dev.cinema.lib.ValidPassword;
import java.util.List;

@ValidPassword(field = "password", fieldMatch = "repeatPassword")
public class UserRequestDto {
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
    private List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
