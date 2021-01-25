package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;

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
