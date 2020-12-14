package io.perfeccionista.framework.cucumber.parameters.datatable.entries;

import io.perfeccionista.framework.cucumber.parameters.CucumberStepParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebElementEntry<T extends WebChildElement> extends CucumberStepParameter, VerifiableDataTableEntry {

    WebElementParameter<T> get();

}
