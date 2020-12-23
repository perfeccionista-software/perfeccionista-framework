package io.perfeccionista.framework.cucumber.parameters.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.exceptions.IncorrectDataTableFormat;
import io.perfeccionista.framework.fixture.FixtureParameters;
import io.perfeccionista.framework.value.ValueService;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.INCORRECT_FIXTURE_PARAMETERS_DATA_TABLE_FORMAT;

// TODO: Вынести привязку к имплементации параметров в конфигурацию
public class EnvironmentDataTablesDefinition implements EnvironmentAvailable {

    @Override
    public Environment getEnvironment() {
        return Environment.getCurrent();
    }

    @DataTableType
    public FixtureParameters fixtureParameters(DataTable dataTable) {
        FixtureParameters fixtureParameters = FixtureParameters.builder();
        for (List<String> dataTableRow : dataTable.asLists()) {
            if (dataTableRow.size() != 2) {
                throw IncorrectDataTableFormat.exception(INCORRECT_FIXTURE_PARAMETERS_DATA_TABLE_FORMAT.getMessage());
            }
            String parameterName = dataTableRow.get(0);
            Object parameter = getEnvironment().getService(ValueService.class)
                    .objectProcess(dataTableRow.get(1));
            fixtureParameters.addParameter(parameterName, parameter);
        }
        return fixtureParameters;
    }







}
