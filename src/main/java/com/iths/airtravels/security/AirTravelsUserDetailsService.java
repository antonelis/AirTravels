package com.iths.airtravels.security;

import com.iths.airtravels.entity.AuthGroup;
import com.iths.airtravels.repository.AuthGroupRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.iths.airtravels.entity.User;
import com.iths.airtravels.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirTravelsUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private AuthGroupRepository authGroupRepository;

    public AirTravelsUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find username: " + username);
        }
        List<AuthGroup> authGroups = authGroupRepository.findByUsername(username);
        return new AirTravelsUserPrincipal(user, authGroups);
    }

}