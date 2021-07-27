package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class MobileListBlockElementTextCondition implements MobileListBlockCondition {
    private final Deque<MobileListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final MobileGetTextAvailable elementFrame;
    private final String expectedText;
    private boolean inverse = false;
    public MobileListBlockElementTextCondition(MobileGetTextAvailable elementFrame, String expectedText) {

        this.elementFrame = elementFrame;
        this.expectedText = expectedText;
    }

    @Override
    public MobileListBlockCondition and(@NotNull MobileListBlockCondition condition) {
        childConditions.add(MobileListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public MobileListBlockCondition or(@NotNull MobileListBlockCondition condition) {
        childConditions.add(MobileListBlockConditionHolder.of(OR, condition));
        return this;
    }

    public MobileListBlockElementTextCondition containsText() {
        return this;
    }

    public MobileListBlockElementTextCondition notContainText() {
        return this.inverse();
    }

    private MobileListBlockElementTextCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<MobileListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull MobileList element, @Nullable String hash) {
        // Цепочка от корня страницы до MobileListBlock
        MobileLocatorChain listLocatorChain = element.getLocatorChain();
        MobileLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(ITEM));

        MobileGetTextAvailable elementToFilter = element.getMobileListFrame()
                .getMappedBlockFrame()
                .getElementRegistry()
                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), MobileGetTextAvailable.class);

        listLocatorChain.addLastLocators(elementToFilter.getLocatorChain());
        MobileElementOperation<String> operation = MobileElementOperation.of(listLocatorChain, MobileGetTextOperationType.of(elementToFilter));
        MobileElementOperationResult<String> operationResult = element.getMobileDeviceDispatcher().executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
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
            boolean check = expectedText.equals(value);
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

}
