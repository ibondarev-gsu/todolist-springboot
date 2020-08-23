package com.itsupport.todolist.controller;

import com.itsupport.todolist.entities.Task;
import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.repository.UserRepository;
import com.itsupport.todolist.service.interfaces.AdminService;
import com.itsupport.todolist.service.interfaces.UserService;
import com.itsupport.todolist.util.exceptions.TaskNotFoundException;
import com.itsupport.todolist.util.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("/users")
    public ModelAndView userList(final ModelAndView modelAndView, final @AuthenticationPrincipal User admin){
        modelAndView.addObject("user", admin);
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @GetMapping("/user/{id}")
    public ModelAndView userTask(final ModelAndView modelAndView,
                                 final @AuthenticationPrincipal User admin,
                                 final @PathVariable(name = "id") Long userId){
        try {
            User user = userService.findUserById(userId);
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("userTask");
            return modelAndView;
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/task")
    public ModelAndView addUserTask(final ModelAndView modelAndView,
                                    final @AuthenticationPrincipal User admin,
                                    final @ModelAttribute("task") Task task,
                                    final @RequestParam(name = "userId") Long userId){
        try {
            User user = userService.findUserById(userId);
            userService.addTask(user, task);
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("userTask");
            return modelAndView;
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @PostMapping("/user/task/{taskId}")
    public ModelAndView deleteUserTask(final ModelAndView modelAndView,
                                    final @AuthenticationPrincipal User admin,
                                    final @PathVariable("taskId") Long taskId,
                                    final @RequestParam(name = "userId") Long userId){
        try {
            User user = userService.findUserById(userId);
            userService.deleteTaskById(user, taskId);
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("userTask");
            return modelAndView;
        } catch (UserNotFoundException | TaskNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @PostMapping("/users/{id}")
    public ModelAndView userList(final ModelAndView modelAndView,
                                 final @PathVariable Long id,
                                 final @AuthenticationPrincipal User admin){
        adminService.deleteUserById(id);
        modelAndView.addObject("user", admin);
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.setViewName("userList");
        return modelAndView;
    }
}
