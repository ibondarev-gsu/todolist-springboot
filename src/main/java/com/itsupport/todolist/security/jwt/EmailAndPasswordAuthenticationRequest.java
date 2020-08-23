package com.itsupport.todolist.security.jwt;

import lombok.Data;

@Data
public class EmailAndPasswordAuthenticationRequest {
    private String email;
    private String password;
}
