package io.perfeccionista.framework.pagefactory.dispatcher.cookies;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class SeleniumCookieConverter {

    public static @Nullable
    Cookie createPerfeccionistaCookie(@Nullable org.openqa.selenium.Cookie cookie) {
        if (Objects.isNull(cookie)) {
            return null;
        }
        Cookie convertedCookie = Cookie.of(cookie.getName(), cookie.getValue())
                .setDomain(cookie.getDomain())
                .setPath(cookie.getPath())
                .setSecure(cookie.isSecure())
                .setHttpOnly(cookie.isHttpOnly());
        if (Objects.nonNull(cookie.getExpiry())) {
            convertedCookie.setExpirationDate(LocalDateTime.ofInstant(cookie.getExpiry().toInstant(), ZoneId.systemDefault()));
        }
        return convertedCookie;
    }

    public static @Nullable org.openqa.selenium.Cookie createSeleniumCookie(Cookie cookie) {
        if (Objects.isNull(cookie)) {
            return null;
        }
        return new org.openqa.selenium.Cookie(cookie.getName(),
                cookie.getValue(),
                cookie.getDomain(),
                cookie.getPath(),
                Objects.isNull(cookie.getExpirationDate()) ? null : Date.from(cookie.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant()),
                cookie.isSecure(),
                cookie.isHttpOnly());
    }

}
