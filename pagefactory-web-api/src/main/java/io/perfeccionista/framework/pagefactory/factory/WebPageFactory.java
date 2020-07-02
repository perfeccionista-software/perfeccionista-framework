package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.WebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.utils.ReflectionUtils.getInheritedInterfaces;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.commons.util.ReflectionUtils.findMethods;

public class WebPageFactory {

    protected final WebElementsConfiguration configuration;
    protected final WebElementInitializer initializer;
    protected final WebElementDecorator decorator;

    public WebPageFactory(WebElementsConfiguration configuration) {
        this.configuration = configuration;
        this.initializer = new WebElementInitializer(configuration);
        this.decorator = new WebElementDecorator(configuration);
    }

    public WebPage createWebPage(Class<? extends WebPage> webPageClass) {
        WebPage webPageInstance = initializer.initWebPageInstance(webPageClass);
        List<Method> webChildElementMethods = getWebChildElementMethods(webPageClass);
        WebElementRegistry webPageElementRegistry = createWebChildElements(webPageInstance, webChildElementMethods);
        return decorator.decorateWebPageInstance(webPageInstance, webPageElementRegistry);
    }

    public Map<Integer, WebMappedBlock> createWebMappedBlocks(WebList parent, WebListFilterResult filterResult) {
        Set<Integer> indexes = filterResult.getIndexes();
        WebLocatorHolder liLocatorHolder = parent.getLocator(LI)
                .orElseThrow(() ->
                        new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI))
                                .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", this.toString()))));
        String hash = filterResult.getHash();
        Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", parent);
        Map<Integer, WebMappedBlock> webMappedBlocks = new HashMap<>();
        for (int index : indexes) {
            WebMappedBlock webMappedBlockInstance = initializer.initWebMappedBlock(mappedBlockClass);
            List<Method> childElementMethods = getWebChildElementMethods(mappedBlockClass);
            WebElementRegistry elementRegistry = createWebChildElements(webMappedBlockInstance, childElementMethods);
            WebParentInfo parentInfo = WebParentInfo.of(hash, liLocatorHolder, index);
            webMappedBlocks.put(index, decorator.decorateWebMappedBlockInstance(parent, webMappedBlockInstance, elementRegistry, parentInfo));
        }
        return webMappedBlocks;
    }

    protected WebElementRegistry createWebChildElements(WebParentElement parent, List<Method> elementMethods) {
        List<WebChildElement> webChildElements = elementMethods.stream()
                .map(webChildElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends WebChildElement> webChildElementType = (Class<? extends WebChildElement>) webChildElementMethod.getReturnType();
                    if (WebBlock.class.isAssignableFrom(webChildElementType)) {
                        //noinspection unchecked because webChildElementType already checked
                        Class<? extends WebBlock> webBlockType = (Class<? extends WebBlock>) webChildElementType;
                        WebBlock webBlockInstance = initializer.initWebBlock(webBlockType);
                        List<Method> childElementMethods = getWebChildElementMethods(webBlockType);
                        WebElementRegistry elementRegistry = createWebChildElements(webBlockInstance, childElementMethods);
                        return decorator.decorateWebBlockInstance(parent, webBlockInstance, webChildElementMethod, elementRegistry);
                    } else {
                        WebChildElement webChildElementInstance = initializer.initWebChildElement(webChildElementType);
                        return decorator.decorateWebChildElementInstance(parent, webChildElementInstance, webChildElementMethod);
                    }
                }).collect(toList());
        return WebElementRegistry.of(webChildElements);
    }

    protected static List<Method> getWebChildElementMethods(Class<? extends WebParentElement> processedClass) {
        Predicate<Method> methodPredicate = method -> WebChildElement.class.isAssignableFrom(method.getReturnType()) && !method.isDefault();
        Set<Method> methods = new HashSet<>();
        Set<Class<? extends WebParentElement>> inheritedInterfaces = getInheritedInterfaces(WebParentElement.class, processedClass);
        for (Class<? extends WebParentElement> inheritedInterface : inheritedInterfaces) {
            methods.addAll(findMethods(inheritedInterface, methodPredicate));
        }
        return new ArrayList<>(methods);
    }

}
