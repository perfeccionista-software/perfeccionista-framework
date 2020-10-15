package io.perfeccionista.framework.bdd.conditions;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.ValueIntegerParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;

// TODO: Реализации этих методов по хорошему тоже нужно куда-то вынести.
//  Или просто для каждого модуля элементов иметь отдельную реализацию BDD
//  Или вынести в конфигурацию, где можно для конкретного метода подложить другой кондишен.
@BddFilterCondition(WebTextListFilterBuilder.class)
public class WebTextListConditions {

    /**
     *
     */
    @Given("without filter")
    @Given("без фильтра")
    public WebTextListBlockCondition withEmptyCondition() {
        return WebFilterConditions.allTextBlocks();
    }

    /**
     *
     * @param blockIndex -
     */
    @Given("with index {integerValue}")
    @Given("с индексом {integerValue}")
    public WebTextListBlockCondition withIndex(ValueIntegerParameter blockIndex) {
        return WebFilterConditions.textBlockIndex(blockIndex.getValue());
    }

    /**
     *
     * @param blockIndex -
     */
    @Given("without index {integerValue}")
    @Given("с индексом не {integerValue}")
    public WebTextListBlockCondition withoutIndex(ValueIntegerParameter blockIndex) {
        return WebFilterConditions.textBlockIndexNot(blockIndex.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("with text {stringValue}")
    @Given("с текстом {stringValue}")
    public WebTextListBlockCondition withText(ValueStringParameter stringValue) {
        return WebFilterConditions.containsTextBlock(stringValue.getValue());
    }

    /**
     *
     * @param stringValue -
     */
    @Given("without text {stringValue}")
    @Given("без текста {stringValue}")
    public WebTextListBlockCondition withoutText(ValueStringParameter stringValue) {
        return WebFilterConditions.notContainsTextBlock(stringValue.getValue());
    }

}
