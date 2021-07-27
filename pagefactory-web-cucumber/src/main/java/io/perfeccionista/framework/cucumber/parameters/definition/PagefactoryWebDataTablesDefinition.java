package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilderResolver;

public class PagefactoryWebDataTablesDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    @DataTableType
    public WebBlockFilterBuilder<WebBlock> webListFilterBuilder(DataTable dataTable) {
        return WebListFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebRadioGroupFilterBuilder webRadioGroupFilterBuilder(DataTable dataTable) {
        return WebRadioGroupFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebTextBlockFilterBuilder webTextListFilterBuilder(DataTable dataTable) {
        return WebTextListFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

}
