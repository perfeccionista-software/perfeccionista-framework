package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebTextListBlockTextNumberValueCondition implements WebTextListBlockCondition {

    private final Deque<WebTextListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final NumberValue<?> expectedNumberValue;

    private boolean inverse = false;

    public WebTextListBlockTextNumberValueCondition(@NotNull NumberValue<?> expectedNumberValue) {
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebTextListBlockTextNumberValueCondition containsTextBlock() {
        return this;
    }

    public WebTextListBlockTextNumberValueCondition notContainTextBlock() {
        return this.inverse();
    }

    @Override
    public WebTextListBlockTextNumberValueCondition and(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTextListBlockTextNumberValueCondition or(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebTextListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebTextList element, @Nullable String hash) {
        WebLink webTextLink = element.getWebTextListFrame()
                .getMappedBlockFrame()
                .textLink();

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(webTextLink);
        WebElementOperation<String> operation = WebElementOperationHandler.of(webTextLink, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
        Map<Integer, String> textValues = operationResult.getResults();
        Set<Integer> matches = getMatches(textValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, String> textValues) {
        Set<Integer> matches = new HashSet<>();
        textValues.forEach((key, value) -> {
            boolean check = expectedNumberValue.checkString(value);
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebTextListBlockTextNumberValueCondition inverse() {
        inverse = true;
        return this;
    }

}
