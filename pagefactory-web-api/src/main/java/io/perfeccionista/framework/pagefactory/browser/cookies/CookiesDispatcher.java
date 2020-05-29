package io.perfeccionista.framework.pagefactory.browser.cookies;

import java.util.stream.Stream;

public interface CookiesDispatcher {

	Cookie getCookieByName(String name);

	Stream<Cookie> getCookies();

	CookiesDispatcher addCookie(Cookie cookie);

	CookiesDispatcher cleanCookies();

	CookiesDispatcher deleteCookie(Cookie cookie);

	CookiesDispatcher deleteCookieByName(String name);

}
