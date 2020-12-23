package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilderResolver;

public class PagefactoryWebDataTablesDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    @DataTableType
    public WebRadioGroupFilterBuilder webRadioGroupFilterBuilder(DataTable dataTable) {
        return WebRadioGroupFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebListFilterBuilder webListFilterBuilder(DataTable dataTable) {
        return WebListFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebTableFilterBuilder webTableFilterBuilder(DataTable dataTable) {
        return WebTableFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebTextListFilterBuilder webTextListFilterBuilder(DataTable dataTable) {
        return WebTextListFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

    @DataTableType
    public WebTextTableFilterBuilder webTextTableFilterBuilder(DataTable dataTable) {
        return WebTextTableFilterBuilderResolver.of(getEnvironment(), dataTable)
                .resolve();
    }

}
