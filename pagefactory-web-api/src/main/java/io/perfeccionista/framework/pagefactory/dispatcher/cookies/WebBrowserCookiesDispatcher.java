package io.perfeccionista.framework.pagefactory.dispatcher.cookies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public interface WebBrowserCookiesDispatcher {

	@Nullable Cookie getCookieByName(@NotNull String name);

	@NotNull Stream<Cookie> getCookies();

	WebBrowserCookiesDispatcher addCookie(@NotNull Cookie cookie);

	WebBrowserCookiesDispatcher cleanCookies();

	WebBrowserCookiesDispatcher deleteCookie(@NotNull Cookie cookie);

	WebBrowserCookiesDispatcher deleteCookieByName(@NotNull String name);

}
