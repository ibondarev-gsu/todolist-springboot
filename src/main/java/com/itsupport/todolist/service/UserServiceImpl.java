package com.itsupport.todolist.service;

import com.itsupport.todolist.entities.*;
import com.itsupport.todolist.dto.UserDto;
import com.itsupport.todolist.repository.PasswordResetTokenRepository;
import com.itsupport.todolist.repository.TaskRepository;
import com.itsupport.todolist.repository.UserRepository;
import com.itsupport.todolist.repository.VerificationTokenRepository;
import com.itsupport.todolist.service.interfaces.UserService;
import com.itsupport.todolist.util.exceptions.PasswordResetTokenNotFountException;
import com.itsupport.todolist.util.exceptions.UserAlreadyExistException;
import com.itsupport.todolist.util.exceptions.UserNotFoundException;
import com.itsupport.todolist.util.exceptions.VerificationTokenNotFountException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
//@Transactional(readOnly = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username: " + email));
    }

    @Override
    @Transactional
    public User createAccount(final UserDto userDto) throws UserAlreadyExistException {

        final String email = userDto.getEmail();

        if (userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistException("There is an account with that email: " + email);
        }

        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .middleName(userDto.getMiddleName())
                .roles(Collections.singleton(Role.USER))
                .build()
        );
    }

    @Override
    @Transactional
    public void saveVerificationToken(final User user, final String token) {
        user.setVerificationToken(VerificationToken.builder().token(token).build());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void savePasswordResetToken(User user, String token) {
        user.setPasswordResetToken(PasswordResetToken.builder().token(token).build());
        userRepository.save(user);
    }

    @Override
    public VerificationToken findVerificationTokenByToken(final String verificationToken)
            throws VerificationTokenNotFountException {
        return verificationTokenRepository
                .findByToken(verificationToken)
                .orElseThrow(VerificationTokenNotFountException::new);
    }

    @Override
    public PasswordResetToken findPasswordResetTokenByToken(final String passwordResetToken)
            throws PasswordResetTokenNotFountException {
        return passwordResetTokenRepository
                .findByToken(passwordResetToken)
                .orElseThrow(PasswordResetTokenNotFountException::new);
    }

    @Override
    @Transactional
    public void saveUser(final User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserAndVerificationToken(User user, VerificationToken token) {
        verificationTokenRepository.delete(token);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deletePasswordResetToken(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token);
    }

    @Override
    @Transactional
    public void addTask(User user, Task task) {
        task.setUser(user);
        user.getTasks().add(task);
        taskRepository.save(task);
//        taskRepository.save(task);
//        userRepository.save(user);
//        user.getTasks().add(task);
//        task.getUsers().add(user);
//        taskRepository.save(task);
//        userRepository.save(user);
    }

    @Override
    public void addTasks(User user, Collection<? extends Task> tasks) {

    }

    @Override
    public User findUserByEmail(String userEmail) throws UserNotFoundException {
        return userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteTaskById(User user, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        if (user.getTasks().remove(task)){
            taskRepository.delete(task);
        }

    }
}