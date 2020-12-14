package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.WebPage;

public interface WebPageParameter extends CucumberStepParameter {

    WebPageParameter usePage();

    WebPage getPage();

}
