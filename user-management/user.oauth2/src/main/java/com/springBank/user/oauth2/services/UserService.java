package com.springBank.user.oauth2.services;

import com.springBank.user.oauth2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service(value = "userService")
public class UserService implements UserDetailsService {

    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Incorrect username or password");
        }

        var account = user.get().getAccount();
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .build();
    }
}
