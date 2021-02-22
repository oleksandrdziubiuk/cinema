package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;

    public InjectData(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
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
        userAdmin.setPassword("1234");
        userAdmin.setRoles(List.of(admin));
        userService.add(userAdmin);
    }
}
