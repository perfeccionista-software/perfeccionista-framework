package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.cookies.Cookie;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Browser") @Tag("Cookies")
class CookiesDispatcherTest extends AbstractWebSeleniumParallelTest {

    @Test
    void getCookieByNameTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        final long cookiesCount1 = browser.cookies()
                .getCookies()
                .count();
        assertEquals(0, cookiesCount1);

        browser.cookies()
                .addCookie(Cookie.of("Cookie1", "Cookie 1 value"));

        Cookie cookie = browser.cookies()
                .getCookieByName("Cookie1");

        assertNotNull(cookie);
    }

    @Test
    void getCookiesTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        final long cookiesCount1 = browser.cookies()
                .getCookies()
                .count();
        assertEquals(0, cookiesCount1);

        browser.cookies()
                .addCookie(Cookie.of("Cookie1", "Cookie 1 value"))
                .addCookie(Cookie.of("Cookie2", "Cookie 2 value"));

        long cookieCount = browser.cookies()
                .getCookies()
                .filter(processedCookie -> processedCookie.getName().contains("Cookie"))
                .count();

        assertEquals(2, cookieCount);
    }

    @Test
    void cleanCookiesTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        final long cookiesCount1 = browser.cookies()
                .getCookies()
                .count();
        assertEquals(0, cookiesCount1);

        browser.cookies()
                .addCookie(Cookie.of("Cookie1", "Cookie 1 value"))
                .addCookie(Cookie.of("Cookie2", "Cookie 2 value"));

        long cookieCount1 = browser.cookies()
                .getCookies()
                .count();

        assertEquals(2, cookieCount1);

        browser.cookies()
                .cleanCookies();

        long cookieCount2 = browser.cookies()
                .getCookies()
                .count();

        assertEquals(0, cookieCount2);
    }

    @Test
    void deleteCookieTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        final long cookiesCount1 = browser.cookies()
                .getCookies()
                .count();
        assertEquals(0, cookiesCount1);

        browser.cookies()
                .addCookie(Cookie.of("Cookie1", "Cookie 1 value"))
                .addCookie(Cookie.of("Cookie2", "Cookie 2 value"));

        long cookieCount1 = browser.cookies()
                .getCookies()
                .count();

        assertEquals(2, cookieCount1);

        browser.cookies()
                .deleteCookie(Cookie.of("Cookie1", "Cookie 1 value"));

        long cookieCount2 = browser.cookies()
                .getCookies()
                .filter(processedCookie -> processedCookie.getName().contains("Cookie"))
                .count();

        assertEquals(1, cookieCount2);
    }

    @Test
    void deleteCookieByNameTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        final long cookiesCount1 = browser.cookies()
                .getCookies()
                .count();
        assertEquals(0, cookiesCount1);

        browser.cookies()
                .addCookie(Cookie.of("Cookie1", "Cookie 1 value"))
                .addCookie(Cookie.of("Cookie2", "Cookie 2 value"));

        long cookieCount1 = browser.cookies()
                .getCookies()
                .count();

        assertEquals(2, cookieCount1);

        browser.cookies()
                .deleteCookieByName("Cookie1");

        long cookieCount2 = browser.cookies()
                .getCookies()
                .filter(processedCookie -> processedCookie.getName().contains("Cookie"))
                .count();

        assertEquals(1, cookieCount2);
    }
}
