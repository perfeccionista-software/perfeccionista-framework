package io.perfeccionista.framework.pagefactory.browser.executor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.pagefactory.jsfunction.base.ExecuteOperation;
import io.perfeccionista.framework.pagefactory.jsfunction.base.LoadJsFunctions;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

public class SeleniumJsFunctionRepository {

    private final RemoteWebDriver instance;
    private Map<String, String[]> loadedJsFunctions = new HashMap<>();

    public SeleniumJsFunctionRepository(RemoteWebDriver instance) {
        this.instance = instance;
        init();
    }

    protected void init() {
        Map<String, String[]> jsFunctionsToLoad = new HashMap<>();
        ExecuteOperation executeOperation = new ExecuteOperation();
        jsFunctionsToLoad.put(executeOperation.getScriptName(), executeOperation.getScriptRows());
        loadJsFunctions(jsFunctionsToLoad);
    }

    public void prepareOperation(JsOperation<?> operation) {
        Map<String, String[]> jsFunctionsToLoad = new HashMap<>();
        operation.getRequiredFunctions().forEach(jsFunction -> {
            if (!loadedJsFunctions.containsKey(jsFunction.getScriptName())) {
                jsFunctionsToLoad.put(jsFunction.getScriptName(), jsFunction.getScriptRows());
            }
        });
        if (!jsFunctionsToLoad.isEmpty()) {
            loadJsFunctions(jsFunctionsToLoad);
        }
    }

    // TODO: Проверка на то, что страница открыта. Если в браузере пустое окно, то window недоступен
    protected void loadJsFunctions(Map<String, String[]> jsFunctionsToLoad) {
        ObjectNode rootNode = createObjectNode();
        ArrayNode scriptsToLoadArrayNode = rootNode.putArray("scriptsToLoad");
        jsFunctionsToLoad.forEach((scriptName, scriptRows) -> {
            ObjectNode scriptToLoadNode = createObjectNode().put("scriptName", scriptName);
            ArrayNode scriptRowsNode = scriptToLoadNode.putArray("scriptContent");
            Arrays.stream(scriptRows).forEachOrdered(scriptRowsNode::add);
            scriptsToLoadArrayNode.add(scriptToLoadNode);
            loadedJsFunctions.put(scriptName, scriptRows);
        });
        instance.executeScript(new LoadJsFunctions().getScript(), toPrettyJson(rootNode));
    }



    // Добавить оши
    public void load(JsFunction<?> jsFunction) {

//
//        Arrays.stream(jsFunctionNames).forEach(jsFunctionName -> {
//            if (!loadedJsFunctions.contains(jsFunctionName)) {
////                driverInstance.getDriverOperationExecutor().executeOperation(.................);
//                loadedJsFunctions.add(jsFunctionName);
//            }
//        });
    }



}
