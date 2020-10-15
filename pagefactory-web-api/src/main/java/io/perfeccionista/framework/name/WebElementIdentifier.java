package io.perfeccionista.framework.name;

import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface WebElementIdentifier extends JsonSerializable {

    @NotNull String getLastUsedName();

    @NotNull Method getElementMethod();

    @NotNull Class<? extends WebChildElement> getElementType();

    boolean containsName(@NotNull String name);

    boolean isNameDeprecated(@NotNull String name);

    Set<String> names();

    Stream<String> namesStream();

    WebElementIdentifier forEachName(@NotNull Consumer<String> consumer);

    WebElementIdentifier setLastUsedName(@NotNull String lastUsedName);

    WebElementIdentifier addElementsByMethodName(@NotNull Map<String, WebChildElement> elementsByMethodName,
                                                 @NotNull WebChildElement webChildElement);

    WebElementIdentifier addElementsByMethod(@NotNull Map<Method, WebChildElement> elementsByMethod,
                                             @NotNull WebChildElement webChildElement);

    WebElementIdentifier addElementsByName(@NotNull Map<String, WebChildElement> elementsByName,
                                           @NotNull WebChildElement webChildElement);

}