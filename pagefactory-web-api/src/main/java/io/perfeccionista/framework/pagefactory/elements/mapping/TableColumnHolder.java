package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TableColumnHolder {

    private final WebLocatorHolder headerLocator;
    private final Class<? extends WebMappedBlock> headerClass;
    private final WebLocatorHolder bodyLocator;
    private final Class<? extends WebMappedBlock> bodyClass;
    private final WebLocatorHolder footerLocator;
    private final Class<? extends WebMappedBlock> footerClass;

    private TableColumnHolder(WebLocatorHolder headerLocator,
                              Class<? extends WebMappedBlock> headerClass,
                              WebLocatorHolder bodyLocator,
                              Class<? extends WebMappedBlock> bodyClass,
                              WebLocatorHolder footerLocator,
                              Class<? extends WebMappedBlock> footerClass) {
        this.headerLocator = headerLocator;
        this.headerClass = headerClass;
        this.bodyLocator = bodyLocator;
        this.bodyClass = bodyClass;
        this.footerLocator = footerLocator;
        this.footerClass = footerClass;
    }

    public static TableColumnHolder of(@Nullable WebLocatorHolder headerLocator,
                                       @NotNull Class<? extends WebMappedBlock> headerClass,
                                       @Nullable WebLocatorHolder bodyLocator,
                                       @NotNull Class<? extends WebMappedBlock> bodyClass,
                                       @Nullable WebLocatorHolder footerLocator,
                                       @NotNull Class<? extends WebMappedBlock> footerClass) {
        return new TableColumnHolder(headerLocator, headerClass, bodyLocator, bodyClass, footerLocator, footerClass);
    }

    public Optional<WebLocatorHolder> getHeaderLocator() {
        return Optional.ofNullable(headerLocator);
    }

    public @NotNull Class<? extends WebMappedBlock> getHeaderClass() {
        return headerClass;
    }

    public Optional<WebLocatorHolder> getBodyLocator() {
        return Optional.ofNullable(bodyLocator);
    }

    public @NotNull Class<? extends WebMappedBlock> getBodyClass() {
        return bodyClass;
    }

    public Optional<WebLocatorHolder> getFooterLocator() {
        return Optional.ofNullable(footerLocator);
    }

    public @NotNull Class<? extends WebMappedBlock> getFooterClass() {
        return footerClass;
    }

}
