package com.itsupport.todolist.util.exceptions;

public class PasswordResetTokenNotFountException extends Exception {
    public  PasswordResetTokenNotFountException(){
        super();
    }

    public PasswordResetTokenNotFountException(String error){
        super(error);
    }
}
