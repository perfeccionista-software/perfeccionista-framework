package io.perfeccionista.framework.name;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.ElementNameNotFound;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NAME_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MappedMobileBlockIdentifier implements MobileElementIdentifier {

    private final Class<? extends MobileBlock> mappedBlockClass;
    private final Map<String, Boolean> names;
    private String lastUsedName = null;

    private MappedMobileBlockIdentifier(Class<? extends MobileBlock> mappedBlockClass, Map<String, Boolean> names) {
        this.mappedBlockClass = mappedBlockClass;
        this.names = names;
    }

    public static MappedMobileBlockIdentifier of(@NotNull Class<? extends MobileBlock> mappedBlockClass) {
        return new MappedMobileBlockIdentifier(mappedBlockClass, new HashMap<>());
    }

    public static MappedMobileBlockIdentifier of(@NotNull Class<? extends MobileBlock> mappedBlockClass, @NotNull Map<String, Boolean> names) {
        return new MappedMobileBlockIdentifier(mappedBlockClass, names);
    }

    @Override
    public @NotNull String getLastUsedName() {
        if (Objects.isNull(lastUsedName)) {
            return mappedBlockClass.getCanonicalName();
        }
        return lastUsedName;
    }

    @Override
    public @NotNull Method getElementMethod() {
        throw new UnsupportedOperationException("MappedBlock can't be called by method because it's declared by annotation");
    }

    @Override
    public @NotNull Class<? extends MobileBlock> getElementType() {
        return mappedBlockClass;
    }

    @Override
    public boolean containsName(@NotNull String name) {
        return names.containsKey(name);
    }

    @Override
    public boolean isNameDeprecated(@NotNull String name) {
        if (!names.containsKey(name)) {
            throw ElementNameNotFound.exception(ELEMENT_NAME_NOT_FOUND.getMessage(name));
        }
        return names.get(name);
    }

    @Override
    public Set<String> names() {
        return names.keySet();
    }

    @Override
    public Stream<String> namesStream() {
        return names().stream();
    }

    @Override
    public MappedMobileBlockIdentifier forEachName(@NotNull Consumer<String> consumer) {
        names().forEach(consumer);
        return this;
    }

    @Override
    public MappedMobileBlockIdentifier setLastUsedName(@NotNull String lastUsedName) {
        this.lastUsedName = lastUsedName;
        return this;
    }

    @Override
    public MappedMobileBlockIdentifier addElementsByMethodName(@NotNull Map<String, MobileChildElement> elementsByMethodName,
                                                               @NotNull MobileChildElement mobileChildElement) {
        return this;
    }

    @Override
    public MappedMobileBlockIdentifier addElementsByMethod(@NotNull Map<Method, MobileChildElement> elementsByMethod,
                                                           @NotNull MobileChildElement mobileChildElement) {
        return this;
    }

    @Override
    public MappedMobileBlockIdentifier addElementsByName(@NotNull Map<String, MobileChildElement> elementsByName,
                                                         @NotNull MobileChildElement mobileChildElement) {
        names.forEach((name, deprecated) -> elementsByName.put(name, mobileChildElement));
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode();
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}

