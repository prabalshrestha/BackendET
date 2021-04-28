package com.upgrad.backendet.controller;

import com.upgrad.backendet.model.User;
import com.upgrad.backendet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class EditController {
    @Autowired
    private UserService userService;

    /*EDIT*/
    @RequestMapping(value = "edituser")
    private String editusers(@RequestParam(value = "userId")Integer userId,
                             Model model
    ){
        Optional<User> user=userService.findByUserId(userId);
        User user1=user.get();
        model.addAttribute("user",user1);
        return "edit";
    }

    @RequestMapping(method = RequestMethod.POST,value = "edituser")
    private String updateData(@RequestParam(value="userId") Integer userId,User user,Model model){


        user.setId(userId);
        userService.updateData(user);
        return "redirect:/userdata";
    }
}
