package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.TableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;

// TODO: Реализации этих методов по хорошему тоже нужно куда-то вынести.
//  Или просто для каждого модуля элементов иметь отдельную реализацию BDD
//  Или вынести в конфигурацию, где можно для конкретного метода подложить другой кондишен.
@BddFilterCondition(WebTextTableFilterBuilder.class)
public class WebTextTableConditions {

    /**
     *
     */
    @Given("without filter")
    @Given("без фильтра")
    public WebTextTableRowCondition withEmptyCondition() {
        return WebFilterConditions.allTextRows();
    }

    /**
     *
     * @param rowIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebTextTableRowCondition withIndex(ValueIntegerParameter rowIndex) {
        return WebFilterConditions.textRowIndex(rowIndex.getValue());
    }

    /**
     *
     * @param rowIndex -
     */
    @Given("without index {integerValue}")
    @Given("с индексом не {integerValue}")
    public WebTextTableRowCondition withoutIndex(ValueIntegerParameter rowIndex) {
        return WebFilterConditions.textRowIndexNot(rowIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with text {stringValue} in column {tableColumn}")
    @Given("с текстом {stringValue} в столбце {tableColumn}")
    public WebTextTableRowCondition withText(TableColumnParameter tableColumn,
                                             ValueStringParameter stringValue) {
        return WebFilterConditions.containsTextCell(tableColumn.getColumnName(), stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without text {stringValue} in column {tableColumn}")
    @Given("без текста {stringValue} в столбце {tableColumn}")
    public WebTextTableRowCondition withoutText(TableColumnParameter tableColumn,
                                                ValueStringParameter stringValue) {
        return WebFilterConditions.notContainsTextCell(tableColumn.getColumnName(), stringValue.getValue());
    }

}
