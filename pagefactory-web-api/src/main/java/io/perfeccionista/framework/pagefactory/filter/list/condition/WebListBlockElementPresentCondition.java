package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;

public class WebListBlockElementPresentCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebChildElement elementFrame;

    private boolean inverse = false;

    public WebListBlockElementPresentCondition(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebListBlockElementPresentCondition(@NotNull IsPresentAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = (WebChildElement) elementFrame;
    }

    public WebListBlockElementPresentCondition present() {
        return this;
    }

    public WebListBlockElementPresentCondition notPresent() {
        return this.inverse();
    }

    @Override
    public WebListBlockCondition and(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(WebConditionGrouping.AND, condition));
        return this;
    }

    @Override
    public WebListBlockCondition or(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(WebConditionGrouping.OR, condition));
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebList element, @Nullable String hash) {

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Находим необходимый элемент, заданный по пути или по методу
        WebChildElement elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), elementFrame.getElementIdentifier().getElementType());
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        JsOperation<Boolean> jsOperation = elementToFilter
                .getJsOperationActionImplementation(IS_PRESENT_METHOD, Boolean.class)
                .getJsOperation(elementToFilter, PRESENTED);
        jsOperation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
        Map<Integer, Boolean> displayedValues = operationResult.getResults();
        Set<Integer> matches = getMatches(displayedValues);

        // Формируем ответ
        return WebFilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, Boolean> presentValues) {
        Set<Integer> matches = new HashSet<>();
        presentValues.forEach((key, value) -> {
            if ((!inverse && value != null && value) || (inverse && value == null) || (inverse && !value)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebListBlockElementPresentCondition inverse() {
        inverse = true;
        return this;
    }

}
