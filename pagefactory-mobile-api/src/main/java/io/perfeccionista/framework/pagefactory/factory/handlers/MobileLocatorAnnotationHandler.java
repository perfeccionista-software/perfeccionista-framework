package io.perfeccionista.framework.pagefactory.factory.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocatorStrategy;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.LOCATOR_STRATEGY_VALIDATION_FAILED;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.ANDROID;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.IOS;
import static io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocatorStrategy.*;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.StringUtils.isNotBlank;

public class MobileLocatorAnnotationHandler {

    private MobileLocatorAnnotationHandler() {
    }

    public static @NotNull MobileLocatorRegistry createMobileLocatorRegistryFor(@NotNull DeviceType deviceType,
                                                                                @NotNull Class<? extends MobilePage> mobileElementClass) {
        Map<String, MobileLocatorHolder> mobileLocators = new HashMap<>();
        if (ANDROID == deviceType) {
            findAllRepeatableAnnotationsInHierarchy(AndroidLocator.class, MobilePage.class, mobileElementClass)
                    .forEach(mobileLocator -> mobileLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
        } else if (IOS == deviceType) {
            findAllRepeatableAnnotationsInHierarchy(IosLocator.class, MobilePage.class, mobileElementClass)
                    .forEach(mobileLocator -> mobileLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
        }
        return MobileLocatorRegistry.of(mobileLocators);
    }

    public static @NotNull MobileLocatorRegistry createMobileLocatorRegistryFor(@NotNull DeviceType deviceType,
                                                                                @NotNull MobileChildElement mobileChildElement,
                                                                                @NotNull Method elementMethod,
                                                                                @NotNull MobilePageFactoryPreferences configuration) {
        if (ANDROID == deviceType) {
            Map<String, MobileLocatorHolder> androidLocators = configuration
                    .getMobileLocatorConfiguration(mobileChildElement.getClass())
                    .androidLocatorsAsMap();
            findAllRepeatableAnnotationsInHierarchy(AndroidLocator.class, MobileChildElement.class, mobileChildElement.getClass())
                    .forEach(mobileLocator -> androidLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            findRepeatableAnnotations(elementMethod, AndroidLocator.class)
                    .forEach(mobileLocator -> androidLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            return MobileLocatorRegistry.of(androidLocators);
        } else if (IOS == deviceType) {
            Map<String, MobileLocatorHolder> iosLocators = configuration
                    .getMobileLocatorConfiguration(mobileChildElement.getClass())
                    .iosLocatorsAsMap();
            findAllRepeatableAnnotationsInHierarchy(IosLocator.class, MobileChildElement.class, mobileChildElement.getClass())
                    .forEach(mobileLocator -> iosLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            findRepeatableAnnotations(elementMethod, IosLocator.class)
                    .forEach(mobileLocator -> iosLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            return MobileLocatorRegistry.of(iosLocators);
        }
        return MobileLocatorRegistry.empty();
    }

    public static @NotNull MobileLocatorRegistry createMobileLocatorRegistryFor(@NotNull DeviceType deviceType,
                                                                                @NotNull MobileChildElement mobileChildElement,
                                                                                @NotNull MobilePageFactoryPreferences configuration) {
        if (ANDROID == deviceType) {
            Map<String, MobileLocatorHolder> androidLocators = configuration
                    .getMobileLocatorConfiguration(mobileChildElement.getClass())
                    .androidLocatorsAsMap();
            findAllRepeatableAnnotationsInHierarchy(AndroidLocator.class, MobileChildElement.class, mobileChildElement.getClass())
                    .forEach(mobileLocator -> androidLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            return MobileLocatorRegistry.of(androidLocators);
        } else if (IOS == deviceType) {
            Map<String, MobileLocatorHolder> iosLocators = configuration
                    .getMobileLocatorConfiguration(mobileChildElement.getClass())
                    .iosLocatorsAsMap();
            findAllRepeatableAnnotationsInHierarchy(IosLocator.class, MobileChildElement.class, mobileChildElement.getClass())
                    .forEach(mobileLocator -> iosLocators.put(mobileLocator.component(), createMobileLocatorHolder(mobileLocator)));
            return MobileLocatorRegistry.of(iosLocators);
        }
        return MobileLocatorRegistry.empty();
    }

    public static @NotNull MobileLocatorHolder createMobileLocatorHolder(@NotNull AndroidLocator mobileLocator) {
        Optional<MobileLocatorHolder> optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileLocator);
        if (optionalMobileLocatorHolder.isPresent()) {
            MobileLocatorHolder mobileLocatorHolder = optionalMobileLocatorHolder.get();
            mobileLocatorHolder.setSingle(mobileLocator.single());
            mobileLocatorHolder.setStrictSearch(mobileLocator.strictSearch());
            mobileLocatorHolder.setOnlyWithinParent(mobileLocator.onlyWithinParent());
            return mobileLocatorHolder;
        }
        throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                .addLastAttachmentEntry(JsonAttachmentEntry.of("MobileLocator", mobileLocatorToJson(mobileLocator)));
    }

    public static @NotNull MobileLocatorHolder createMobileLocatorHolder(@NotNull IosLocator mobileLocator) {
        Optional<MobileLocatorHolder> optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileLocator);
        if (optionalMobileLocatorHolder.isPresent()) {
            MobileLocatorHolder mobileLocatorHolder = optionalMobileLocatorHolder.get();
            mobileLocatorHolder.setSingle(mobileLocator.single());
            mobileLocatorHolder.setStrictSearch(mobileLocator.strictSearch());
            mobileLocatorHolder.setOnlyWithinParent(mobileLocator.onlyWithinParent());
            return mobileLocatorHolder;
        }
        throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                .addLastAttachmentEntry(JsonAttachmentEntry.of("MobileLocator", mobileLocatorToJson(mobileLocator)));
    }

    public static Optional<MobileLocatorHolder> createOptionalMobileLocatorHolder(@NotNull AndroidLocator mobileLocator) {
        MobileLocatorHolder mobileLocatorHolder = null;
        if (isNotBlank(mobileLocator.id())) {
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), ID, mobileLocator.id());
        }
        if (isNotBlank(mobileLocator.xpath())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), XPATH, mobileLocator.xpath());
        }
        if (isNotBlank(mobileLocator.name())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), NAME, mobileLocator.name());
        }
        if (isNotBlank(mobileLocator.tagName())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), TAG_NAME, mobileLocator.tagName());
        }
        if (isNotBlank(mobileLocator.className())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), CLASS_NAME, mobileLocator.className());
        }
        if (isNotBlank(mobileLocator.accessibilityId())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), ACCESSIBILITY_ID, mobileLocator.accessibilityId());
        }
        if (isNotBlank(mobileLocator.text())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), TEXT, mobileLocator.text());
        }
        if (isNotBlank(mobileLocator.containsText())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), CONTAINS_TEXT, mobileLocator.containsText());
        }
        if (isNotBlank(mobileLocator.androidViewTag())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), ANDROID_VIEW_TAG, mobileLocator.androidViewTag());
        }
        if (isNotBlank(mobileLocator.androidDataMatcher())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = AndroidLocatorHolder.of(mobileLocator.component(), ANDROID_DATA_MATCHER, mobileLocator.androidDataMatcher());
        }

        if (Objects.isNull(mobileLocatorHolder)) {
            return Optional.empty();
        }
        mobileLocatorHolder.setSingle(mobileLocator.single())
                .setStrictSearch(mobileLocator.strictSearch())
                .setOnlyWithinParent(mobileLocator.onlyWithinParent());
        // TODO: Добавить когда мобилки тоже будут поддерживать действия при обращении к локаторам
