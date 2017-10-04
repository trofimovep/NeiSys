package trofimovep;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class SendMailSSL {
    public static void main(String[] args) {
        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("trofimovep.mt@gmail.com","60letcccp");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trofimovep.mt@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("eptrofimov@yandex.ru"));
            message.setSubject("Testing Subject");
            message.setText("г к");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
































//import java.util.*;
//import javax.mail.*;
//import javax.mail.Transport;//Use this
//import javax.mail.internet.*;
//
//class TestEmail {
//
//    public static void trofimovep.main(String[] args) {
//
//        // Сюда необходимо подставить адрес получателя сообщения
//        String to = "trofimovep.mt@gmail.com";
//        String from = "trofimovep@list.ru";
//        // Сюда необходимо подставить SMTP сервер, используемый для отправки
//        String host = "smtp.mail.ru";
//        // Тут указываем порт SMTP сервера.
//        int port = 465;
//
//        // Создание свойств, получение сессии
//        Properties props = new Properties();
//
//        // При использовании статического метода Transport.send()
//        // необходимо указать через какой хост будет передано сообщение
//        props.put("mail.smtp.host", host);
//        // Если почтовый сервер использует SSL
//        props.put("mail.smtp.ssl.enable", "true");
//        // Указываем порт SMTP сервера.
//        props.put("mail.smtp.port", port);
//        // Большинство SMTP серверов, используют авторизацию.
//        props.put("mail.smtp.auth", "true");
//        // Включение debug-режима
//        props.put("mail.debug", "true");
//        // Авторизируемся.
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//            // Указываем логин пароль, от почты, с которой будем отправлять сообщение.
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                System.out.println("Done");
//                return new PasswordAuthentication("trofimovep@list.ru", "60letcccp");
//
//            }
//        });
//
//        try {
//            // Создание объекта сообщения
//            Message msg = new MimeMessage(session);
//
//            // Установка атрибутов сообщения
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject("Saying that u r faggot");
//            msg.setSentDate(new Date());
//
//            // Установка тела сообщения
//            msg.setText("u r fAGGOT.");
//
//            // Отправка сообщения
//            Transport.send(msg);
//            System.out.println("Done");
//
//        }
//        catch (MessagingException mex) {
//            // Печать информации об исключении в случае его возникновения
//            mex.printStackTrace();
//        }
//    }
//}