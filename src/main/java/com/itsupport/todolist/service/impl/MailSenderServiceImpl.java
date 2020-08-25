package com.itsupport.todolist.service;

import com.itsupport.todolist.entities.User;
import com.itsupport.todolist.service.interfaces.MailSenderService;
import com.itsupport.todolist.service.interfaces.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;

    public MailSenderServiceImpl(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    private void sendEmail(String toAddress, String subject, String msgBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);
        javaMailSender.send(message);
    }

    // TODO: 19.08.2020 add prop
    @Async
    @Override
    public void sendActiveCode(User user) {

        String activeCode = UUID.randomUUID().toString();
        userService.saveVerificationToken(user, activeCode);

        String toAddress = user.getEmail();
        String subject = "Active account";
        String msgBody =  "http://localhost:8080/active?code=" + activeCode;

        sendEmail(toAddress, subject, msgBody);
    }

    // TODO: 19.08.2020 add prop
    @Async
    @Override
    public void sendPasswordResetCode(User user) {

        String resetCode = UUID.randomUUID().toString();
        userService.savePasswordResetToken(user, resetCode);

        String toAddress = user.getEmail();
        String subject = "Reset password";
        String msgBody = "http://localhost:8080/changePassword?code=" + resetCode;

        sendEmail(toAddress, subject, msgBody);
    }
}
