package com.hero.services;

import com.hero.models.SimpleUser;
import com.hero.models.entities.UserEntity;
import com.hero.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class HeroUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public HeroUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = getFullUserByUsername(userName);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());
    }

    public SimpleUser getUserByUsername(String userName) {
        getFullUserByUsername(userName); // user exists?
        return new SimpleUser(userName);
    }

    private UserEntity getFullUserByUsername(String userName) {
        return userRepository.findFirstByUsername(userName);
    }
}
