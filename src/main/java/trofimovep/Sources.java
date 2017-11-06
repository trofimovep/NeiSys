package trofimovep;

import java.awt.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

class SendMailSSL {


    public static void main(String[] args) {

        JFrame jf = new JFrame();
        jf.setLayout(null);

        final JTextField fromtext = new JTextField("Ваш e-mail...");
        fromtext.setBounds(25, 10, 400, 20);
        fromtext.setVisible(true);
//        fromtext.setBackground(Color.LIGHT_GRAY);
        jf.add(fromtext);

        final JTextField passtext = new JTextField("Пароль...");
        passtext.setBounds(25, 40, 400, 20);
//        passtext.setBackground(Color.LIGHT_GRAY);
        jf.add(passtext);

        final JTextField themetext = new JTextField("Тема сообщения...");
        themetext.setBounds(25, 70, 400, 20);
//        themetext.setBackground(Color.LIGHT_GRAY);
        jf.add(themetext);

        final JTextArea messText = new JTextArea("Ваше сообщение...");
        messText.setBounds(25, 90, 400, 200);
        messText.setLineWrap(true);
        messText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messText);
        scrollPane.setBounds(25, 90, 400, 200);
        scrollPane.setVisible(true);
        jf.add(scrollPane);

        jf.setSize(450, 310);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);


    }





}//class




//    private static class SenderForm extends JPanel{
//
//        public SenderForm() {
//
//
//            setLayout(new GridLayout(1,0));
//            this.setPreferredSize(new Dimension(450,500));
//
//            JPanel frame = new JPanel();
//            frame.setLayout(null);
//            frame.setVisible(true);
//
//            JTextField sender = new JTextField("Ваш e-mail...");
//            sender.setBounds(25, 10, 400, 20);
//            sender.setVisible(true);
//            frame.add(sender);
//
//            JTextField pass = new JTextField("Ваш пароль...");
//            pass.setBounds(25, 40, 400, 20);
//            pass.setVisible(true);
//            frame.add(pass);
//
//            JTextField theme = new JTextField("Тема сообщения..");
//            theme.setBounds(25, 70, 400, 20);
//            theme.setVisible(true);
//            frame.add(theme);
//
//            JTextField mess = new JTextField("Ваше сообщение...");
//            mess.setBounds(25, 100, 400, 400);
//            mess.setVisible(true);
//            frame.add(mess);
//
//           frame.setVisible(true);
//        }
//
//
//
//
//    }






//    private void SendFunction(){
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.mail.ru");
//        props.put("mail.smtp.port", "587");
////        props.put("mail.smtp.host", "smtp.gmail.com");
////        props.put("mail.smtp.socketFactory.port", "465");
////        props.put("mail.smtp.socketFactory.class",
////                "javax.net.ssl.SSLSocketFactory");
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("trofimovep@list.ru","psss");
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("trofimovep@list.ru"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("trofimovep.mt@gmail.com"));
//            message.setSubject("Testing Subject");
//            message.setText("fuck uyo");
//
////            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }







