package io.perfeccionista.framework.name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO
public class WebElementIdentifier {

    private final Method elementMethod;
    private final Map<String, Boolean> names;
    private String lastUsedName = null;

    private WebElementIdentifier(Map<String, Boolean> names, Method elementMethod) {
        this.names = names;
        this.elementMethod = elementMethod;
    }

    public static WebElementIdentifier of(Map<String, Boolean> names, Method elementMethod) {
        return new WebElementIdentifier(names, elementMethod);
    }

    public Method getElementMethod() {
        return elementMethod;
    }

    public void setLastUsedName(String lastUsedName) {
        this.lastUsedName = lastUsedName;
    }

    public String getLastUsedName() {
        return lastUsedName == null ? elementMethod.getName() : lastUsedName;
    }

    public boolean containsName(String name) {
        return names.containsKey(name);
    }

    public boolean isNameDeprecated(String name) {
        // TODO: Проверять что такое имя в элементе есть
        return names.get(name);
    }

    public Set<String> names() {
        return Set.copyOf(names.keySet());
    }

    public Stream<String> namesStream() {
        return names().stream();
    }

    public void forEachName(Consumer<String> consumer) {
        names().forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("elementMethod", elementMethod.toString())
                .put("lastUsedName", lastUsedName);
        ArrayNode namesNode = rootNode.putArray("names");
        forEachName(namesNode::add);
        return rootNode;
    }

    public String toString() {
        return toJson().toPrettyString();
    }

}
