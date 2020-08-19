package com.itsupport.todolist.util.exceptions;

public class UserAlreadyExistException extends Exception {
    public  UserAlreadyExistException(){
        super();
    }

    public UserAlreadyExistException(String error){
        super(error);
    }
}

