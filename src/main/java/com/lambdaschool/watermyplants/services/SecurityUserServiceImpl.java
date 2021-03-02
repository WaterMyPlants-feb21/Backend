package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service("securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userrepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String un) throws UsernameNotFoundException{
        User user = userrepo.findByUsername(un.toLowerCase());
        if(user == null){
            throw new EntityNotFoundException("Invalid Username or Password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
}
