package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.WebPage;
import org.jetbrains.annotations.NotNull;

public interface WebPageParameter extends CucumberStepDefinitionParameter {

    @NotNull WebPageParameter usePage();

    @NotNull WebPage getPage();

}
