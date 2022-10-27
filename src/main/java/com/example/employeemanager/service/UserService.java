package com.example.employeemanager.service;

import com.example.employeemanager.model.Role;
import com.example.employeemanager.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
