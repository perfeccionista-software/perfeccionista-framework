package io.perfeccionista.framework.pagefactory.filter.conditions;

import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_STATE_NOT_FOUND;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;

public class WebItemElementHaveStateCondition<T extends WebBlock<?>> implements WebItemCondition<T> {

    private final Deque<WebListItemConditionHolder<T>> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebChildElement elementFrame;
    private final String stateName;

    private boolean inverse = false;

    public WebItemElementHaveStateCondition(@NotNull String elementPath,
                                            @NotNull String stateName) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.stateName = stateName;
    }

    public WebItemElementHaveStateCondition(@NotNull WebChildElement elementFrame,
                                            @NotNull String stateName) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.stateName = stateName;
    }

    public WebItemElementHaveStateCondition<T> haveState() {
        return this;
    }

    public WebItemElementHaveStateCondition<T> notHaveState() {
        return this.inverse();
    }

    @Override
    public WebItemCondition<T> and(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(ConditionGrouping.AND, condition));
        return this;
    }

    @Override
    public WebItemCondition<T> or(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(ConditionGrouping.OR, condition));
        return this;
    }

    @Override
    public Deque<WebListItemConditionHolder<T>> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash) {

        // Цепочка от корня страницы до WebListBlock
        WebSelectorChain listLocatorChain = element.getSelectorChain();
        WebSelectorHolder listLocatorHolder = listLocatorChain.getLastSelector()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastSelector(element.getRequiredSelector(ITEM));

        // Находим необходимый элемент, заданный по пути или по методу
        WebChildElement elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), elementFrame.getElementIdentifier().getElementType());
        }

        // Формируем операцию по проверке состояния
        Optional<WebElementStateHolder> optionalStateHolder = elementToFilter.getStateHolder(stateName);
        WebElementOperation<Boolean> operation;
        if (optionalStateHolder.isPresent()) {
            operation = optionalStateHolder.get()
                    .getOperation(elementToFilter)
                    .withPageSource();
        } else {
            throw ElementStateNotFound.exception(ELEMENT_STATE_NOT_FOUND.getMessage(stateName))
                    .addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
        }
        operation.getLocatorChain()
                .addFirstSelectors(listLocatorChain);
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher()
                .executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    throw exceptionMapper.mapElementException(element, originalException);
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getSelectorId());
        Map<Integer, Boolean> hasStateValues = operationResult.getResults();
        Set<Integer> matches = getMatches(hasStateValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, Boolean> haveStateValues) {
        Set<Integer> matches = new HashSet<>();
        haveStateValues.forEach((key, value) -> {
            boolean check = value;
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebItemElementHaveStateCondition<T> inverse() {
        inverse = true;
        return this;
    }

}
