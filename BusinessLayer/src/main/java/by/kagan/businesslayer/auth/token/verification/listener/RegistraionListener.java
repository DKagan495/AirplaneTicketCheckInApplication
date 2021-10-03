package by.kagan.businesslayer.auth.token.verification.listener;

import by.kagan.businesslayer.auth.token.jwt.JwtProvider;
import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class RegistraionListener implements ApplicationListener<AfterCompleteRegistrationEvent> {

    private final JavaMailSender mailSender;

    private final JwtProvider provider;

    @Override
    public void onApplicationEvent(AfterCompleteRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(AfterCompleteRegistrationEvent event){
        User user = event.getUser();

        String token = provider.getToken(user.getEmail());

        String recAdress = user.getEmail();
        String subject = "Confirm Registration";
        String confirmUrl = event.getAppUrl() + "/api/signup/verify?token=" + token;

        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setFrom("daniil.kahan002@gmail.com");
        confirmationMessage.setTo(recAdress);
        confirmationMessage.setSubject(subject);
        confirmationMessage.setText("Hello, " + user.getFirstName() + " " + user.getLastName() + "!\nIf you want to activate your account, you should use link below.\n" + "http://localhost:7777" + confirmUrl);

        mailSender.send(confirmationMessage);

    }
}
