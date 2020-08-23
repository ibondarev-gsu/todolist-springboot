package com.itsupport.todolist.util.exceptions;

public class TaskNotFoundException extends Exception {
    public  TaskNotFoundException(){
        super();
    }

    public TaskNotFoundException(String error){
        super(error);
    }
}
