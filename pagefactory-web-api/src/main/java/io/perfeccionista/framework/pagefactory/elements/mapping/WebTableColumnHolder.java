package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WebTableColumnHolder implements JsonSerializable {

    private final WebLocatorHolder headerLocator;
    private final Class<? extends WebBlock> headerClass;
    private final WebLocatorHolder bodyLocator;
    private final Class<? extends WebBlock> bodyClass;
    private final WebLocatorHolder footerLocator;
    private final Class<? extends WebBlock> footerClass;

    private WebTableColumnHolder(WebLocatorHolder headerLocator,
                                 Class<? extends WebBlock> headerClass,
                                 WebLocatorHolder bodyLocator,
                                 Class<? extends WebBlock> bodyClass,
                                 WebLocatorHolder footerLocator,
                                 Class<? extends WebBlock> footerClass) {
        this.headerLocator = headerLocator;
        this.headerClass = headerClass;
        this.bodyLocator = bodyLocator;
        this.bodyClass = bodyClass;
        this.footerLocator = footerLocator;
        this.footerClass = footerClass;
    }

    public static WebTableColumnHolder of(@Nullable WebLocatorHolder headerLocator,
                                          @NotNull Class<? extends WebBlock> headerClass,
                                          @Nullable WebLocatorHolder bodyLocator,
                                          @NotNull Class<? extends WebBlock> bodyClass,
                                          @Nullable WebLocatorHolder footerLocator,
                                          @NotNull Class<? extends WebBlock> footerClass) {
        return new WebTableColumnHolder(headerLocator, headerClass, bodyLocator, bodyClass, footerLocator, footerClass);
    }

    public Optional<WebLocatorHolder> getHeaderLocator() {
        return Optional.ofNullable(headerLocator);
    }

    public @NotNull Class<? extends WebBlock> getHeaderClass() {
        return headerClass;
    }

    public Optional<WebLocatorHolder> getBodyLocator() {
        return Optional.ofNullable(bodyLocator);
    }

    public @NotNull Class<? extends WebBlock> getBodyClass() {
        return bodyClass;
    }

    public Optional<WebLocatorHolder> getFooterLocator() {
        return Optional.ofNullable(footerLocator);
    }

    public @NotNull Class<? extends WebBlock> getFooterClass() {
        return footerClass;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return null;
    }

}
