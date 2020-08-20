package com.itsupport.todolist.service.interfaces;

import com.itsupport.todolist.entities.User;

public interface MailSenderService {
    void sendActiveCode(User user);
    void sendPasswordResetCode(User user);
}
