package logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CustomMailAppender extends AppenderSkeleton {
    private String addressFrom = "";
    private String addressTo = "";
    private String username = "";
    private String password = "";
    private String smtp = "";
    private String smtpPort = "";

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void append(LoggingEvent event) {
        String subj;
        String body = layout.format(event);

        if (layout instanceof SubjectLayoutable) {
            subj = ((SubjectLayoutable) layout).formatSubject(event);
        } else {
            subj = "CARSHOP:" + event.getLevel();
        }

        sendMail(subj, body);
    }

    private void sendMail(String subj, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(addressFrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(addressTo));
            message.setSubject(subj);
            message.setText(body);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
