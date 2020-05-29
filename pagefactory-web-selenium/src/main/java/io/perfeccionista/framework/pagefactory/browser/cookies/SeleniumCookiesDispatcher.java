package io.perfeccionista.framework.pagefactory.browser.cookies;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.stream.Stream;

public class SeleniumCookiesDispatcher implements CookiesDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumCookiesDispatcher(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public Cookie getCookieByName(String name) {
        return null;
    }

    @Override
    public Stream<Cookie> getCookies() {
        return null;
    }

    @Override
    public CookiesDispatcher addCookie(Cookie cookie) {
        return null;
    }

    @Override
    public CookiesDispatcher cleanCookies() {
        return null;
    }

    @Override
    public CookiesDispatcher deleteCookie(Cookie cookie) {
        return null;
    }

    @Override
    public CookiesDispatcher deleteCookieByName(String name) {
        return null;
    }

}
