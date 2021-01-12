package com.iths.airtravels.service;

import com.iths.airtravels.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
    Users getUserByEmail(String email);
    Users creatUser(Users user);
}
