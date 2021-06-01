package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectMobileLocatorValue;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.exceptions.UnsupportedSearchLogic;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocatorStrategy;
import io.perfeccionista.framework.pagefactory.operation.MobileLocatorProcessingResult;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperation;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperationResult;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsDisplayedOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_STRATEGY_UNSUPPORTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.INCORRECT_MOBILE_LOCATOR_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PagefactoryMobileAppiumMessages.UNSUPPORTED_SEARCH_LOGIC;

public class DefaultAppiumAndroidOperationExecutor implements MobileDeviceOperationExecutor {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    protected boolean traceSearch = false;

    public DefaultAppiumAndroidOperationExecutor(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    public DefaultAppiumAndroidOperationExecutor withTraceSearch() {
        this.traceSearch = true;
        return this;
    }

    @Override
    public <T> MobileElementOperationResult<T> executeMobileElementOperation(@NotNull MobileElementOperation<T> operation) {

        MobileLocatorChain locatorChain = operation.getLocatorChain();
        MobileElementOperationType<T> operationType = operation.getOperationType();
        EndpointHandler<T> elementHandler = operationType.getEndpointHandler();

        Map<String, Map<Integer, MobileLocatorProcessingResult>> locatorProcessingResults = new HashMap<>();

        try {
            Deque<MobileLocatorHolder> locatorHolders = new ArrayDeque<>(locatorChain.getAllLocators());
            AndroidLocatorProcessingResult result = AndroidLocatorProcessingResult.empty();
            while (!locatorHolders.isEmpty()) {
                MobileLocatorHolder processedLocatorHolder = locatorHolders.removeFirst();
                boolean singleParent = result.isSingle();
                boolean singleChild = processedLocatorHolder.isSingle();
                if (singleParent && singleChild) {
                    result = findSingleFromSingle(result, processedLocatorHolder);
                } else if (singleParent && !singleChild) {
                    result = findMultipleFromSingle(result, processedLocatorHolder);
                } else if (!singleParent && singleChild) {
                    result = findSingleFromMultiple(result, processedLocatorHolder);
                } else {
                    throw UnsupportedSearchLogic.exception(UNSUPPORTED_SEARCH_LOGIC.getMessage())
                            .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Chain", locatorChain.toJson()));
                }

                Map<Integer, MobileLocatorProcessingResult> locatorProcessingResult = result.getElements().entrySet().stream()
                        .map(entry -> Map.entry(entry.getKey(), MobileLocatorProcessingResult.of(entry.getKey(), Objects.nonNull(entry.getValue()), entry.getValue() != null ? entry.getValue().getId() : null, null)))
                        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
                locatorProcessingResults.put(processedLocatorHolder.getLocatorId(), locatorProcessingResult);

                // Если мы поймали ошибку при поиске последнего элемента в цепочке
                if (!result.isSuccess()) {
                    if (locatorHolders.isEmpty()) {
                        if (operationType instanceof MobileGetIsPresentOperationType || operationType instanceof MobileGetIsDisplayedOperationType) {
                            break;
                        }
                        throw result.getException();
                    }
                    throw result.getException();
                }
            }

            Map<Integer, T> handledValues = new HashMap<>();
            for (Entry<Integer, AndroidElement> entry : result.getElements().entrySet()) {
                T handledValue = elementHandler.handle(entry.getValue());
                handledValues.put(entry.getKey(), handledValue);
            }

            String pageSource = "empty";
            if (operation.isWithPageSource()) {
                pageSource = instance.getPageSource();
            }

            return MobileElementOperationResult.of(locatorProcessingResults, handledValues, pageSource);
        } catch (RuntimeException exception) {
            String pageSource = "empty";
            if (operation.isWithPageSource()) {
                pageSource = exceptionMapper.map(instance::getPageSource).getResult();
                if (pageSource == null) {
                    pageSource = "not available";
                }
            }
            return MobileElementOperationResult.of(locatorProcessingResults, exceptionMapper, exception, pageSource);
        }
    }

    @Override
    public <T> MobilePageOperationResult<T> executeMobilePageOperation(MobilePageOperation<T> operation) {
//        MobileLocatorChain locatorChain = operation.getLocatorChain();
//        Map<String, Map<Integer, MobileLocatorProcessingResult>> locatorProcessingResults = new HashMap<>();
//        ExceptionMapperResult<MobilePageOperationResult<T>> exceptionMapperResult;
//        if (locatorChain.isEmpty()) {
//            exceptionMapperResult = exceptionMapper
//                    .mapPageException(() -> {
//                        MobileElementOperationType<T> operationType = operation.getOperationType();
//                        EndpointHandler<T> elementHandler = operationType.getEndpointHandler();
//
//                        // TODO:
//                        AndroidElement rootElement = instance.findElementByXPath("//*");
//                        Map<Integer, T> handledValues = new HashMap<>();
//                        handledValues.put(-1, elementHandler.handle(rootElement));
//
//                        String pageSource = "empty";
//                        if (operation.isWithPageSource()) {
//                            pageSource = instance.getPageSource();
//                        }
//
//                        return MobilePageOperationResult.of(locatorProcessingResults, handledValues, pageSource);
//                    }, operation.getPage());
//
//        } else {
//            exceptionMapperResult = exceptionMapper
//                    .mapPageException(() -> {
//                        Deque<MobileLocatorHolder> locatorHolders = new ArrayDeque<>(locatorChain.getAllLocators());
//                        AndroidLocatorProcessingResult result = AndroidLocatorProcessingResult.empty();
//                        while (!locatorHolders.isEmpty()) {
//                            MobileLocatorHolder processedLocatorHolder = locatorHolders.removeFirst();
//                            boolean singleParent = result.isSingle();
//                            boolean singleChild = processedLocatorHolder.isSingle();
//                            if (singleParent && singleChild) {
//                                result = findSingleFromSingle(result, processedLocatorHolder);
//                            } else if (singleParent && !singleChild) {
//                                result = findMultipleFromSingle(result, processedLocatorHolder);
//                            } else if (!singleParent && singleChild) {
//                                result = findSingleFromMultiple(result, processedLocatorHolder);
//                            } else {
//                                throw UnsupportedSearchLogic.exception(UNSUPPORTED_SEARCH_LOGIC.getMessage())
//                                        .addLastAttachmentEntry(JsonAttachmentEntry.of("Locator Chain", locatorChain.toJson()));
//                            }
//                        }
//                        MobileElementOperationType<T> operationType = operation.getOperationType();
//                        EndpointHandler<T> elementHandler = operationType.getEndpointHandler();
//
//                        Map<Integer, T> handledValues = new HashMap<>();
//                        for (Entry<Integer, AndroidElement> entry : result.getElements().entrySet()) {
//                            T handledValue = elementHandler.handle(entry.getValue());
//                            handledValues.put(entry.getKey(), handledValue);
//                        }
//
//                        String pageSource = "empty";
//                        if (operation.isWithPageSource()) {
//                            pageSource = instance.getPageSource();
//                        }
//
//                        return MobilePageOperationResult.of(locatorProcessingResults, handledValues, pageSource);
//                    }, operation.getPage());
//        }
//        if (exceptionMapperResult.isException()) {
//            String pageSource = "empty";
//            if (operation.isWithPageSource()) {
//                pageSource = exceptionMapper.map(instance::getPageSource).getResult();
//            }
//            return MobilePageOperationResult.of(locatorProcessingResults, exceptionMapperResult.getException(), pageSource);
//        }
//        return exceptionMapperResult.getResult();
        return null;
    }


    protected AndroidLocatorProcessingResult findSingleFromSingle(@NotNull AndroidLocatorProcessingResult previousResult,
                                                                  @NotNull MobileLocatorHolder locator) {
        // TODO: Логика работает неправильно, если предыдущий элемент не найден (например, нет элементов списка)
        if (previousResult.size() == 0) {
            return findElementFromRoot(locator);
        } else {
            return findElementFromOtherElement(previousResult, locator);
        }
    }

    protected AndroidLocatorProcessingResult findSingleFromMultiple(@NotNull AndroidLocatorProcessingResult previousResult,
                                                                    @NotNull MobileLocatorHolder locator) {
        return findElementsFromOtherElements(previousResult, locator);
    }

    protected AndroidLocatorProcessingResult findMultipleFromSingle(@NotNull AndroidLocatorProcessingResult previousResult,
                                                                    @NotNull MobileLocatorHolder locator) {
        // TODO: Логика работает неправильно, если предыдущий элемент не найден (например, нет элементов списка)
        if (previousResult.size() == 0) {
            return findElementsFromRoot(locator);
        } else {
            return findElementsFromOtherElement(previousResult, locator);
        }
    }


    protected AndroidLocatorProcessingResult findElementFromRoot(MobileLocatorHolder locator) {
        AndroidLocatorStrategy locatorStrategy = (AndroidLocatorStrategy) locator.getLocatorStrategy();
        String locatorValue = locator.getLocatorValue();
        HashMap<Integer, AndroidElement> elements = new HashMap<>();
        try {
            AndroidElement result;
            switch (locatorStrategy) {
                case SELF_NODE:
                    throw IncorrectMobileLocatorValue.exception(INCORRECT_MOBILE_LOCATOR_VALUE.getMessage());
                case ID:
                    result = instance.findElementById(locatorValue);
                    break;
                case XPATH:
                    result = instance.findElementByXPath(locatorValue);
                    break;
                case NAME:
                    result = instance.findElementByName(locatorValue);
                    break;
                case TAG_NAME:
                    result = instance.findElementByTagName(locatorValue);
                    break;
                case CLASS_NAME:
                    result = instance.findElementByClassName(locatorValue);
                    break;
                case ACCESSIBILITY_ID:
                    result = instance.findElementByAccessibilityId(locatorValue);
                    break;
                case TEXT:
                    result = instance.findElementByXPath(".//*[@text = '" + locatorValue + "']");
                    break;
                case CONTAINS_TEXT:
                    result = instance.findElementByXPath(".//*[contains(@text, '" + locatorValue + "']");
                    break;
                case ANDROID_VIEW_TAG:
                    result = instance.findElementByAndroidViewTag(locatorValue);
                    break;
                case ANDROID_DATA_MATCHER:
                    result = instance.findElementByAndroidDataMatcher(locatorValue);
                    break;
                default:
                    throw LocatorProcessing.exception(LOCATOR_STRATEGY_UNSUPPORTED.getMessage(locatorStrategy, AndroidDriver.class));
            }
            return AndroidLocatorProcessingResult.of(result);
        } catch (NoSuchElementException exception) {
            elements.put(-1, null);
            return AndroidLocatorProcessingResult.of(elements, exception);
        }
    }

    protected AndroidLocatorProcessingResult findElementsFromRoot(MobileLocatorHolder locator) {
        AndroidLocatorStrategy locatorStrategy = (AndroidLocatorStrategy) locator.getLocatorStrategy();
        String locatorValue = locator.getLocatorValue();
        HashMap<Integer, AndroidElement> elements = new HashMap<>();
        try {
            List<AndroidElement> results;
            switch (locatorStrategy) {
                case SELF_NODE:
                    throw IncorrectMobileLocatorValue.exception(INCORRECT_MOBILE_LOCATOR_VALUE.getMessage());
                case ID:
                    results = instance.findElementsById(locatorValue);
                    break;
                case XPATH:
                    results = instance.findElementsByXPath(locatorValue);
                    break;
                case NAME:
                    results = instance.findElementsByName(locatorValue);
                    break;
                case TAG_NAME:
                    results = instance.findElementsByTagName(locatorValue);
                    break;
                case CLASS_NAME:
                    results = instance.findElementsByClassName(locatorValue);
                    break;
                case ACCESSIBILITY_ID:
                    results = instance.findElementsByAccessibilityId(locatorValue);
                    break;
                case TEXT:
                    results = instance.findElementsByXPath(".//*[@text = '" + locatorValue + "']");
                    break;
                case CONTAINS_TEXT:
                    results = instance.findElementsByXPath(".//*[contains(@text, '" + locatorValue + "']");
                    break;
                case ANDROID_VIEW_TAG:
                    results = instance.findElementsByAndroidViewTag(locatorValue);
                    break;
                case ANDROID_DATA_MATCHER:
                    results = instance.findElementsByAndroidDataMatcher(locatorValue);
                    break;
                default:
                    throw LocatorProcessing.exception(LOCATOR_STRATEGY_UNSUPPORTED.getMessage(locatorStrategy, AndroidDriver.class));
            }
            for (int i = 0; i < results.size(); i++) {
                elements.put(i, results.get(i));
            }
            return AndroidLocatorProcessingResult.of(elements);
        } catch (NoSuchElementException exception) {
            return AndroidLocatorProcessingResult.of(elements, exception);
        }
    }

    protected AndroidLocatorProcessingResult findElementFromOtherElement(AndroidLocatorProcessingResult parentResult, MobileLocatorHolder locator) {
        AndroidElement parent = parentResult.getElement();

        AndroidLocatorStrategy locatorStrategy = (AndroidLocatorStrategy) locator.getLocatorStrategy();
        String locatorValue = locator.getLocatorValue();
        Integer index = locator.getIndex();
        HashMap<Integer, AndroidElement> elements = new HashMap<>();
        try {
            AndroidElement result;
            switch (locatorStrategy) {
                case SELF_NODE:
                    return parentResult;
                case ID:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsById(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            // TODO Реализовать логику работы с индексом для остальных стратегий
                            //  Добавить найденные элементы в ответ для дальнейшего логирования?
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementById(locatorValue);
                    }
                    break;
                case XPATH:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByXPath(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByXPath(locatorValue);
                    }
                    break;
                case NAME:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByName(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByName(locatorValue);
                    }
                    break;
                case TAG_NAME:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByTagName(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByTagName(locatorValue);
                    }
                    break;
                case CLASS_NAME:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByClassName(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByClassName(locatorValue);
                    }
                    break;
                case ACCESSIBILITY_ID:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByAccessibilityId(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByAccessibilityId(locatorValue);
                    }
                    break;
                case TEXT:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByXPath(".//*[@text = '" + locatorValue + "']");
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByXPath(".//*[@text = '" + locatorValue + "']");
                    }
                    break;
                case CONTAINS_TEXT:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByXPath(".//*[contains(@text, '" + locatorValue + "']");
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByXPath(".//*[contains(@text, '" + locatorValue + "']");
                    }
                    break;
                case ANDROID_VIEW_TAG:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByAndroidViewTag(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByAndroidViewTag(locatorValue);
                    }
                    break;
                case ANDROID_DATA_MATCHER:
                    if (index > -1) {
                        List<MobileElement> foundedElements = parent.findElementsByAndroidDataMatcher(locatorValue);
                        if (foundedElements.size() > index) {
                            result = (AndroidElement) foundedElements.get(index);
                        } else {
                            throw new NoSuchElementException("Element with index " + index + " not found");
                        }
                    } else {
                        result = (AndroidElement) parent.findElementByAndroidDataMatcher(locatorValue);
                    }
                    break;
                default:
                    throw LocatorProcessing.exception(LOCATOR_STRATEGY_UNSUPPORTED.getMessage(locatorStrategy, AndroidDriver.class));
            }
            return AndroidLocatorProcessingResult.of(result);
        } catch (NoSuchElementException exception) {
            elements.put(-1, null);
            return AndroidLocatorProcessingResult.of(elements, exception);
        }
    }

    protected AndroidLocatorProcessingResult findElementsFromOtherElement(AndroidLocatorProcessingResult parentResult, MobileLocatorHolder locator) {
        AndroidElement parent = parentResult.getElement();

        AndroidLocatorStrategy locatorStrategy = (AndroidLocatorStrategy) locator.getLocatorStrategy();
        String locatorValue = locator.getLocatorValue();
        HashMap<Integer, AndroidElement> elements = new HashMap<>();
        try {
            List<AndroidElement> results;
            switch (locatorStrategy) {
                case SELF_NODE:
                    return parentResult;
                case ID:
                    results = parent.findElementsById(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case XPATH:
                    results = parent.findElementsByXPath(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case NAME:
                    results = parent.findElementsByName(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case TAG_NAME:
                    results = parent.findElementsByTagName(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case CLASS_NAME:
                    results = parent.findElementsByClassName(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case ACCESSIBILITY_ID:
                    results = parent.findElementsByAccessibilityId(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case TEXT:
                    results = parent.findElementsByXPath(".//*[@text = '" + locatorValue + "']").stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case CONTAINS_TEXT:
                    results = parent.findElementsByXPath(".//*[contains(@text, '" + locatorValue + "']").stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case ANDROID_VIEW_TAG:
                    results = parent.findElementsByAndroidViewTag(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                case ANDROID_DATA_MATCHER:
                    results = parent.findElementsByAndroidDataMatcher(locatorValue).stream()
                            .map(element -> (AndroidElement) element).collect(Collectors.toList());
                    break;
                default:
                    throw LocatorProcessing.exception(LOCATOR_STRATEGY_UNSUPPORTED.getMessage(locatorStrategy, AndroidDriver.class));
            }
            for (int i = 0; i < results.size(); i++) {
                elements.put(i, results.get(i));
            }
            return AndroidLocatorProcessingResult.of(elements);
        } catch (NoSuchElementException exception) {
            return AndroidLocatorProcessingResult.of(elements, exception);
        }
    }


    protected AndroidLocatorProcessingResult findElementsFromOtherElements(AndroidLocatorProcessingResult parentResult, MobileLocatorHolder locator) {
        Map<Integer, AndroidElement> parentElements = parentResult.getElements();

        AndroidLocatorStrategy locatorStrategy = (AndroidLocatorStrategy) locator.getLocatorStrategy();
        String locatorValue = locator.getLocatorValue();
        HashMap<Integer, AndroidElement> elements = new HashMap<>();

        for (Map.Entry<Integer, AndroidElement> parentEntry : parentElements.entrySet()) {
            try {
                AndroidElement result;
                switch (locatorStrategy) {
                    case SELF_NODE:
                        result = parentEntry.getValue();
                        break;
                    case ID:
                        result = (AndroidElement) parentEntry.getValue().findElementById(locatorValue);
                        break;
                    case XPATH:
                        result = (AndroidElement) parentEntry.getValue().findElementByXPath(locatorValue);
                        break;
                    case NAME:
                        result = (AndroidElement) parentEntry.getValue().findElementByName(locatorValue);
                        break;
                    case TAG_NAME:
                        result = (AndroidElement) parentEntry.getValue().findElementByTagName(locatorValue);
                        break;
                    case CLASS_NAME:
                        result = (AndroidElement) parentEntry.getValue().findElementByClassName(locatorValue);
                        break;
                    case ACCESSIBILITY_ID:
                        result = (AndroidElement) parentEntry.getValue().findElementByAccessibilityId(locatorValue);
                        break;
                    case TEXT:
                        result = (AndroidElement) parentEntry.getValue().findElementByXPath(".//*[@text = '" + locatorValue + "']");
                        break;
                    case CONTAINS_TEXT:
                        result = (AndroidElement) parentEntry.getValue().findElementByXPath(".//*[contains(@text, '" + locatorValue + "']");
                        break;
                    case ANDROID_VIEW_TAG:
                        result = (AndroidElement) parentEntry.getValue().findElementByAndroidViewTag(locatorValue);
                        break;
                    case ANDROID_DATA_MATCHER:
                        result = (AndroidElement) parentEntry.getValue().findElementByAndroidDataMatcher(locatorValue);
                        break;
                    default:
                        throw LocatorProcessing.exception(LOCATOR_STRATEGY_UNSUPPORTED.getMessage(locatorStrategy, AndroidDriver.class));
                }
                elements.put(parentEntry.getKey(), result);
            } catch (NoSuchElementException exception) {
                return AndroidLocatorProcessingResult.of(elements, exception);
            }
        }
        return AndroidLocatorProcessingResult.of(elements);
    }

}