//        for (Class<? extends EndpointHandler<Void>> endpointHandlerClass : webLocator.invokeOnCall()) {
//            webLocatorHolder.addInvokedOnCallFunction(newInstance(endpointHandlerClass));
//        }
        return Optional.of(mobileLocatorHolder);
    }

    public static Optional<MobileLocatorHolder> createOptionalMobileLocatorHolder(@NotNull IosLocator mobileLocator) {
        MobileLocatorHolder mobileLocatorHolder = null;
        if (isNotBlank(mobileLocator.accessibilityId())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.ACCESSIBILITY_ID, mobileLocator.accessibilityId());
        }
        if (isNotBlank(mobileLocator.className())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.CLASS_NAME, mobileLocator.className());
        }
        if (isNotBlank(mobileLocator.id())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.ID, mobileLocator.id());
        }
        if (isNotBlank(mobileLocator.name())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.NAME, mobileLocator.name());
        }
        if (isNotBlank(mobileLocator.xpath())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.XPATH, mobileLocator.xpath());
        }
        if (isNotBlank(mobileLocator.image())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.IMAGE, mobileLocator.image());
        }
        if (isNotBlank(mobileLocator.iosClassChain())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.IOS_CLASS_CHAIN, mobileLocator.iosClassChain());
        }
        if (isNotBlank(mobileLocator.iosNsPredicate())) {
            checkMobileLocatorStrategyIsEmpty(mobileLocatorHolder, mobileLocator);
            mobileLocatorHolder = IosLocatorHolder.of(mobileLocator.component(), IosLocatorStrategy.IOS_NS_PREDICATE, mobileLocator.iosNsPredicate());
        }
        return Optional.ofNullable(mobileLocatorHolder);
    }

    private static void checkMobileLocatorStrategyIsEmpty(@Nullable MobileLocatorHolder mobileLocatorHolder, AndroidLocator mobileLocator) {
        if (mobileLocatorHolder != null) {
            throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("MobileLocator", mobileLocatorToJson(mobileLocator)));
        }
    }

    private static void checkMobileLocatorStrategyIsEmpty(@Nullable MobileLocatorHolder mobileLocatorHolder, IosLocator mobileLocator) {
        if (mobileLocatorHolder != null) {
            throw LocatorProcessing.exception(LOCATOR_STRATEGY_VALIDATION_FAILED.getMessage())
                    .addLastAttachmentEntry(JsonAttachmentEntry.of("MobileLocator", mobileLocatorToJson(mobileLocator)));
        }
    }

    private static JsonNode mobileLocatorToJson(@NotNull AndroidLocator mobileLocator) {
        return createObjectNode()
                .put("component", mobileLocator.component())
                .put("id", mobileLocator.id())
                .put("xpath", mobileLocator.xpath())
                .put("name", mobileLocator.name())
                .put("tagName", mobileLocator.tagName())
                .put("className", mobileLocator.className())
                .put("accessibilityId", mobileLocator.accessibilityId())
                .put("text", mobileLocator.text())
                .put("containsText", mobileLocator.containsText())
                .put("androidViewTag", mobileLocator.androidViewTag())
                .put("androidDataMatcher", mobileLocator.androidDataMatcher())
                .put("single", mobileLocator.single())
                .put("strictSearch", mobileLocator.strictSearch())
                .put("onlyWithinParent", mobileLocator.onlyWithinParent());
    }

    private static JsonNode mobileLocatorToJson(@NotNull IosLocator mobileLocator) {
        return createObjectNode()
                .put("component", mobileLocator.component())
                .put("accessibilityId", mobileLocator.accessibilityId())
                .put("className", mobileLocator.className())
                .put("id", mobileLocator.id())
                .put("name", mobileLocator.name())
                .put("xpath", mobileLocator.xpath())
                .put("image", mobileLocator.image())
                .put("iosClassChain", mobileLocator.iosClassChain())
                .put("iosNsPredicate", mobileLocator.iosNsPredicate())
                .put("single", mobileLocator.single())
                .put("strictSearch", mobileLocator.strictSearch())
                .put("onlyWithinParent", mobileLocator.onlyWithinParent());
    }

}
