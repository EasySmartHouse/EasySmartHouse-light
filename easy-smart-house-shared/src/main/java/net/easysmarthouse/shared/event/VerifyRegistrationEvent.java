package net.easysmarthouse.shared.event;

import net.easysmarthouse.shared.domain.user.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
import java.util.Objects;

public class VerifyRegistrationEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public VerifyRegistrationEvent(String appUrl, Locale locale, User user) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyRegistrationEvent that = (VerifyRegistrationEvent) o;
        return Objects.equals(appUrl, that.appUrl) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUrl, locale, user);
    }
}
