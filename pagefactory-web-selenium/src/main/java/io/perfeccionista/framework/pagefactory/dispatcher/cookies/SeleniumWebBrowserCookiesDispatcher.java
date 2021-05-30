package io.perfeccionista.framework.pagefactory.dispatcher.cookies;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nullable;
import java.util.stream.Stream;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_ADD_COOKIE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_CLEAN_COOKIES_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_DELETE_COOKIE_BY_NAME_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_DELETE_COOKIE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_COOKIES_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_COOKIE_BY_NAME_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.cookies.SeleniumCookieConverter.createPerfeccionistaCookie;
import static io.perfeccionista.framework.pagefactory.dispatcher.cookies.SeleniumCookieConverter.createSeleniumCookie;

public class SeleniumWebBrowserCookiesDispatcher implements WebBrowserCookiesDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final WebExceptionMapper exceptionMapper;

    public SeleniumWebBrowserCookiesDispatcher(Environment environment, RemoteWebDriver instance, WebExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public @Nullable
    Cookie getCookieByName(@NotNull String name) {
        return runCheck(environment, getterInvocation(BROWSER_GET_COOKIE_BY_NAME_METHOD, name), () ->
                exceptionMapper.map(() -> createPerfeccionistaCookie(instance.manage().getCookieNamed(name)))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());

    }

    @Override
    public @NotNull Stream<Cookie> getCookies() {
        return runCheck(environment, getterInvocation(BROWSER_GET_COOKIES_METHOD), () ->
                exceptionMapper.map(() -> instance.manage()
                        .getCookies().stream()
                        .map(SeleniumCookieConverter::createPerfeccionistaCookie))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public SeleniumWebBrowserCookiesDispatcher addCookie(@NotNull Cookie cookie) {
        runCheck(environment, getterInvocation(BROWSER_ADD_COOKIE_METHOD, cookie), () ->
                exceptionMapper.map(() -> instance.manage()
                        .addCookie(createSeleniumCookie(cookie)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWebBrowserCookiesDispatcher cleanCookies() {
        runCheck(environment, getterInvocation(BROWSER_CLEAN_COOKIES_METHOD), () ->
                exceptionMapper.map(() -> instance.manage()
                        .deleteAllCookies())
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWebBrowserCookiesDispatcher deleteCookie(@NotNull Cookie cookie) {
        runCheck(environment, getterInvocation(BROWSER_DELETE_COOKIE_METHOD, cookie), () ->
                exceptionMapper.map(() -> instance.manage()
                        .deleteCookie(createSeleniumCookie(cookie)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWebBrowserCookiesDispatcher deleteCookieByName(@NotNull String name) {
        runCheck(environment, getterInvocation(BROWSER_DELETE_COOKIE_BY_NAME_METHOD, name), () ->
                exceptionMapper.map(() -> instance.manage()
                        .deleteCookieNamed(name))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

}