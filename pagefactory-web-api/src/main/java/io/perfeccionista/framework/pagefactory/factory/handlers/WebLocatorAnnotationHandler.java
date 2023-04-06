package io.perfeccionista.framework.pagefactory.factory.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorRegistry;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_STRATEGY_VALIDATION_FAILED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.CONTAINS_TEXT;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.CSS;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.DTI;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.NAME;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.TAG_NAME;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.XPATH;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAnnotation;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;
import static io.perfeccionista.framework.utils.StringUtils.isNotBlank;

public class WebLocatorAnnotationHandler {

    private WebLocatorAnnotationHandler() {
    }

    public static @NotNull WebSelectorRegistry createWebLocatorRegistryFor(@NotNull Class<? extends WebPage> webElementClass) {
        Map<String, WebSelectorHolder> webLocators = new HashMap<>();
        findAllRepeatableAnnotationsInHierarchy(WebSelector.class, WebPage.class, webElementClass)
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        return WebSelectorRegistry.of(webLocators);
    }

    public static @NotNull WebSelectorRegistry createWebLocatorRegistryFor(@NotNull WebChildElement webChildElement,
                                                                           @NotNull Method elementMethod,
                                                                           @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebSelectorHolder> webLocators = configuration
                .getWebLocatorConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebSelector.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        findRepeatableAnnotations(elementMethod, WebSelector.class)
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        findFirstAnnotationInHierarchy(WebItemSelector.class, WebChildElement.class, webChildElement.getClass())
                .ifPresent(webLocator -> webLocators.put(ITEM, createWebLocatorHolder(webLocator)));
        findAnnotation(elementMethod, WebItemSelector.class)
                .ifPresent(webLocator -> webLocators.put(ITEM, createWebLocatorHolder(webLocator)));
        return WebSelectorRegistry.of(webLocators);
    }

    public static @NotNull WebSelectorRegistry createWebLocatorRegistryFor(@NotNull WebChildElement webChildElement,
                                                                           @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebSelectorHolder> webLocators = configuration
                .getWebLocatorConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebSelector.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        findFirstAnnotationInHierarchy(WebItemSelector.class, WebChildElement.class, webChildElement.getClass())
                .ifPresent(webLocator -> webLocators.put(ITEM, createWebLocatorHolder(webLocator)));;
        return WebSelectorRegistry.of(webLocators);
    }

