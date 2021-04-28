package com.upgrad.backendet.controller;

import com.upgrad.backendet.model.User;
import com.upgrad.backendet.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    /*INDEX*/
    /*GET*/
    @RequestMapping("/")
    private String profile(){
        return "index";
    }

    /*POST*/
    @RequestMapping(value = "/",method = RequestMethod.POST)
    private String post(
            User user,
            Model model
            )throws Exception{

        if (userService.findByName(user.getName()) != null) {
            model.addAttribute("nameInvalid", true);
            return "index";
        }
        if(userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailInvalid", true);

            return "index";
        }
        if(userService.findByPhoneNumber(user.getPhoneNumber()) != null) {
            model.addAttribute("phoneInvalid", true);

            return "index";
        }



        userService.addData(user);
        return "index";
    }



    /*USERDATA*/
    /*GET*/
    @RequestMapping(value = "/userdata")
    private String userData(
            Model model
    ){
        List<User> users=userService.getAllData();
        model.addAttribute("users", users);
        return "userdata";
    }

    /*SEARCH*/
    @RequestMapping(value = "/findUser")
    private String findUser(
            Model model,
            User findUser
    ){
            User user=userService.findByUser(findUser.getName());
            model.addAttribute("users",user);
            return "userdata";
    }

    /*DELETE*/
    @RequestMapping(method = RequestMethod.POST, value = "/deleteuser")
    public String deletePost(@RequestParam(name = "userId") Integer userId) {
        userService.deletePost(userId);
        return "redirect:/userdata";
    }





}
