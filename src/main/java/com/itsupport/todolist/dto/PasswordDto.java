package com.itsupport.todolist.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordDto {

    @Size(min = 6, max = 32, message = "{Size.userForm.password}")
    private String oldPassword;

    private String token;

    @Size(min = 6, max = 32, message = "{Size.userForm.password}")
    private String newPassword;
}