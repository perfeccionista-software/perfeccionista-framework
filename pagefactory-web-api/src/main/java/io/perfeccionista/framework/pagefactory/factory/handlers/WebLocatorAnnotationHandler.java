package io.perfeccionista.framework.pagefactory.factory.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.WebLocatorProcessingException;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LOCATOR_STRATEGY_VALIDATION_FAILED;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;
import static org.junit.platform.commons.util.StringUtils.isNotBlank;

public class WebLocatorAnnotationHandler {

    private WebLocatorAnnotationHandler() {
    }

    public static WebLocatorRegistry createWebLocatorRegistryFor(Class<? extends WebPage> webElementClass) {
        Map<String, WebLocatorHolder> webLocators = new HashMap<>();
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebPage.class, webElementClass)
                .forEach(webLocator -> {
                    if (!webLocators.containsKey(webLocator.component())) {
                        webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator));
                    }
                });
        return WebLocatorRegistry.of(webLocators);
    }

    public static WebLocatorRegistry createWebLocatorRegistryFor(WebChildElement webChildElement) {
        Map<String, WebLocatorHolder> webLocators = new HashMap<>();
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> {
                    if (!webLocators.containsKey(webLocator.component())) {
                        webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator));
                    }
                });
        return WebLocatorRegistry.of(webLocators);
    }

    public static WebLocatorRegistry createWebLocatorRegistryFor(WebChildElement webChildElement, Method elementMethod) {
        Map<String, WebLocatorHolder> webLocators = new HashMap<>();
        findRepeatableAnnotations(elementMethod, WebLocator.class)
                .forEach(webLocator -> webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator)));
        findAllRepeatableAnnotationsInHierarchy(WebLocator.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webLocator -> {
                    if (!webLocators.containsKey(webLocator.component())) {
                        webLocators.put(webLocator.component(), createWebLocatorHolder(webLocator));
                    }
                });
        return WebLocatorRegistry.of(webLocators);
    }

    public static WebLocatorHolder createWebLocatorHolder(WebLocator webLocator) {
        WebLocatorHolder webLocatorHolder = null;
        if (isNotBlank(webLocator.id())) {
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "id", webLocator.id());
        }
        if (isNotBlank(webLocator.css())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "css", webLocator.css());
        }
        if (isNotBlank(webLocator.xpath())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "xpath", webLocator.xpath());
        }
        if (isNotBlank(webLocator.className())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "className", webLocator.className());
        }
        if (isNotBlank(webLocator.tagName())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "tagName", webLocator.tagName());
        }
        if (isNotBlank(webLocator.name())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "name", webLocator.name());
        }
        if (isNotBlank(webLocator.text())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "text", webLocator.text());
        }
        if (isNotBlank(webLocator.containsText())) {
            checkWebLocatorStrategyIsEmpty(webLocatorHolder, webLocator);
            webLocatorHolder = WebLocatorHolder.of(webLocator.component(), "containsText", webLocator.containsText());
        }
        if (webLocatorHolder == null) {
            throw new WebLocatorProcessingException(WEB_LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
        }
        webLocatorHolder.setSingle(webLocator.single());
        webLocatorHolder.setStrictSearch(webLocator.strictSearch());
        webLocatorHolder.setOnlyWithinParent(webLocator.onlyWithinParent());
        for (Class<? extends JsFunction<Void>> jsFunctionClass : webLocator.invokeOnCall()) {
            webLocatorHolder.addInvokedOnCallFunction(newInstance(jsFunctionClass));
        }
        return webLocatorHolder;
    }

    private static void checkWebLocatorStrategyIsEmpty(@Nullable WebLocatorHolder webLocatorHolder, WebLocator webLocator) {
        if (webLocatorHolder != null) {
            throw new WebLocatorProcessingException(WEB_LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addAttachmentEntry(JsonAttachmentEntry.of("WebLocator", webLocatorToJson(webLocator)));
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
