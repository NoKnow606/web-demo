package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,User>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList(){
//        List<User> list = new ArrayList<>(users.values());
        List<User> list = userRepository.findAll();
        return list;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user){
        System.out.println("user:"+user.toString());

//        users.put(user.getId(),user);
        userRepository.save(user);

        return "success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id){
//        return users.get(id);
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            return u.get();
        }else {
            return null;
        }


    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUserById(@PathVariable Long id,@ModelAttribute User user){
        userRepository.updateUserNameById(id,user.getName());
//        User u = users.get(id);
//        u.setAge(user.getAge());
//        u.setName(user.getName());
//        users.put(id,u);
        return "success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable Long id){
//        users.remove(id);
        userRepository.deleteById(id);
        return "success";
    }



}
