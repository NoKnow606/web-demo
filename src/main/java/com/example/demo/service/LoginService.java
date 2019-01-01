//package com.example.demo.service;
//
//import com.example.demo.domain.RealUser;
//import com.example.demo.domain.RealUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService implements UserDetailsService {
//
//    @Autowired
//    private RealUserRepository realUserRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        RealUser realUser = realUserRepository.findByName(name);
//        if(realUser==null){
//            throw  new UsernameNotFoundException("this userName no exists");
//        }
//
//        return realUser;
//    }
//}
