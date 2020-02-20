package io.perfeccionista.framework.pagefactory.asserts;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.action.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.action.wrappers.LogicActionWrapper.runLogic;

public class ElementActions {

    private final Environment environment;

    public ElementActions(Environment environment) {
        this.environment = environment;
    }

    public ElementActions click(ClickAvailable element) {
        runLogic(environment, (@NotNull Runnable) element::click);
        return this;
    }

    public ElementActions click(String elementsChain) {

    }


}
