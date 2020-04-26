package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.elements.WebPage;

public class WebPageParameterImpl implements WebPageParameter {

    private final Environment environment;
    private final String rawInput;

    public WebPageParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public WebPageParameter usePage() {
        environment.getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .usePage(rawInput);
        return this;
    }

    @Override
    public WebPage getPage() {
        return environment.getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .getPage(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
