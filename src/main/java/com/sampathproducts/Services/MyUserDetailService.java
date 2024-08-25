package com.sampathproducts.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sampathproducts.Role.Role;
import com.sampathproducts.User.User;
import com.sampathproducts.User.UserDao;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {

        System.out.println(user_name);
        User extUser = userDao.getUserByUsername(user_name);

        System.out.println(extUser.getUser_name());

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        for (Role role : extUser.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(extUser.getUser_name(),
                extUser.getUser_password(), extUser.getUser_status(), true, true, true, grantedAuthorities);

        return user;
    }

}
