package io.perfeccionista.framework.pagefactory.js;

import org.openqa.selenium.WebElement;

public class GetWebElement implements JsFunction<WebElement, WebElement> {

    @Override
    public WebElement convert(WebElement result) {
        return result;
    }

    @Override
    public Class<WebElement> getResultType() {
        return null;
    }
}
