package com.example.demo.controller;

import com.example.demo.dao.SystemUserRepository;
import com.example.demo.domain.ResponseFormate;
import com.example.demo.domain.SystemUser;
import com.example.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

//@RestController
@Controller
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginSuccess() {
        return "initiate";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){return "register";}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute SystemUser user, BindingResult bindingResult) {
//        UserService userService = new UserService();
        String username = user.getUsername();
        SystemUser existsUser = systemUserRepository.findByUsername(username);
        if(existsUser!=null){
            bindingResult.reject("用户已经存在");
            return "register";
        }
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
        systemUserRepository.save(user);
//        userService.insert(user);
        return "login";
    }

    @RequestMapping(value = "/launchGroup",method = RequestMethod.GET)
    public String launchGroup(){
        return "launchGroup";
    }
}
