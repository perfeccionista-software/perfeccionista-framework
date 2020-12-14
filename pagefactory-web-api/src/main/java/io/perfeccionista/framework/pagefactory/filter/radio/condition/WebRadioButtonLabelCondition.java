package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.OR;

public class WebRadioButtonLabelCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

    private final String expectedText;
    private final StringValue expectedStringValue;
    private final NumberValue<? extends Number> expectedNumberValue;

    private boolean inverse = false;

    public WebRadioButtonLabelCondition(@NotNull String expectedText) {
        this.expectedText = expectedText;
        this.expectedStringValue = null;
        this.expectedNumberValue = null;
    }

    public WebRadioButtonLabelCondition(@NotNull StringValue expectedStringValue) {
        this.expectedText = null;
        this.expectedStringValue = expectedStringValue;
        this.expectedNumberValue = null;
    }

    public WebRadioButtonLabelCondition(@NotNull NumberValue<? extends Number> expectedNumberValue) {
        this.expectedText = null;
        this.expectedStringValue = null;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebRadioButtonLabelCondition containsLabel() {
        return this;
    }

    public WebRadioButtonLabelCondition notContainLabel() {
        return this.inverse();
    }

    @Override
    public WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebRadioButtonConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash) {
        WebRadioButton webRadioButton = element.getWebRadioGroupFrame()
                .getMappedBlockFrame()
                .radioButton();

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain();
        WebLocatorHolder radioGroupLocatorHolder = radioGroupLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        radioGroupLocatorChain.addLastLocator(element.getRequiredLocator(RADIO));

        // Добавляем в цепочку локаторов операции локаторы до блока RadioButtonBlock
        JsOperation<String> jsOperation = webRadioButton
                .getJsOperationActionImplementation(GET_LABEL_METHOD, String.class)
                .getJsOperation(webRadioButton);
        jsOperation.getLocatorChain()
                .addFirstLocators(radioGroupLocatorChain);

        // Выполняем операцию
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(radioGroupLocatorHolder.getLocatorId());
        Map<Integer, String> labelValues = operationResult.getResults();
        Set<Integer> matches = getMatches(labelValues);

        // Формируем ответ
        return WebFilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, String> textValues) {
        Set<Integer> matches = new HashSet<>();
        textValues.forEach((key, value) -> {
            boolean check;
            if (expectedText != null) {
                check = expectedText.equals(value);
            } else if (expectedStringValue != null) {
                check = expectedStringValue.check(value);
            } else {
                check = expectedNumberValue.checkString(value);
            }
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebRadioButtonLabelCondition inverse() {
        inverse = true;
        return this;
    }

}
