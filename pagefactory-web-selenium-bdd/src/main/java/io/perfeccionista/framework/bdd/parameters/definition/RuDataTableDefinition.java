package io.perfeccionista.framework.bdd.parameters.definition;

import io.cucumber.java.DataTableType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameterImpl;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.Map;

public class RuDataTableDefinition implements EnvironmentAvailable {

    private final Environment environment;

    public RuDataTableDefinition(Environment environment) {
        this.environment = environment;
    }

    @DataTableType
    public <T extends Element> WebElementParameter<T> webElementParameterEntry(Map<String, String> entry) {
        // verifyEntry()
        return new WebElementParameterImpl<>(environment, entry.get("elementPath"));
    }



    // TODO: WebRadioButtonFilter
    // TODO: WebListFilter
    // TODO: WebTableFilter
    // TODO: WebTextListFilter
    // TODO: WebTextTableFilter


}
