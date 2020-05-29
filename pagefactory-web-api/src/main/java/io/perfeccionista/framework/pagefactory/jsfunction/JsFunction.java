package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URL;
import java.util.function.Function;

import static io.perfeccionista.framework.utils.FileUtils.readFile;

/**
 * @param <R>
 */
public interface JsFunction<R> {

    Function<Object, R> getConverter();

    /**
     * JSON формат в виде:
     * <pre>
     * {
     *     "name": "scriptName",
     *     "args": ["arg1", "arg2"]
     * }
     * </pre>
     * @return
     */
    ObjectNode getJsFunctionInvocation();

    String getScriptDestination();

    String getScriptName();

    default String getScript() {
        // TODO: минифицировать скрипт перед загрузкой
        URL url = getClass().getClassLoader().getResource(getScriptDestination());
        if (url == null) {
            // TODO: Сделать корректное исключение
            throw new RuntimeException();
        }
        return readFile(url);
    }

    default String[] getScriptRows() {
        return getScript().split("\n");
    }

}
