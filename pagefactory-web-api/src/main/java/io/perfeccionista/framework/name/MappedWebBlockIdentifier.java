package io.perfeccionista.framework.name;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.MappedWebBlockIdentifierCall;
import io.perfeccionista.framework.exceptions.WebElementNameNotFound;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.MAPPED_WEB_BLOCK_CALLED_BY_METHOD;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_ELEMENT_NAME_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MappedWebBlockIdentifier implements WebElementIdentifier {

    private final Class<? extends WebBlock> mappedBlockClass;
    private final Map<String, Boolean> names;
    private String lastUsedName = null;

    private MappedWebBlockIdentifier(Class<? extends WebBlock> mappedBlockClass, Map<String, Boolean> names) {
        this.mappedBlockClass = mappedBlockClass;
        this.names = names;
    }

    public static MappedWebBlockIdentifier of(@NotNull Class<? extends WebBlock> mappedBlockClass) {
        return new MappedWebBlockIdentifier(mappedBlockClass, new HashMap<>());
    }

    public static MappedWebBlockIdentifier of(@NotNull Class<? extends WebBlock> mappedBlockClass, @NotNull Map<String, Boolean> names) {
        return new MappedWebBlockIdentifier(mappedBlockClass, names);
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
        throw MappedWebBlockIdentifierCall.exception(MAPPED_WEB_BLOCK_CALLED_BY_METHOD.getMessage());
    }

    @Override
    public @NotNull Class<? extends WebBlock> getElementType() {
        return mappedBlockClass;
    }

    @Override
    public boolean containsName(@NotNull String name) {
        return names.containsKey(name);
    }

    @Override
    public boolean isNameDeprecated(@NotNull String name) {
        if (!names.containsKey(name)) {
            throw WebElementNameNotFound.exception(WEB_ELEMENT_NAME_NOT_FOUND.getMessage(name));
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
    public MappedWebBlockIdentifier forEachName(@NotNull Consumer<String> consumer) {
        names().forEach(consumer);
        return this;
    }

    @Override
    public MappedWebBlockIdentifier setLastUsedName(@NotNull String lastUsedName) {
        this.lastUsedName = lastUsedName;
        return this;
    }

    @Override
    public MappedWebBlockIdentifier addElementsByMethodName(@NotNull Map<String, WebChildElement> elementsByMethodName,
                                                             @NotNull WebChildElement webChildElement) {
        return this;
    }

    @Override
    public MappedWebBlockIdentifier addElementsByMethod(@NotNull Map<Method, WebChildElement> elementsByMethod,
                                                         @NotNull WebChildElement webChildElement) {
        return this;
    }

    @Override
    public MappedWebBlockIdentifier addElementsByName(@NotNull Map<String, WebChildElement> elementsByName,
                                                       @NotNull WebChildElement webChildElement) {
        names.forEach((name, deprecated) -> elementsByName.put(name, webChildElement));
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
