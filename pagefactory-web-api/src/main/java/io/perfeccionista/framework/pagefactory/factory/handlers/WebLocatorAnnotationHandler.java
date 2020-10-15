package io.perfeccionista.framework.pagefactory.factory.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebLocatorProcessing;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_STRATEGY_VALIDATION_FAILED;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CONTAINS_TEXT;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CSS;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TAG_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;
import static org.junit.platform.commons.util.StringUtils.isNotBlank;

public class WebLocatorAnnotationHandler {

    private WebLocatorAnnotationHandler() {
    }

    public static @NotNull WebLocatorRegistry createWebLocatorRegistryFor(@NotNull Class<? extends WebPage> webElementClass) {
        Map<String, WebLocatorHolder> webLocators = new HashMap<>();
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebPage.class, webElementClass)
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        return WebLocatorRegistry.of(webLocators);
    }

    public static @NotNull WebLocatorRegistry createWebLocatorRegistryFor(@NotNull WebChildElement webChildElement,
                                                                          @NotNull Method elementMethod,
                                                                          @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebLocatorHolder> webLocators = configuration
                .getWebLocatorConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        findRepeatableAnnotations(elementMethod, WebLocator.class)
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        return WebLocatorRegistry.of(webLocators);
    }

    public static @NotNull WebLocatorRegistry createWebLocatorRegistryFor(@NotNull WebChildElement webChildElement,
                                                                          @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebLocatorHolder> webLocators = configuration
                .getWebLocatorConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        return WebLocatorRegistry.of(webLocators);
    }

    public static @NotNull WebLocatorHolder createWebLocatorHolder(@NotNull WebLocator webLocator) {
        Optional<WebLocatorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webLocator);
        if (optionalWebLocatorHolder.isPresent()) {
            WebLocatorHolder webLocatorHolder = optionalWebLocatorHolder.get();
            webLocatorHolder.setSingle(webLocator.single());
            webLocatorHolder.setStrictSearch(webLocator.strictSearch());
            webLocatorHolder.setOnlyWithinParent(webLocator.onlyWithinParent());
            for (Class<? extends JsFunction<Void>> jsFunctionClass : webLocator.invokeOnCall()) {
                webLocatorHolder.addInvokedOnCallFunction(newInstance(jsFunctionClass));
            }
            return webLocatorHolder;
        }
        throw WebLocatorProcessing.exception(WEB_LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
    }

    public static Optional<WebLocatorHolder> createOptionalWebLocatorHolder(WebLocator webLocator) {
        WebLocatorHolder webLocatorHolder = null;
        if (isNotBlank(webLocator.id())) {
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), ID, webLocator.id());
        }
        if (isNotBlank(webLocator.css())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), CSS, webLocator.css());
        }
        if (isNotBlank(webLocator.xpath())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), XPATH, webLocator.xpath());
        }
        if (isNotBlank(webLocator.className())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), CLASS_NAME, webLocator.className());
        }
        if (isNotBlank(webLocator.tagName())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), TAG_NAME, webLocator.tagName());
        }
        if (isNotBlank(webLocator.name())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), NAME, webLocator.name());
        }
        if (isNotBlank(webLocator.text())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), TEXT, webLocator.text());
        }
        if (isNotBlank(webLocator.containsText())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), CONTAINS_TEXT, webLocator.containsText());
        }
        return Optional.ofNullable(webLocatorHolder);
    }

    private static void checkWebLocatorStrategyIsEmpty(@Nullable WebLocatorHolder webLocatorHolder, WebLocator webLocator) {
        if (webLocatorHolder != null) {
            throw WebLocatorProcessing.exception(WEB_LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
        }
    }

    private static JsonNode webLocatorToJson(WebLocator webLocator) {
        ObjectNode rootNode = createObjectNode()
                .put("component", webLocator.component())
                .put("id", webLocator.id())
                .put("css", webLocator.css())
                .put("xpath", webLocator.xpath())
                .put("className", webLocator.className())
                .put("tagName", webLocator.tagName())
                .put("name", webLocator.name())
                .put("text", webLocator.text())
                .put("containsText", webLocator.containsText())
                .put("single", webLocator.single())
                .put("strictSearch", webLocator.strictSearch())
                .put("onlyWithinParent", webLocator.onlyWithinParent());
        ArrayNode invokeOnCallNode = rootNode.putArray("invokeOnCall");
        for (Class<? extends JsFunction<Void>> jsFunctionClass : webLocator.invokeOnCall()) {
            invokeOnCallNode.add(jsFunctionClass.getCanonicalName());
        }
        return rootNode;
    }

}
