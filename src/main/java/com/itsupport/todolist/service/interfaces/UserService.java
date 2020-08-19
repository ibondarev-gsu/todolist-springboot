package com.itsupport.todolist.service.interfaces;

import com.itsupport.todolist.models.PasswordResetToken;
import com.itsupport.todolist.models.User;
import com.itsupport.todolist.models.VerificationToken;
import com.itsupport.todolist.models.dto.UserDto;
import com.itsupport.todolist.util.exceptions.PasswordResetTokenNotFountException;
import com.itsupport.todolist.util.exceptions.UserAlreadyExistException;
import com.itsupport.todolist.util.exceptions.UserNotFoundException;
import com.itsupport.todolist.util.exceptions.VerificationTokenNotFountException;
import org.springframework.security.core.userdetails.UserDetailsService;

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
}