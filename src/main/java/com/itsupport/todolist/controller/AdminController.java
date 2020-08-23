package com.itsupport.todolist.controller;

import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.repository.UserRepository;
import com.itsupport.todolist.service.interfaces.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final AdminService adminService;

    @GetMapping("/users")
    public ModelAndView userList(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @PostMapping("/users/{id}")
    public ModelAndView userList(final ModelAndView modelAndView,
                                 final @PathVariable Long id,
                                 final @AuthenticationPrincipal User user){
        adminService.deleteUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.setViewName("userList");
        return modelAndView;
    }
}
