package io.perfeccionista.framework.matcher.element.implementations;

import io.perfeccionista.framework.exceptions.ElementIsDisplayed;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.HtmlAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.element.WebTableMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_IS_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.COMPONENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COLUMN_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.COLUMN_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.THEAD_ROW;

public class WebTableShouldHaveColumnMatcher implements WebTableMatcher {

    private final String columnName;
    private final boolean positive;

    public WebTableShouldHaveColumnMatcher(@NotNull String columnName, boolean positive) {
        this.columnName = columnName;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebTable element) {
        InvocationName invocationName = positive
                ? assertInvocation(COLUMN_SHOULD_BE_DISPLAYED_METHOD, element)
                : assertInvocation(COLUMN_SHOULD_NOT_BE_DISPLAYED_METHOD, element);

        runCheck(element.getEnvironment(), invocationName,
                () -> {
                    if (positive) {
                        shouldBeDisplayed(element, columnName);
                    } else {
                        shouldNotBeDisplayed(element, columnName);
                    }
                });
    }

    protected void shouldBeDisplayed(WebTable element, String componentName) {
        WebTableFrame<WebBlock> webTableRegistry = element.getWebTableFrame();

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator();
        tableLocatorChain.addLastLocator(element.getRequiredLocator(THEAD_ROW))
                .addLastLocator(webTableRegistry.getRequiredHeaderLocator(columnName));

        // Добавляем в цепочку локаторов операции локаторы до ячейки таблицы
        WebBlock columnBlock = webTableRegistry.getRequiredHeaderMappedBlock(columnName);
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(columnBlock);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(columnBlock, operationType, DISPLAYED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation);
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (exception instanceof JsElementSearch) {
                throw ElementNotDisplayed.assertionError(COMPONENT_NOT_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addSuppressedException(exception)
                        .setAttachment(exception.getAttachment().orElse(Attachment.with()))
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
            throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        boolean displayed = operationResult.getResult();
        if (!displayed) {
            throw ElementNotDisplayed.assertionError(ELEMENT_NOT_DISPLAYED.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true)
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                    .addLastAttachmentEntry(TextAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
        }
    }

    protected void shouldNotBeDisplayed(WebTable element, String componentName) {
        WebTableFrame<WebBlock> webTableRegistry = element.getWebTableFrame();

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator();
        tableLocatorChain.addLastLocator(element.getRequiredLocator(THEAD_ROW))
                .addLastLocator(webTableRegistry.getRequiredHeaderLocator(columnName));

        // Добавляем в цепочку локаторов операции локаторы до ячейки таблицы
        WebBlock columnBlock = webTableRegistry.getRequiredHeaderMappedBlock(columnName);
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(columnBlock);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(columnBlock, operationType, DISPLAYED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation);
        operationResult.ifSuccess(successOperationResult -> {
            // Сюда мы попадем если элемент найден. При этом он может быть невидим для пользователя
            boolean displayed = successOperationResult.getResult();
            if (displayed) {
                throw ElementIsDisplayed.assertionError(COMPONENT_IS_DISPLAYED.getMessage(componentName, element.getElementIdentifier().getLastUsedName()))
                        .setProcessed(true)
                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addLastAttachmentEntry(HtmlAttachmentEntry.of("OuterHtml", operationResult.getPageSource()));
            }
        });
        operationResult.ifException((exceptionMapper, originalException) -> {
            PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
            if (!(exception instanceof JsElementSearch)) {
                throw exception.addLastAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            }
        });
    }

}

