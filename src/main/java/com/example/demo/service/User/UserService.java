package com.example.demo.service.User;

import com.example.demo.dao.SystemUserRepository;
import com.example.demo.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService implements UserDetailsService {

    @Autowired
    public SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUser user = systemUserRepository.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    public boolean isExists(SystemUser user){
        String username = user.getUsername();
        System.out.println(systemUserRepository.toString()+"rowland");
        SystemUser existsUser = systemUserRepository.findByUsername(username);
        if(existsUser==null){
            return false;
        }else {
            return true;
        }
    }

    public void insert(SystemUser user){
        encryptPassword(user);
        systemUserRepository.save(user);
    }

    public void encryptPassword(SystemUser user){
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
    }

}
