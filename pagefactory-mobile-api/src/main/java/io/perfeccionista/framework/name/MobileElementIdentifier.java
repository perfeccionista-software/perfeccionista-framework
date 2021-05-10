package io.perfeccionista.framework.name;

import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface MobileElementIdentifier extends JsonSerializable {

    @NotNull String getLastUsedName();

    @NotNull Method getElementMethod();

    @NotNull Class<? extends MobileChildElement> getElementType();

    boolean containsName(@NotNull String name);

    boolean isNameDeprecated(@NotNull String name);

    Set<String> names();

    Stream<String> namesStream();

    MobileElementIdentifier forEachName(@NotNull Consumer<String> consumer);

    MobileElementIdentifier setLastUsedName(@NotNull String lastUsedName);

    MobileElementIdentifier addElementsByMethodName(@NotNull Map<String, MobileChildElement> elementsByMethodName,
                                                    @NotNull MobileChildElement mobileChildElement);

    MobileElementIdentifier addElementsByMethod(@NotNull Map<Method, MobileChildElement> elementsByMethod,
                                                @NotNull MobileChildElement mobileChildElement);

    MobileElementIdentifier addElementsByName(@NotNull Map<String, MobileChildElement> elementsByName,
                                              @NotNull MobileChildElement mobileChildElement);

}
