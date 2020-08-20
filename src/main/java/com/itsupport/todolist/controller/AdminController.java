package com.itsupport.todolist.controller;

import com.itsupport.todolist.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/users")
    public ModelAndView userList(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userList");
        return modelAndView;
    }
}
