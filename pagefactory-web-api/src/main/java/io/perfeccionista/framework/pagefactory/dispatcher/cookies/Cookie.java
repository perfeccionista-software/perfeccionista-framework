package io.perfeccionista.framework.pagefactory.dispatcher.cookies;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.CookieValidation;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COOKIES_DOMAIN_CONTAINS_PORT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COOKIES_NAME_EMPTY;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.COOKIES_NAME_INCORRECT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.StringUtils.isBlank;
import static java.util.Objects.isNull;

public class Cookie implements JsonSerializable {

    private final String name;
    private final String value;

    private String domain = null;
    private String path = null;

    private LocalDateTime expirationDate = null;

    private boolean isSecure = false;
    private boolean isHttpOnly = false;

    private Cookie(@NotNull String name, @NotNull String value) {
        this.name = validateName(name);
        this.value = value;
        this.path = validatePath(null);
    }

    // Setters

    public static Cookie of(@NotNull String name, @NotNull String value) {
        return new Cookie(validateName(name), value);
    }

    public Cookie setDomain(@Nullable String domain) {
        this.domain = validateDomain(domain);
        return this;
    }

    public Cookie setPath(@Nullable String path) {
        this.path = validatePath(path);
        return this;
    }

    public Cookie setExpirationDate(@Nullable LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Cookie setSecure(boolean secure) {
        this.isSecure = secure;
        return this;
    }

    public Cookie setHttpOnly(boolean httpOnly) {
        this.isHttpOnly = httpOnly;
        return this;
    }

    // Getters

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getValue() {
        return value;
    }

    public @Nullable String getDomain() {
        return domain;
    }

    public @NotNull String getPath() {
        return path;
    }

    public @Nullable LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public boolean isHttpOnly() {
        return isHttpOnly;
    }

    // Validators

    protected static String validateName(String name) {
        if (isBlank(name)) {
            throw CookieValidation.exception(COOKIES_NAME_EMPTY.getMessage());
        }
        if (name.contains(";") || name.contains(" ") || name.contains("=") || name.contains(",")) {
            throw CookieValidation.exception(COOKIES_NAME_INCORRECT.getMessage())
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Cookie's name", name));
        }
        return name;
    }

    protected String validateDomain(String domain) {
        if (isNull(domain)) {
            return null;
        }
        String validatableDomain = domain.split(":")[0];
        if (validatableDomain.contains(":")) {
            throw CookieValidation.exception(COOKIES_DOMAIN_CONTAINS_PORT.getMessage())
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Cookie's domain", validatableDomain));
        }
        return validatableDomain;
    }

    protected String validatePath(String path) {
        return isBlank(path) ? "/" : path;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", name)
                .put("value", value)
                .put("domain", domain)
                .put("path", path)
                .put("expirationDate", Objects.isNull(expirationDate) ? "null" : expirationDate.toString())
                .put("isSecure", isSecure)
                .put("isHttpOnly", isHttpOnly);
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) o;
        if (!name.equals(cookie.name)) {
            return false;
        }
        return value.equals(cookie.value);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
