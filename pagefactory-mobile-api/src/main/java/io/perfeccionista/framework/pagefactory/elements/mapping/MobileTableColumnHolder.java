package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MobileTableColumnHolder implements JsonSerializable {

    private final MobileLocatorHolder headerLocator;
    private final Class<? extends MobileBlock> headerClass;
    private final MobileLocatorHolder bodyLocator;
    private final Class<? extends MobileBlock> bodyClass;
    private final MobileLocatorHolder footerLocator;
    private final Class<? extends MobileBlock> footerClass;

    private MobileTableColumnHolder(MobileLocatorHolder headerLocator,
                                    Class<? extends MobileBlock> headerClass,
                                    MobileLocatorHolder bodyLocator,
                                    Class<? extends MobileBlock> bodyClass,
                                    MobileLocatorHolder footerLocator,
                                    Class<? extends MobileBlock> footerClass) {
        this.headerLocator = headerLocator;
        this.headerClass = headerClass;
        this.bodyLocator = bodyLocator;
        this.bodyClass = bodyClass;
        this.footerLocator = footerLocator;
        this.footerClass = footerClass;
    }

    public static MobileTableColumnHolder of(@Nullable MobileLocatorHolder headerLocator,
                                             @NotNull Class<? extends MobileBlock> headerClass,
                                             @Nullable MobileLocatorHolder bodyLocator,
                                             @NotNull Class<? extends MobileBlock> bodyClass,
                                             @Nullable MobileLocatorHolder footerLocator,
                                             @NotNull Class<? extends MobileBlock> footerClass) {
        return new MobileTableColumnHolder(headerLocator, headerClass, bodyLocator, bodyClass, footerLocator, footerClass);
    }

    public Optional<MobileLocatorHolder> getHeaderLocator() {
        return Optional.ofNullable(headerLocator);
    }

    public @NotNull Class<? extends MobileBlock> getHeaderClass() {
        return headerClass;
    }

    public Optional<MobileLocatorHolder> getBodyLocator() {
        return Optional.ofNullable(bodyLocator);
    }

    public @NotNull Class<? extends MobileBlock> getBodyClass() {
        return bodyClass;
    }

    public Optional<MobileLocatorHolder> getFooterLocator() {
        return Optional.ofNullable(footerLocator);
    }

    public @NotNull Class<? extends MobileBlock> getFooterClass() {
        return footerClass;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return null;
    }

}
