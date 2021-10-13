package Land.Development.Agency.myboard.mailservice;

import Land.Development.Agency.myboard.WebUser.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private WebUserRepository webUserRepository;
    @Value("${spring.mail.username}")
    String ServerEmail;


    public void sendConfirmMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ServerEmail);
        message.setTo(email);
        message.setSubject("This is a confirm email");
        message.setText("http://127.0.0.1/register/" + webUserRepository.findByEmail(email).get().getId());

        mailSender.send(message);
    }
}
