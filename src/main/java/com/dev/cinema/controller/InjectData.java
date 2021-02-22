package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectData(RoleService roleService, UserService userService,
                      PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void addRole() {
        Role user = new Role();
        user.setRoleName("USER");
        roleService.add(user);
        Role admin = new Role();
        admin.setRoleName("ADMIN");
        roleService.add(admin);

        User userAdmin = new User();
        userAdmin.setEmail("bob");
        userAdmin.setPassword(passwordEncoder.encode("1234"));
        userAdmin.setRoles(List.of(admin));
        userService.add(userAdmin);
    }
}
