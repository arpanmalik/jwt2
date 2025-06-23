package com.example.demo2PractiseSecurity.service;

import com.example.demo2PractiseSecurity.models.User;
import com.example.demo2PractiseSecurity.models.UserPrincipal;
import com.example.demo2PractiseSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomeUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }
}
