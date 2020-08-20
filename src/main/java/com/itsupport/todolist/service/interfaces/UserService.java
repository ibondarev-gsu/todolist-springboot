package com.itsupport.todolist.service.interfaces;

import com.itsupport.todolist.entities.PasswordResetToken;
import com.itsupport.todolist.entities.Task;
import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.entities.VerificationToken;
import com.itsupport.todolist.dto.UserDto;
import com.itsupport.todolist.util.exceptions.PasswordResetTokenNotFountException;
import com.itsupport.todolist.util.exceptions.UserAlreadyExistException;
import com.itsupport.todolist.util.exceptions.UserNotFoundException;
import com.itsupport.todolist.util.exceptions.VerificationTokenNotFountException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    void saveVerificationToken(final User user, final String token);
    void savePasswordResetToken(final User user, final String token);

    User findUserByUsername(final String username) throws UserNotFoundException;
    User findUserByEmail(final String userEmail) throws UserNotFoundException;
    User createAccount(final UserDto userDto) throws UserAlreadyExistException;

    VerificationToken findVerificationTokenByToken(final String token) throws VerificationTokenNotFountException;
    PasswordResetToken findPasswordResetTokenByToken(final String token) throws PasswordResetTokenNotFountException;

    void saveUser(final User user);

    void saveUserPassword(final User user, final String password);

    void deleteUserAndVerificationToken(final User user, final VerificationToken token);

    void deletePasswordResetToken(final PasswordResetToken token);

    void addTask(User user, Task task);
    void addTasks(User user, Collection<? extends Task> tasks);
    void deleteTaskById(User user, Long id);
}