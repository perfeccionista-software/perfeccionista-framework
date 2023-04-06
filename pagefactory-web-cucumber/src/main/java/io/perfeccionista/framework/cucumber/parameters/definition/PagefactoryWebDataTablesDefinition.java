package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderResolver;

public class PagefactoryWebDataTablesDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    @DataTableType
    public WebListFilterBuilder<WebBlock<?>> webListFilterBuilder(DataTable dataTable) {
        return WebListFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

//    @DataTableType
//    public WebRadioGroupFilterBuilder webRadioGroupFilterBuilder(DataTable dataTable) {
//        return WebRadioGroupFilterBuilderResolver.of(getEnvironment(), dataTable)
//                .resolve();
//    }
//
//    @DataTableType
//    public WebTextBlockFilterBuilder webTextListFilterBuilder(DataTable dataTable) {
//        return WebTextListFilterBuilderResolver.of(getEnvironment(), dataTable)
//                .resolve();
//    }

}
