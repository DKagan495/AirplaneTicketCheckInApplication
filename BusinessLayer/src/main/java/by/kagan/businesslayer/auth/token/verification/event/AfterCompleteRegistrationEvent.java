package by.kagan.businesslayer.auth.token.verification.event;

import by.kagan.businesslayer.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class AfterCompleteRegistrationEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public AfterCompleteRegistrationEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
