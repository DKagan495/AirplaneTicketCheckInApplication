package by.kagan.businesslayer.auth.token.verification.listener;

import by.kagan.businesslayer.auth.token.verification.event.AfterCompleteRegistrationEvent;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RegistraionListener implements ApplicationListener<AfterCompleteRegistrationEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(AfterCompleteRegistrationEvent event) {
        System.out.println("me");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(AfterCompleteRegistrationEvent event){
        System.out.println("boo");
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        String recAdress = user.getEmail();
        System.out.println(recAdress);
        String subject = "Confirm Registration";
        String confirmUrl = event.getAppUrl() + "/signupconfirmation?token=" + token;
       // String message
         //       = messageSource.getMessage("{registration.success}", null, event.getLocale());
        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setFrom("daniil.kahan002@gmail.com");
        confirmationMessage.setTo(recAdress);
        confirmationMessage.setSubject(subject);
        confirmationMessage.setText("http://localhost:7777/" + confirmUrl);
        mailSender.send(confirmationMessage);

    }
}
