package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.TableColumnParameter;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowTextCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableRowIndexCondition;

public class WebTextTableConditions {

    /**
     *
     * @param rowIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebTextTableRowCondition withIndex(ValueIntegerParameter rowIndex) {
        return new WebTextTableRowIndexCondition(rowIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with text {stringValue}")
    @Given("с текстом {stringValue}")
    public WebTextTableRowCondition withText(TableColumnParameter tableColumn,
                                             ValueStringParameter stringValue) {
        return new WebTextTableRowTextCondition(tableColumn.getColumnName(), stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without text {stringValue}")
    @Given("без текста {stringValue}")
    public WebTextTableRowCondition withoutText(TableColumnParameter tableColumn,
                                                ValueStringParameter stringValue) {
        return new WebTextTableRowTextCondition(tableColumn.getColumnName(), stringValue.getValue()).inverse();
    }

}
