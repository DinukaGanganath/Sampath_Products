package com.sampathproducts.Email;

public interface EmailService {

    void sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
