package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.WebPage;

public interface WebPageParameter extends BddStepParameter {

    WebPageParameter usePage();

    WebPage getPage();

}
