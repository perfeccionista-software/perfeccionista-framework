package io.perfeccionista.framework.bdd.parameters.definition;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;

public class RuDataTableDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    // FIXME Cucumber can't work with generic type here
//    @DataTableType
//    public <T extends Element> WebElementParameter<T> webElementParameterEntry(Map<String, String> entry) {
//        // verifyEntry()
//        return new WebElementParameterImpl<>(environment, entry.get("elementPath"));
//    }



    // TODO: WebRadioButtonFilter
    // TODO: WebListFilter
    // TODO: WebTableFilter
    // TODO: WebTextListFilter
    // TODO: WebTextTableFilter


}
