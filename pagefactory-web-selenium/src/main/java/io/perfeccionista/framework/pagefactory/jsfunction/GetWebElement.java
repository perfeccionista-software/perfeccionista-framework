package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetWebElement implements JsFunction<WebElement> {

    @Override
    public Function<Object, WebElement> getConverter() {
        return object -> (RemoteWebElement) object;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetWebElement";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetWebElement.js";
    }

}
