package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.base.UnclassifiedPerfeccionistaException;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.base.ExecuteOperation;
import io.perfeccionista.framework.pagefactory.operation.handler.base.LoadJsFunctions;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

public class SeleniumJsFunctionRepository {
    protected static final Map<String, String> jsFunctionsCache = new ConcurrentHashMap<>();

    private final RemoteWebDriver instance;
    private Set<String> loadedJsFunctions = new HashSet<>();

    public SeleniumJsFunctionRepository(RemoteWebDriver instance) {
        this.instance = instance;
        init();
    }

    protected void init() {
        loadedJsFunctions.clear();
        Set<String> jsFunctionsToLoad = new HashSet<>();
        addScriptToCache(new LoadJsFunctions());
        addScriptToCache(new ExecuteOperation());
        jsFunctionsToLoad.add("perfeccionista.js.selenium.ExecuteOperation");
        loadJsFunctions(jsFunctionsToLoad);
    }

    public void prepareOperation(WebElementOperation<?> operation) {
        Set<String> jsFunctionsToLoad = new HashSet<>();
        operation.getRequiredFunctions().forEach(endpointHandler -> {
            JsonNode functionInvocation = endpointHandler.toJson();
            String functionName = functionInvocation.get("name").asText();
            String functionPath = functionInvocation.get("script").asText();
            if (!loadedJsFunctions.contains(functionName)) {
                jsFunctionsToLoad.add(functionName);
                if (!jsFunctionsCache.containsKey(functionName)) {
                    jsFunctionsCache.put(functionName, getScript(functionPath));
                }
            }
        });
        if (!jsFunctionsToLoad.isEmpty()) {
            loadJsFunctions(jsFunctionsToLoad);
        }
    }

    // TODO: Проверка на то, что страница открыта. Если в браузере пустое окно, то window недоступен
    protected void loadJsFunctions(Set<String> jsFunctionsToLoad) {
        ObjectNode rootNode = createObjectNode();
        ArrayNode scriptsToLoadArrayNode = rootNode.putArray("scriptsToLoad");
        jsFunctionsToLoad.forEach((scriptName) -> {
            ObjectNode scriptToLoadNode = createObjectNode().put("scriptName", scriptName);
            ArrayNode scriptRowsNode = scriptToLoadNode.putArray("scriptContent");
            Arrays.stream(jsFunctionsCache.get(scriptName).split("\n")).forEachOrdered(scriptRowsNode::add);
            scriptsToLoadArrayNode.add(scriptToLoadNode);
            loadedJsFunctions.add(scriptName);
        });
        instance.executeScript(jsFunctionsCache.get("perfeccionista.js.selenium.LoadJsFunctions"), toPrettyJson(rootNode));
    }

    // TODO: минифицировать скрипт перед загрузкой и
    protected String getScript(@NotNull String resourcePath) {
        try (final InputStream is = SeleniumJsFunctionRepository.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (Objects.isNull(is)) {
                // TODO: Сделать корректное исключение
                throw new UnclassifiedPerfeccionistaException("Required js script is not found");
            }
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            // TODO: Сделать корректное исключение
            throw new UnclassifiedPerfeccionistaException(e.getMessage(), e);
        }
    }

    protected void addScriptToCache(@NotNull EndpointHandler<?> endpointHandler) {
        JsonNode functionInvocation = endpointHandler.toJson();
        String functionName = functionInvocation.get("name").asText();
        String functionPath = functionInvocation.get("script").asText();
        jsFunctionsCache.put(functionName, getScript(functionPath));
    }


    // Добавить оши
//    public void load(WebBrowserOperationType<?> jsFunction) {

//
//        Arrays.stream(jsFunctionNames).forEach(jsFunctionName -> {
//            if (!loadedJsFunctions.contains(jsFunctionName)) {
////                driverInstance.getDriverOperationExecutor().executeOperation(.................);
//                loadedJsFunctions.add(jsFunctionName);
//            }
//        });
//    }



}
