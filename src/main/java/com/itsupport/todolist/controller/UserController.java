package com.itsupport.todolist.controller;

import com.itsupport.todolist.entities.Task;
import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.service.interfaces.UserService;
import com.itsupport.todolist.util.exceptions.TaskNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ModelAndView home(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/inbox")
    public ModelAndView inbox(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("inbox");
        return modelAndView;
    }

    @GetMapping("/today")
    public ModelAndView today(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("inbox");
        return modelAndView;
    }

    @GetMapping("/done")
    public ModelAndView done(final ModelAndView modelAndView, final @AuthenticationPrincipal User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("done");
        return modelAndView;
    }

    @PostMapping("/task")
    public ModelAndView addTask(final ModelAndView modelAndView,
                                final @ModelAttribute("task") Task task,
                                final @AuthenticationPrincipal User user){
        userService.addTask(user, task);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("inbox");
        return modelAndView;
    }

    @PostMapping("/task/{id}")
    public ModelAndView deleteTask(final ModelAndView modelAndView,
                                   final @PathVariable Long id,
                                   final @AuthenticationPrincipal User user){
        try {
            userService.deleteTaskById(user, id);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("inbox");
            return modelAndView;
        } catch (TaskNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
