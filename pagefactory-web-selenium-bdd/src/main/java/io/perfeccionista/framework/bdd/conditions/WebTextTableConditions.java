package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.TableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableCellCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableCellTextCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowIndexCondition;

public class WebTextTableConditions {

    /**
     *
     * @param rowIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebTextTableCellCondition withIndex(ValueIntegerParameter rowIndex) {
        return new WebTextTableRowIndexCondition(rowIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with text {stringValue}")
    @Given("с текстом {stringValue}")
    public WebTextTableCellCondition withText(TableColumnParameter tableColumn,
                                              ValueStringParameter stringValue) {
        return new WebTextTableCellTextCondition(tableColumn.getColumnName(), stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without text {stringValue}")
    @Given("без текста {stringValue}")
    public WebTextTableCellCondition withoutText(TableColumnParameter tableColumn,
                                                 ValueStringParameter stringValue) {
        return new WebTextTableCellTextCondition(tableColumn.getColumnName(), stringValue.getValue()).inverse();
    }

}
