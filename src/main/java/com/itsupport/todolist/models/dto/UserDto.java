package com.itsupport.todolist.models.dto;

import com.itsupport.todolist.util.annotations.FieldMatch;
import com.itsupport.todolist.util.annotations.ValidEmail;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Data
@Component
@FieldMatch(first = "password", second = "confirmPassword", message = "{Diff.userForm.passwordConfirm}")
public class UserDto {

    @Size(min = 4, max = 32, message = "{Size.userForm.username}")
    private String username;

    @Size(min = 6, max = 32, message = "{Size.userForm.password}")
    private String password;

    //@FieldMatch applies to this field.
    @NotEmpty
    private String confirmPassword;

    @ValidEmail(message = "{user.invalid.email}")
    private String email;

    //    @NotNull
//    @NotEmpty
    private String firstName;

    //    @NotNull
//    @NotEmpty
    private String lastName;

    //    @NotNull
//    @NotEmpty
    private String middleName;
}