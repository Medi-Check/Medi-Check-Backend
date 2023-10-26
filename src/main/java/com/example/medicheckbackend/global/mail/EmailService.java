package com.example.medicheckbackend.global.mail;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}
