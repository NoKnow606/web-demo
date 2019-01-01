package com.example.demo.controller;

import com.example.demo.dao.SystemUserRepository;
import com.example.demo.domain.ResponseFormate;
import com.example.demo.domain.SystemUser;
import com.example.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public String register(@ModelAttribute SystemUser user, ModelMap map) {
//        UserService userService = new UserService();
        String username = user.getUsername();
        SystemUser existsUser = systemUserRepository.findByUsername(username);
        if(existsUser!=null){
            map.addAttribute("error","用户已存在");
            map.addAttribute("code",1000);
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

    @RequestMapping(value = "/back",method = RequestMethod.GET)
    public String back(HttpSession session){
        SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(savedRequest!=null) {
            return "redirect:" + savedRequest.getRedirectUrl();
        }else {
            return "redirect:/loginSuccess";
        }
    }
}
