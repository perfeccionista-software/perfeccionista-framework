package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;

public interface WebStepDefinitions extends CucumberStepDefinitions {

    default WebPageContext getWebPageContext() {
        return getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getWebPageContext();
    }

    default WebBrowserDispatcher getWebBrowserDispatcher() {
        return getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher();
    }

}
