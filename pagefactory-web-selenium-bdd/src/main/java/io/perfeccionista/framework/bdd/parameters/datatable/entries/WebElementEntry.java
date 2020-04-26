package io.perfeccionista.framework.bdd.parameters.datatable.entries;

import io.perfeccionista.framework.bdd.parameters.BddStepParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

public interface WebElementEntry<T extends Element> extends BddStepParameter, VerifiableDataTableEntry {

    WebElementParameter<T> get();

}
