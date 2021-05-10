package io.perfeccionista.framework.name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementNameNotFound;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NAME_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileChildElementIdentifier implements MobileElementIdentifier {

    private final Method elementMethod;
    private final Map<String, Boolean> names;
    private String lastUsedName = null;

    private MobileChildElementIdentifier(Map<String, Boolean> names, Method elementMethod) {
        this.names = names;
        this.elementMethod = elementMethod;
    }

    public static MobileChildElementIdentifier of(@NotNull Map<String, Boolean> names, @NotNull Method elementMethod) {
        return new MobileChildElementIdentifier(names, elementMethod);
    }

    @Override
    public @NotNull String getLastUsedName() {
        return lastUsedName == null ? elementMethod.getName() : lastUsedName;
    }

    @Override
    public @NotNull Method getElementMethod() {
        return elementMethod;
    }

    @Override
    public @NotNull Class<? extends MobileChildElement> getElementType() {
        //noinspection unchecked
        return (Class<? extends MobileChildElement>) elementMethod.getReturnType();
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
        return Set.copyOf(names.keySet());
    }

    @Override
    public Stream<String> namesStream() {
        return names().stream();
    }

    @Override
    public MobileChildElementIdentifier forEachName(@NotNull Consumer<String> consumer) {
        names().forEach(consumer);
        return this;
    }

    @Override
    public MobileChildElementIdentifier setLastUsedName(@NotNull String lastUsedName) {
        this.lastUsedName = lastUsedName;
        return this;
    }

    @Override
    public MobileChildElementIdentifier addElementsByMethodName(@NotNull Map<String, MobileChildElement> elementsByMethodName,
                                                                @NotNull MobileChildElement mobileChildElement) {
        elementsByMethodName.put(elementMethod.getName(), mobileChildElement);
        return this;
    }

    @Override
    public MobileChildElementIdentifier addElementsByMethod(@NotNull Map<Method, MobileChildElement> elementsByMethod,
                                                            @NotNull MobileChildElement mobileChildElement) {
        elementsByMethod.put(elementMethod, mobileChildElement);
        return this;
    }

    @Override
    public MobileChildElementIdentifier addElementsByName(@NotNull Map<String, MobileChildElement> elementsByName,
                                                          @NotNull MobileChildElement mobileChildElement) {
        names.forEach((name, deprecated) -> elementsByName.put(name, mobileChildElement));
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("elementMethod", elementMethod.toString())
                .put("lastUsedName", lastUsedName);
        ArrayNode namesNode = rootNode.putArray("names");
        forEachName(namesNode::add);
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}

