package io.perfeccionista.framework.bdd.parameters.datatable.entries;

import io.perfeccionista.framework.bdd.parameters.BddStepParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebElementEntry<T extends WebChildElement> extends BddStepParameter, VerifiableDataTableEntry {

    WebElementParameter<T> get();

}
