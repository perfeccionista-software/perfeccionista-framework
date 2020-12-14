package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;

public interface WebStepDefinitions extends EnvironmentAvailable {

    @Override
    default Environment getEnvironment() {
        return Environment.getCurrent();
    }

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
