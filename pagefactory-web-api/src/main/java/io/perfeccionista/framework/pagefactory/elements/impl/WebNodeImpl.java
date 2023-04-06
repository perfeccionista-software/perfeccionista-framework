package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.pagefactory.elements.WebNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.options.ClickOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ContextClickOptions;
import io.perfeccionista.framework.pagefactory.elements.options.DoubleClickOptions;
import io.perfeccionista.framework.pagefactory.elements.options.DownloadOptions;
import io.perfeccionista.framework.pagefactory.elements.options.DragAndDropOptions;
import io.perfeccionista.framework.pagefactory.elements.options.GetTextOptions;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.options.TypeTextOptions;
import io.perfeccionista.framework.pagefactory.elements.options.UploadOptions;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebClearOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebContextClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebDoubleClickOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebFileDownloadOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebDragAndDropOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebFileUploadOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsSelectedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebIsImageOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSaveImageToFileOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSendKeyEventsOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebSetValueOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebTypeTextOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Path;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.IMAGE;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.options.ClickOptions.clickOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.ContextClickOptions.contextClickOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.DoubleClickOptions.doubleClickOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.DownloadOptions.downloadOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.DragAndDropOptions.dragAndDropOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.TypeTextOptions.typeTextOptions;
import static io.perfeccionista.framework.pagefactory.elements.options.UploadOptions.uploadOptions;

public class WebNodeImpl extends AbstractWebChildElement implements WebNode {

    protected WebElementRegistry elementRegistry;

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    @Override
    public @NotNull WebChildElement getElement(@NotNull String elementPath) {
        return getElementRegistry().getRequiredElementByPath(elementPath);
    }

    @Override
    public <T extends WebChildElement> @NotNull T getElement(@NotNull String elementPath, @NotNull Class<T> elementClass) {
        return getElementRegistry().getRequiredElementByPath(elementPath, elementClass);
    }

    // Actions

    @Override
    public WebNode executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Add

    @Override
    public WebNode addName(@NotNull String elementName) {
        super.addName(elementName);
        return this;
    }