    public static @NotNull WebSelectorHolder createWebLocatorHolder(@NotNull WebSelector webLocator) {
        Optional<WebSelectorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webLocator);
        if (optionalWebLocatorHolder.isPresent()) {
            return optionalWebLocatorHolder.get();
        }
        throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
    }

    public static @NotNull WebSelectorHolder createWebLocatorHolder(@NotNull WebItemSelector webLocator) {
        Optional<WebSelectorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webLocator);
        if (optionalWebLocatorHolder.isPresent()) {
            return optionalWebLocatorHolder.get();
        }
        throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
    }

    public static Optional<WebSelectorHolder> createOptionalWebLocatorHolder(WebSelector webLocator) {
        WebSelectorHolder webLocatorHolder = null;
        if (webLocator.selfNode()) {
            webLocatorHolder = WebSelectorHolder.selfNode(webLocator.component());
        }
        if (isNotBlank(webLocator.id())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), ID, webLocator.id());
        }
        if (isNotBlank(webLocator.css())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), CSS, webLocator.css());
        }
        if (isNotBlank(webLocator.xpath())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), XPATH, webLocator.xpath());
        }
        if (isNotBlank(webLocator.className())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), CLASS_NAME, webLocator.className());
        }
        if (isNotBlank(webLocator.tagName())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), TAG_NAME, webLocator.tagName());
        }
        if (isNotBlank(webLocator.name())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), NAME, webLocator.name());
        }
        if (isNotBlank(webLocator.dti())) {
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), DTI, webLocator.dti());
        }
        if (isNotBlank(webLocator.text())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), EQUALS_TEXT, webLocator.text());
        }
        if (isNotBlank(webLocator.containsText())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(webLocator.component(), CONTAINS_TEXT, webLocator.containsText());
        }
        if (Objects.isNull(webLocatorHolder)) {
            return Optional.empty();
        }
        webLocatorHolder.setSingle(webLocator.single())
                .setStrictSearch(webLocator.strictSearch())
            .setFromParent(webLocator.fromParent())
            .setOnlyWithinParent(webLocator.onlyWithinParent());
        for (Class<? extends EndpointHandler<Void>> endpointHandlerClass : webLocator.invokeOnCall()) {
            webLocatorHolder.addInvokedOnCallFunction(newInstance(endpointHandlerClass));
        }
        return Optional.of(webLocatorHolder);
    }

    public static Optional<WebSelectorHolder> createOptionalWebLocatorHolder(WebItemSelector webLocator) {
        WebSelectorHolder webLocatorHolder = null;
        if (isNotBlank(webLocator.id())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, ID, webLocator.id());
        }
        if (isNotBlank(webLocator.css())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, CSS, webLocator.css());
        }
        if (isNotBlank(webLocator.xpath())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, XPATH, webLocator.xpath());
        }
        if (isNotBlank(webLocator.className())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, CLASS_NAME, webLocator.className());
        }
        if (isNotBlank(webLocator.tagName())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, TAG_NAME, webLocator.tagName());
        }
        if (isNotBlank(webLocator.name())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, NAME, webLocator.name());
        }
        if (isNotBlank(webLocator.dti())) {
            webLocatorHolder = WebSelectorHolder.of(ITEM, DTI, webLocator.dti());
        }
        if (isNotBlank(webLocator.text())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, EQUALS_TEXT, webLocator.text());
        }
        if (isNotBlank(webLocator.containsText())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebSelectorHolder.of(ITEM, CONTAINS_TEXT, webLocator.containsText());
        }
        if (Objects.isNull(webLocatorHolder)) {
            return Optional.empty();
        }
        webLocatorHolder.setSingle(false)
                .setStrictSearch(webLocator.strictSearch())
                .setFromParent(webLocator.fromParent())
                .setOnlyWithinParent(webLocator.onlyWithinParent());
        for (Class<? extends EndpointHandler<Void>> endpointHandlerClass : webLocator.invokeOnCall()) {
            webLocatorHolder.addInvokedOnCallFunction(newInstance(endpointHandlerClass));
        }
        return Optional.of(webLocatorHolder);
    }

    private static void checkWebLocatorStrategyIsEmpty(@Nullable WebSelectorHolder webLocatorHolder, WebSelector webLocator) {
        if (webLocatorHolder != null) {
            throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
        }
    }

    private static void checkWebLocatorStrategyIsEmpty(@Nullable WebSelectorHolder webLocatorHolder, WebItemSelector webLocator) {
        if (webLocatorHolder != null) {
            throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
        }
    }

    private static JsonNode webLocatorToJson(WebSelector webLocator) {
        ObjectNode rootNode = createObjectNode()
                .put("component", webLocator.component())
                .put("id", webLocator.id())
                .put("css", webLocator.css())
                .put("xpath", webLocator.xpath())
                .put("className", webLocator.className())
                .put("tagName", webLocator.tagName())
                .put("dti", webLocator.dti())
                .put("name", webLocator.name())
                .put("text", webLocator.text())
                .put("containsText", webLocator.containsText())
                .put("selfNode", webLocator.selfNode())
                .put("single", webLocator.single())
                .put("strictSearch", webLocator.strictSearch())
                .put("fromParent", webLocator.fromParent())
                .put("onlyWithinParent", webLocator.onlyWithinParent());
        ArrayNode invokeOnCallNode = rootNode.putArray("invokeOnCall");
        for (Class<? extends EndpointHandler<Void>> jsFunctionClass : webLocator.invokeOnCall()) {
            invokeOnCallNode.add(jsFunctionClass.getCanonicalName());
        }
        return rootNode;
    }

    private static JsonNode webLocatorToJson(WebItemSelector webLocator) {
        ObjectNode rootNode = createObjectNode()
                .put("component", ITEM)
                .put("id", webLocator.id())
                .put("css", webLocator.css())
                .put("xpath", webLocator.xpath())
                .put("className", webLocator.className())
                .put("tagName", webLocator.tagName())
                .put("dti", webLocator.dti())
                .put("name", webLocator.name())
                .put("text", webLocator.text())
                .put("containsText", webLocator.containsText())
                .put("single", false)
                .put("strictSearch", webLocator.strictSearch())
                .put("fromParent", webLocator.fromParent())
                .put("onlyWithinParent", webLocator.onlyWithinParent());
        ArrayNode invokeOnCallNode = rootNode.putArray("invokeOnCall");
        for (Class<? extends EndpointHandler<Void>> jsFunctionClass : webLocator.invokeOnCall()) {
            invokeOnCallNode.add(jsFunctionClass.getCanonicalName());
        }
        return rootNode;
    }

}
