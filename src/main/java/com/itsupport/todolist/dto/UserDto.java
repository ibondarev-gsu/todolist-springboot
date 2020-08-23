package com.itsupport.todolist.dto;

import com.itsupport.todolist.util.annotations.FieldMatch;
import com.itsupport.todolist.util.annotations.ValidEmail;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Component
@FieldMatch(first = "password", second = "confirmPassword", message = "{Diff.userForm.passwordConfirm}")
public class UserDto {

    @Size(min = 4, max = 32, message = "{Size.userForm.username}")
    private String username;

    @Size(min = 6, max = 32, message = "{Size.userForm.password}")
    private String password;

    @NotBlank(message = "{NotBlank}")
    private String confirmPassword;

    @ValidEmail(message = "{user.invalid.email}")
    private String email;

    private String firstName;

    private String lastName;

    private String middleName;
}