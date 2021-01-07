package io.perfeccionista.framework.pagefactory.browser.cookies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public interface CookiesDispatcher {

	@Nullable Cookie getCookieByName(@NotNull String name);

	@NotNull Stream<Cookie> getCookies();

	CookiesDispatcher addCookie(@NotNull Cookie cookie);

	CookiesDispatcher cleanCookies();

	CookiesDispatcher deleteCookie(@NotNull Cookie cookie);

	CookiesDispatcher deleteCookieByName(@NotNull String name);

}
