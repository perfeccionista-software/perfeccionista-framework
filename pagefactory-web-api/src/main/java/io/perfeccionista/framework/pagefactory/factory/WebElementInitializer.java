package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.ElementImplementationException;
import io.perfeccionista.framework.pagefactory.WebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.factory.proxy.NullInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebBlockMock;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebChildElementMockInvocationHandler;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebMappedBlockMock;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementCallbackFilter;
import io.perfeccionista.framework.pagefactory.factory.proxy.WebParentElementInvocationHandler;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BLOCK_IMPLEMENTATION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_CHILD_ELEMENT_IMPLEMENTATION_HAS_UNIMPLEMENTED_METHODS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_CHILD_ELEMENT_IMPLEMENTATION_IMPLEMENTS_MORE_THAN_ONE_WEB_CHILD_ELEMENTS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_MAPPED_BLOCK_IMPLEMENTATION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_PAGE_IMPLEMENTATION_NOT_DECLARED;
import static java.lang.reflect.Modifier.*;
import static java.lang.reflect.Modifier.isInterface;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;


public class WebElementInitializer {

    private final WebElementsConfiguration configuration;

    public WebElementInitializer(WebElementsConfiguration configuration) {
        this.configuration = configuration;
    }

    public WebPage initWebPageInstance(Class<? extends WebPage> webPageClass) {
        Class<? extends WebPage> webPageImplementation = configuration.getWebPageImplementation();
        if (webPageImplementation == null) {
            throw new ElementImplementationException(WEB_PAGE_IMPLEMENTATION_NOT_DECLARED.getMessage());
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webPageClass});
        enhancer.setSuperclass(webPageImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webPageImplementation));
        return (WebPage) enhancer.create();
    }

    public WebBlock initWebBlock(Class<? extends WebBlock> webBlockClass) {
        Class<? extends AbstractWebBlock> webBlockImplementation = configuration.getWebBlockImplementation();
        if (webBlockImplementation == null) {
            throw new ElementImplementationException(WEB_BLOCK_IMPLEMENTATION_NOT_DECLARED.getMessage());
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webBlockImplementation));
        return (WebBlock) enhancer.create();
    }

    public WebMappedBlock initWebMappedBlock(Class<? extends WebMappedBlock> webBlockClass) {
        Class<? extends AbstractWebMappedBlock> webBlockImplementation = configuration.getWebMappedBlockImplementation();
        if (webBlockImplementation == null) {
            throw new ElementImplementationException(WEB_MAPPED_BLOCK_IMPLEMENTATION_NOT_DECLARED.getMessage());
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webBlockClass});
        enhancer.setSuperclass(webBlockImplementation);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE, new WebParentElementInvocationHandler()});
        enhancer.setCallbackFilter(new WebParentElementCallbackFilter(webBlockImplementation));
        return (WebMappedBlock) enhancer.create();
    }

    // TODO: Подумать как быть со статик методами в интерфейсах
    public WebChildElement initWebChildElement(Class<? extends WebChildElement> webChildElementClass) {
        if (!isInterface(webChildElementClass.getModifiers())) {
            if (isAbstract(webChildElementClass.getModifiers())) {
                throw new ElementImplementationException(ABSTRACT_WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_ALLOWED
                        .getMessage(webChildElementClass.getCanonicalName()));
            }
            return newInstance(webChildElementClass);
        }
        Class<? extends AbstractWebChildElement> webChildElementImplementation = configuration.getElementImplementations().get(webChildElementClass);
        if (webChildElementImplementation != null) {
            return newInstance(webChildElementImplementation);
        }
        Class<? extends WebChildElement> interfaceImplementationClass = findInterfaceImplementation(webChildElementClass);
        // Создаем элемент через Enhancer
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[] {webChildElementClass});
        enhancer.setSuperclass(interfaceImplementationClass);
        enhancer.setCallbacks(new Callback[] {NoOp.INSTANCE});
        enhancer.setCallbackFilter(method -> 0);
        return (WebChildElement) enhancer.create();
    }

    protected Class<? extends WebChildElement> findInterfaceImplementation(Class<? extends WebChildElement> webChildElementClass) {

        Class<? extends WebChildElement> processedInterface = webChildElementClass;
        Class<? extends WebChildElement> processedInterfaceImplementation = null;

        while (processedInterfaceImplementation == null) {
            processedInterfaceImplementation = configuration.getElementImplementations().get(processedInterface);
            if (processedInterfaceImplementation != null) {
                return processedInterfaceImplementation;
            }
            processedInterface = findAncestorInterfaceImplementation(processedInterface);
        }

        throw new ElementImplementationException(WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_DECLARED
                .getMessage(webChildElementClass.getCanonicalName()));
    }

    protected Class<? extends WebChildElement> findAncestorInterfaceImplementation(Class<? extends WebChildElement> webChildElementClass) {

        List<Class<? extends WebChildElement>> ancestorInterfaces = Arrays.stream(webChildElementClass.getInterfaces())
                .map(this::checkInterface)
                .filter(WebChildElement.class::isAssignableFrom)
                .map(inheritedInterface -> (Class<? extends WebChildElement>) inheritedInterface)
                .collect(toList());
        // TODO: В принципе, если элемент экстендит 2 разных элемента, то это допустимый сценарий. Нужно это реализовать и написать тесты на такие элементы
        if (ancestorInterfaces.size() > 1) {
            throw new ElementImplementationException(WEB_CHILD_ELEMENT_IMPLEMENTATION_IMPLEMENTS_MORE_THAN_ONE_WEB_CHILD_ELEMENTS
                    .getMessage(webChildElementClass.getCanonicalName()));
        }
        if (ancestorInterfaces.size() == 0) {
            throw new ElementImplementationException(WEB_CHILD_ELEMENT_IMPLEMENTATION_NOT_DECLARED
                    .getMessage(webChildElementClass.getCanonicalName()));
        }
        return ancestorInterfaces.get(0);
    }

    protected Class<?> checkInterface(Class<?> processedInterface) {
        boolean checkResult = Arrays.stream(processedInterface.getDeclaredMethods()).allMatch(Method::isDefault)
                || configuration.getElementImplementations().get(processedInterface) != null;
        if (checkResult) {
            return processedInterface;
        }
        throw new ElementImplementationException(WEB_CHILD_ELEMENT_IMPLEMENTATION_HAS_UNIMPLEMENTED_METHODS
                .getMessage(processedInterface.getCanonicalName()));
    }








}
