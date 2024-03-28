package it.contrader.inbook.service;

import it.contrader.inbook.converter.AuthService;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import java.security.SecureRandom;

@Service
public class RetrievePasswordService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;

    public static String generateCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void sendEmail(String recipientEmail, String generatedCode) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.outlook.com");
        properties.put("mail.smtp.port", "587");

        String username = "inbook@outlook.it";
        String password = "testtest123";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Conferma ripristino password InBook");

        String emailBody = "Gentile utente,\n\n";
        emailBody += "Ecco il codice di conferma per il ripristino della password: " + generatedCode + "\n\n";
        emailBody += "Per confermare il ripristino della password, utilizza questo codice nell'applicazione.\n\n";
        emailBody += "Grazie,\n";
        emailBody += "Il tuo team di supporto";
        message.setText(emailBody);

        Transport.send(message);
        System.out.println("Email inviata con successo a " + recipientEmail);
    }

    public String changePassword(String email){
        String code = generateCode();
        try{
        sendEmail(email, code);
        }
        catch (MessagingException ex){
            throw new NotExistException("Email non inviata");
        }
        return code;
    }

    public UserDTO confirmCode(String generatedCode, String userCode, String newPass, String email){
        if (generatedCode.equals(userCode)){
            UserDTO dto = userService.getByEmail(email);
            dto.setPassword(encoder.encode(newPass));
            return userService.save(dto);
        }

        return null;
    }

}