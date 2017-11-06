package trofimovep;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class SendMailSSL {


    public static void main(String[] args) {


        JFrame jf = new JFrame();
        jf.setLayout(null);

        /*add a label for password*/

        final JTextField fromtext = new JTextField();
        fromtext.setToolTipText("Ваш e-mail...");
        fromtext.setBounds(25, 10, 400, 20);
        fromtext.setVisible(true);
        jf.add(fromtext);

        final JPasswordField passtext = new JPasswordField();
        passtext.setEchoChar((char)0);
        passtext.setText("Пароль...");
        passtext.setBounds(25, 40, 400, 20);
        jf.add(passtext);
        passtext.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(String.valueOf(passtext.getPassword()).equals("Пароль...")==false) {
                    passtext.setEchoChar('*');
                } else {

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        final JTextField themetext = new JTextField("Тема сообщения...");
        themetext.setBounds(25, 70, 400, 20);
        jf.add(themetext);

        final JTextArea messText = new JTextArea("Ваше сообщение...");
        messText.setBounds(25, 90, 400, 200);
        messText.setLineWrap(true);
        messText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messText);
        scrollPane.setBounds(25, 90, 400, 200);
        scrollPane.setVisible(true);
        jf.add(scrollPane);

        JButton send = new JButton("Отправить");
        send.setBounds(65, 300, 150, 30);
        send.setVisible(true);
        jf.add(send);
        send.addActionListener(e -> {
            try {
                SendFunction(fromtext.getText(), passtext.getText(), themetext.getText(), messText.getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }

        });

        JButton cancel = new JButton("Отмена");
        cancel.setBounds(240, 300, 150, 30);
        cancel.setVisible(true);
        jf.add(cancel);
        cancel.addActionListener(e -> jf.dispose());

        jf.setSize(450, 350);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);



    }


    private static void SendFunction(String email, String password, String theme, String mess){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("trofimovep@list.ru","psss");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trofimovep@list.ru"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("trofimovep.mt@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("fuck uyo");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}//class







