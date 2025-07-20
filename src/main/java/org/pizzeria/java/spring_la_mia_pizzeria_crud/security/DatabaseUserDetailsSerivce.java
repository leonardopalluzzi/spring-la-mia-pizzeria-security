package org.pizzeria.java.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.pizzeria.java.spring_la_mia_pizzeria_crud.model.User;
import org.pizzeria.java.spring_la_mia_pizzeria_crud.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsSerivce implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new DatabaseUserDetails(user.get());
    }
}