    @Override
    public WebNode addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        super.addComponent(componentName, selector);
        return this;
    }

    // Asserts

    public WebNode should(@NotNull WebElementCondition... conditions) {
        super.should(conditions);
        return this;
    }

    public WebNode shouldNot(@NotNull WebElementCondition... conditions) {
        super.shouldNot(conditions);
        return this;
    }

    // Click

    @Override
    public WebNode click() {
        ClickOptions options = clickOptions();
        WebClickOperationType operationType = WebClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    @Override
    public WebNode click(@NotNull ClickOptions options) {
        WebClickOperationType operationType = WebClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    @Override
    public WebNode doubleClick() {
        DoubleClickOptions options = doubleClickOptions();
        WebDoubleClickOperationType operationType = WebDoubleClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    @Override
    public WebNode doubleClick(@NotNull DoubleClickOptions options) {
        WebDoubleClickOperationType operationType = WebDoubleClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    @Override
    public WebNode contextClick() {
        ContextClickOptions options = contextClickOptions();
        WebContextClickOperationType operationType = WebContextClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    @Override
    public WebNode contextClick(@NotNull ContextClickOptions options) {
        WebContextClickOperationType operationType = WebContextClickOperationType.of(this, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(CLICK)).executeAction());
        return this;
    }

    // Drag and Drop

    @Override
    public WebNode dragAndDropTo(@NotNull WebNode targetNode) {
        DragAndDropOptions options = dragAndDropOptions();
        WebDragAndDropOperationType operationType = WebDragAndDropOperationType.of(this, targetNode, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(ROOT)).executeAction());
        return this;
    }

    @Override
    public WebNode dragAndDropTo(@NotNull DragAndDropOptions options, @NotNull WebNode targetNode) {
        WebDragAndDropOperationType operationType = WebDragAndDropOperationType.of(this, targetNode, options);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(ROOT)).executeAction());
        return this;
    }

    // File download

    @Override
    public @NotNull File download() {
        DownloadOptions options = downloadOptions();
        WebFileDownloadOperationType operationType = WebFileDownloadOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeGetter());
    }

    @Override
    public @NotNull File download(@NotNull DownloadOptions options) {
        WebFileDownloadOperationType operationType = WebFileDownloadOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeGetter());
    }

    // File Upload

    @Override
    public WebNode uploadFromClasspath(@NotNull String... resourceName) {
        UploadOptions options = uploadOptions();
        WebFileUploadOperationType operationType = WebFileUploadOperationType.of(this, options, resourceName);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    @Override
    public WebNode uploadFromClasspath(@NotNull UploadOptions options, @NotNull String... resourceName) {
        WebFileUploadOperationType operationType = WebFileUploadOperationType.of(this, options, resourceName);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    @Override
    public WebNode uploadFromFile(@NotNull Path... file) {
        UploadOptions options = uploadOptions();
        WebFileUploadOperationType operationType = WebFileUploadOperationType.of(this, options, file);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    @Override
    public WebNode uploadFromFile(@NotNull UploadOptions options, @NotNull Path... file) {
        WebFileUploadOperationType operationType = WebFileUploadOperationType.of(this, options, file);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    // Get text

    @Override
    public @Nullable String getText() {
        GetTextOptions options = GetTextOptions.getTextOptions();
        WebGetTextOperationType operationType = WebGetTextOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(TEXT)).executeGetter());
    }

    @Override
    public @Nullable String getText(@NotNull GetTextOptions options) {
        WebGetTextOperationType operationType = WebGetTextOperationType.of(this, options);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(TEXT)).executeGetter());
    }

    // HoverTo

    @Override
    public WebNode hoverTo() {
        super.hoverTo();
        return this;
    }

    @Override
    public WebNode hoverTo(@NotNull HoverOptions options) {
        hoverTo(options);
        return this;
    }

    // Image

    @Override
    public boolean isImage() {
        WebIsImageOperationType operationType = WebIsImageOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, IMAGE).executeGetter());
    }

    @Override
    public WebNode saveImage(@NotNull Path filePath) {
        WebSaveImageToFileOperationType operationType = WebSaveImageToFileOperationType.of(this, filePath);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, IMAGE).executeAction());
        return this;
    }

    // Input

    @Override
    public WebNode clear() {
        WebClearOperationType operationType = WebClearOperationType.of(this);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebNode sendKeyEvents(@NotNull KeyEventsChain keyEvents) {
        WebSendKeyEventsOperationType operationType = WebSendKeyEventsOperationType.of(this, keyEvents);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    @Override
    public WebNode setText(@NotNull String text) {
        TypeTextOptions options = typeTextOptions();
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    @Override
    public WebNode setText(@NotNull TypeTextOptions options, @NotNull String text) {
        WebTypeTextOperationType operationType = WebTypeTextOperationType.of(this, text);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, options.evaluate(INPUT)).executeAction());
        return this;
    }

    @Override
    public WebNode setValue(@NotNull String text) {
        WebSetValueOperationType operationType = WebSetValueOperationType.of(this, text);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, INPUT).executeAction());
        return this;
    }

    // Is Enabled

    @Override
    public boolean isEnabled() {
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ENABLED).executeGetter());
    }

    @Override
    public boolean isEnabled(@NotNull String componentName) {
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Is selected

    @Override
    public boolean isSelected() {
        WebGetIsSelectedOperationType operationType = WebGetIsSelectedOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, SELECTED).executeGetter());
    }

    @Override
    public boolean isSelected(@NotNull String componentName) {
        WebGetIsSelectedOperationType operationType = WebGetIsSelectedOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, componentName).executeGetter());
    }

    // Press key

    @Override
    public WebNode press(@NotNull Key key) {
        super.press(key);
        return this;
    }

    // ScrollTo

    @Override
    public WebNode scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public WebNode scrollTo(@NotNull ScrollOptions options) {
        super.scrollTo(options);
        return this;
    }

}
