package trofimovep;


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

class Send {


    protected static void send() {

        String domens = "<html>\n" +
                "<head>\n"+

                "<style>\n" +

                "table {\n" +
                "    font-family: arial, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "    border: 1px solid #dddddd;\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "    background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table>\n" +
                "<caption>Отправка сообщений возможна только со следующих доменов:</caption>" +
                "  <tr>\n" +
                "    <th>mail.ru:</th>\n" +
                "    <th>gmail.com:</th>\n" +
                "    <th>yandex.ru:</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>@mail.ru</td>\n" +
                "    <td>@gmail.com</td>\n" +
                "    <td>@yandex.ru</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>@list.ru</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>@bk.ru</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>@inbox.ru</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        JPanel panel = new JPanel();
        JLabel lab = new JLabel();
        lab.setText(domens);
        panel.add(lab);


        int result = JOptionPane.showConfirmDialog(null, panel,"Внимание!", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {

            JFrame jf = new JFrame();
            jf.setLayout(null);

        /*add a label for password*/

            final JTextField fromtext = new JTextField();
            fromtext.setToolTipText("Ваш e-mail...");
            fromtext.setBounds(25, 10, 400, 20);
            fromtext.setVisible(true);
            jf.add(fromtext);

            final JPasswordField passtext = new JPasswordField();
            passtext.setEchoChar((char) 0);
            passtext.setText("Пароль...");
            passtext.setBounds(25, 40, 400, 20);
            jf.add(passtext);
            passtext.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (String.valueOf(passtext.getPassword()).equals("Пароль...") == false) {
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
                    SendFunction(fromtext.getText(), String.valueOf(passtext.getPassword()), themetext.getText(), messText.getText());
                    JOptionPane.showMessageDialog(null, "Сообщение успешно отправлено");
                } catch (Exception ex) {
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

    }


    private static void SendFunction(String email, String password, String theme, String mess){

        System.out.println(password);
        String host = null;
        String port = "465";


        String serv = email.replaceAll(".+@","");

        if(serv.equals("mail.ru") | serv.equals("list.ru") | serv.equals("bk.ru") | serv.equals("inbox.ru")){
            host = "smtp.mail.ru";
            System.out.println(host);
        }
        else if(serv.equals("gmail.com")){
            host = "smtp.gmail.com";
            System.out.println(host);
        }
        else if(serv.equals("yandex.ru")) {
            System.out.println(host);
            host = "smtp.yandex.ru";
        }

        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        System.out.println("Процесс аутефинкации...");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
//                        System.out.println(new PasswordAuthentication(email,password));
                        return new PasswordAuthentication(email,password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("trofimovep.mt@gmail.com"));
            message.setSubject(theme);
            message.setText("fuck uyo");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}//class







