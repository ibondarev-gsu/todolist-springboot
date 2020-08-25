package com.itsupport.todolist.service;

import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.repository.UserRepository;
import com.itsupport.todolist.service.interfaces.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userRepository.delete(user);;
    }
}
