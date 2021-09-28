package by.kagan.businesslayer.auth.token.verification.listener;

import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RegistraionListener implements ApplicationListener<AfterCompleteRegistrationEvent> {

    @Autowired
    private AuthService authService;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(AfterCompleteRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(AfterCompleteRegistrationEvent event){
        User user = event.getUser();

        String token = UUID.randomUUID().toString();
        authService.createVerificationToken(user, token);

        String recAdress = user.getEmail();
        String subject = "Confirm Registration";
        String confirmUrl = event.getAppUrl() + "/signupconfirmation?token=" + token;

        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setFrom("daniil.kahan002@gmail.com");
        confirmationMessage.setTo(recAdress);
        confirmationMessage.setSubject(subject);
        confirmationMessage.setText("http://localhost:7777" + confirmUrl);

        mailSender.send(confirmationMessage);

    }
}
