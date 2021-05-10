package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.MobileAutocompleteImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileButton;
import io.perfeccionista.framework.pagefactory.elements.MobileButtonImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileCheckbox;
import io.perfeccionista.framework.pagefactory.elements.MobileCheckboxImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownListImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileImage;
import io.perfeccionista.framework.pagefactory.elements.MobileImageImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.MobileListImpl;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.MobileTableImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileText;
import io.perfeccionista.framework.pagefactory.elements.MobileTextAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.MobileTextAutocompleteImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.MobileTextDropDownListImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTextImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTextInput;
import io.perfeccionista.framework.pagefactory.elements.MobileTextInputImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.elements.MobileTextListImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTableImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTile;
import io.perfeccionista.framework.pagefactory.elements.MobileTileImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileToggleButton;
import io.perfeccionista.framework.pagefactory.elements.MobileToggleButtonImpl;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementStateAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetElementBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.states.CheckStringAttributeMobileElementStateExtractor;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumAndroidListScrollToVerticallyHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumCheckBooleanAttributeHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumCheckStringAttributeHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetCheckedAttributeHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetElementBoundsHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetStringAttributeHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumIsInFocusHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumScrollToHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumReplaceTextHandler;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileDropDownAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumClearHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumAndroidDoubleTapHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetColorHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetIsDisplayedHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetIsEnabledHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetIsOnTheScreenActionConfiguration;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetIsPresentHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetSelectedAttributeHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetLabelHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetScreenshotHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumGetTextHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumAndroidTapHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.AppiumTypeTextHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOUBLE_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.LIST_SCROLL_TO_VERTICALLY_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.LONG_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.REPLACE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SINGLE_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;

public class DefaultAppiumAndroidPageFactoryPreferences extends DefaultMobilePageFactoryPreferences {

    public DefaultAppiumAndroidPageFactoryPreferences() {
        super();
        setMobileMappedBlocks(getMobileMappedBlocks());
        setMobileElementImplementations(getMobileElementImplementations());
        setMobilePageActionConfigurations(getMobilePageActionConfigurations());
        setMobileElementActionConfigurations(getMobileElementActionConfigurations());
        setMobileElementPropertyConfigurations(getMobileElementPropertyConfigurations());
        setMobileElementStateConfigurations(getMobileElementStateConfigurations());
        setMobileLocatorConfigurations(getMobileLocatorConfigurations());
    }

    protected Map<Class<? extends MobileChildElement>, Class<? extends MobileBlock>> getMobileMappedBlocks() {
        Map<Class<? extends MobileChildElement>, Class<? extends MobileBlock>> mobileMappedBlocks = new HashMap<>();
//        mobileMappedBlocks.put(MobileRadioGroup.class, DefaultMobileRadioButtonBlock.class);
        mobileMappedBlocks.put(MobileTextAutocomplete.class, DefaultMobileTextBlock.class);
        mobileMappedBlocks.put(MobileTextDropDownList.class, DefaultMobileTextBlock.class);
        mobileMappedBlocks.put(MobileTextList.class, DefaultMobileTextBlock.class);
        return mobileMappedBlocks;
    }

    protected Map<Class<? extends MobileChildElement>, Class<? extends MobileChildElement>> getMobileElementImplementations() {
        Map<Class<? extends MobileChildElement>, Class<? extends MobileChildElement>> implementations = new HashMap<>();
        implementations.put(MobileAutocomplete.class, MobileAutocompleteImpl.class);
        implementations.put(MobileButton.class, MobileButtonImpl.class);
        implementations.put(MobileToggleButton.class, MobileToggleButtonImpl.class);
        implementations.put(MobileCheckbox.class, MobileCheckboxImpl.class);
        implementations.put(MobileDropDownList.class, MobileDropDownListImpl.class);
//        implementations.put(MobileFileInput.class, MobileFileInputImpl.class);
        implementations.put(MobileImage.class, MobileImageImpl.class);
//        implementations.put(MobileLink.class, MobileLinkImpl.class);
        implementations.put(MobileList.class, MobileListImpl.class);
//        implementations.put(MobileRadioButton.class, MobileRadioButtonImpl.class);
//        implementations.put(MobileRadioGroup.class, MobileRadioGroupImpl.class);
        implementations.put(MobileTable.class, MobileTableImpl.class);
        implementations.put(MobileTextAutocomplete.class, MobileTextAutocompleteImpl.class);
        implementations.put(MobileText.class, MobileTextImpl.class);
        implementations.put(MobileTextDropDownList.class, MobileTextDropDownListImpl.class);
        implementations.put(MobileTextInput.class, MobileTextInputImpl.class);
        implementations.put(MobileTextList.class, MobileTextListImpl.class);
        implementations.put(MobileTextTable.class, MobileTextTableImpl.class);
        implementations.put(MobileTile.class, MobileTileImpl.class);
        return implementations;
    }

    protected Map<Class<? extends MobilePage>, MobileEndpointHandlerConfiguration> getMobilePageActionConfigurations() {
        Map<Class<? extends MobilePage>, MobileEndpointHandlerConfiguration> actionConfigurations = new HashMap<>();
//        actionConfigurations.put(MobilePage.class, MobileEndpointHandlerConfiguration.builder()
//                .set(PARENT_SCROLL_TO_HORIZONTALLY_METHOD, AppiumAndroidParentScrollToHorizontallyHandler.class)
//                .set(PARENT_SCROLL_TO_VERTICALLY_METHOD, AppiumAndroidParentScrollToVerticallyHandler.class));
        return actionConfigurations;
    }

    protected Map<Class<? extends MobileChildElementBase>, MobileEndpointHandlerConfiguration> getMobileElementActionConfigurations() {
        Map<Class<? extends MobileChildElementBase>, MobileEndpointHandlerConfiguration> actionConfigurations = new HashMap<>();
        // Elements
//        actionConfigurations.put(MobileAutocomplete.class, MobileElementActionConfiguration.builder()
//                .set(GET_TEXT_METHOD, new MobileGetText()));
//        actionConfigurations.put(MobileImage.class, MobileElementActionConfiguration.builder()
//                .set(SAVE_TO_FILE_METHOD, new JsSaveImageToFile()));
//        actionConfigurations.put(MobileFileInput.class, MobileElementActionConfiguration.builder()
//                .set(GET_TEXT_METHOD, new JsGetTextFromValue())
//                .set(SET_FILENAME_METHOD, new SeleniumSendKeys()));                                 // SeleniumImpl
//        actionConfigurations.put(MobileTextAutocomplete.class, MobileElementActionConfiguration.builder()
//                .set(GET_TEXT_METHOD, new MobileGetText()));
//        actionConfigurations.put(MobileTextInput.class, MobileElementActionConfiguration.builder()
//                .set(GET_TEXT_METHOD, new MobileGetText()));
        actionConfigurations.put(MobileList.class, MobileEndpointHandlerConfiguration.builder()
//                .set(LIST_SCROLL_TO_HORIZONTALLY_METHOD, AppiumAndroidListScrollToHorizontallyHandler.class)                                             // SeleniumImpl
                .set(LIST_SCROLL_TO_VERTICALLY_METHOD, AppiumAndroidListScrollToVerticallyHandler.class));                                             // SeleniumImpl

        // Interfaces
        actionConfigurations.put(MobileInputTextAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(CLEAR_METHOD, AppiumClearHandler.class)
                .set(TYPE_TEXT_METHOD, AppiumTypeTextHandler.class)
                .set(REPLACE_TEXT_METHOD, AppiumReplaceTextHandler.class));
//                .set(SEND_KEY_EVENTS_METHOD, new SeleniumSendKeyEvents()));
        actionConfigurations.put(MobileDropDownAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_OPEN_METHOD, AppiumGetIsDisplayedHandler.class)
                .set(OPEN_METHOD, AppiumAndroidTapHandler.class)
                .set(CLOSE_METHOD, AppiumAndroidTapHandler.class));
        actionConfigurations.put(MobileGetColorAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_COLOR_METHOD, AppiumGetColorHandler.class));
        actionConfigurations.put(MobileGetElementBoundsAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_ELEMENT_BOUNDS_METHOD, AppiumGetElementBoundsHandler.class));
        actionConfigurations.put(MobileGetLabelAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_LABEL_METHOD, AppiumGetLabelHandler.class));
        actionConfigurations.put(MobileGetScreenshotAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_SCREENSHOT_METHOD, AppiumGetScreenshotHandler.class));
        actionConfigurations.put(MobileGetTextAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_TEXT_METHOD, AppiumGetTextHandler.class));
//        actionConfigurations.put(MobileHoverToAvailable.class, MobileElementActionConfiguration.builder()
//                .set(HOVER_TO_METHOD, new SeleniumHoverTo()));                                      // SeleniumImpl
        actionConfigurations.put(MobileIsDisplayedAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_DISPLAYED_METHOD, AppiumGetIsDisplayedHandler.class));
        actionConfigurations.put(MobileIsEnabledAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_ENABLED_METHOD, AppiumGetIsEnabledHandler.class));
        actionConfigurations.put(MobileIsInFocusAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_IN_FOCUS_METHOD, AppiumIsInFocusHandler.class));
        actionConfigurations.put(MobileIsOnTheScreenAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_ON_THE_SCREEN_METHOD, AppiumGetIsOnTheScreenActionConfiguration.class));
        actionConfigurations.put(MobileIsPresentAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_PRESENT_METHOD, AppiumGetIsPresentHandler.class));
        actionConfigurations.put(MobileIsSelectedAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_SELECTED_METHOD, AppiumGetCheckedAttributeHandler.class));
        actionConfigurations.put(MobileToggleButton.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_SELECTED_METHOD, AppiumGetSelectedAttributeHandler.class));
        actionConfigurations.put(MobileScrollToAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(SCROLL_TO_METHOD, AppiumScrollToHandler.class));
        actionConfigurations.put(MobileTapAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(SINGLE_TAP_METHOD, AppiumAndroidTapHandler.class)
                .set(LONG_TAP_METHOD, AppiumAndroidTapHandler.class)
                .set(DOUBLE_TAP_METHOD, AppiumAndroidDoubleTapHandler.class));
        actionConfigurations.put(MobileComponentAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(IS_COMPONENT_PRESENT_METHOD, AppiumGetIsPresentHandler.class)
                .set(IS_COMPONENT_DISPLAYED_METHOD, AppiumGetIsDisplayedHandler.class));
        actionConfigurations.put(MobileElementPropertyAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(GET_STRING_ATTRIBUTE_VALUE_METHOD, AppiumGetStringAttributeHandler.class));
        actionConfigurations.put(MobileElementStateAvailable.class, MobileEndpointHandlerConfiguration.builder()
                .set(CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD, AppiumCheckBooleanAttributeHandler.class)
                .set(CHECK_STRING_ATTRIBUTE_VALUE_METHOD, AppiumCheckStringAttributeHandler.class));
        return actionConfigurations;
    }

    protected Map<Class<? extends MobileChildElementBase>, MobileElementPropertyConfiguration> getMobileElementPropertyConfigurations() {
        Map<Class<? extends MobileChildElementBase>, MobileElementPropertyConfiguration> properties = new HashMap<>();
//        properties.put(MobileImage.class, MobileElementPropertyConfiguration.builder()
//                .set(IMAGE_SOURCE, MobileElementPropertyHolder.of(IMAGE_SOURCE, null, new SrcAttributeExtractor())));
//        properties.put(MobileButton.class, MobileElementPropertyConfiguration.builder()
//                .set(LINK, MobileElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
//        properties.put(MobileLink.class, MobileElementPropertyConfiguration.builder()
//                .set(LINK, MobileElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
//        MobileLocatorHolder inputLocatorHolder = MobileLocatorHolder.of(INPUT, CSS, "input[placeholder]");
//        properties.put(MobileAutocomplete.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
//        properties.put(MobileTextAutocomplete.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
//        properties.put(MobileTextInput.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
        return properties;
    }

    protected Map<Class<? extends MobileChildElementBase>, MobileElementStateConfiguration> getMobileElementStateConfigurations() {
        Map<Class<? extends MobileChildElementBase>, MobileElementStateConfiguration> states = new HashMap<>();
        states.put(MobileChildElement.class, MobileElementStateConfiguration.builder()
                .set("enabled", MobileElementStateHolder.of("enabled", null,
                        new CheckStringAttributeMobileElementStateExtractor(List.of("enabled", "true"))))
                .set("disabled", MobileElementStateHolder.of("disabled", null,
                        new CheckStringAttributeMobileElementStateExtractor(List.of("enabled", "false")))));
//        properties.put(MobileImage.class, MobileElementPropertyConfiguration.builder()
//                .set(IMAGE_SOURCE, MobileElementPropertyHolder.of(IMAGE_SOURCE, null, new SrcAttributeExtractor())));
//        properties.put(MobileButton.class, MobileElementPropertyConfiguration.builder()
//                .set(LINK, MobileElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
//        properties.put(MobileLink.class, MobileElementPropertyConfiguration.builder()
//                .set(LINK, MobileElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
//        MobileLocatorHolder inputLocatorHolder = MobileLocatorHolder.of(INPUT, CSS, "input[placeholder]");
//        properties.put(MobileAutocomplete.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
//        properties.put(MobileTextAutocomplete.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
//        properties.put(MobileTextInput.class, MobileElementPropertyConfiguration.builder()
//                .set(PLACEHOLDER, MobileElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
        return states;
    }

    protected Map<Class<? extends MobileChildElementBase>, MobileLocatorConfiguration> getMobileLocatorConfigurations() {
        Map<Class<? extends MobileChildElementBase>, MobileLocatorConfiguration> locators = new HashMap<>();
//        locators.put(MobileAutocomplete.class, MobileLocatorConfiguration.builder()
//                .set(CLEAR, MobileLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
//                .set(INPUT, MobileLocatorHolder.of(INPUT, CSS, "input[type='text']"))
//                .set(TEXT, MobileLocatorHolder.of(TEXT, CSS, "input[type='text']")));
//        locators.put(MobileCheckbox.class, MobileLocatorConfiguration.builder()
//                .set(LABEL, MobileLocatorHolder.of(LABEL, CSS, "label"))
//                .set(FOCUS, MobileLocatorHolder.of(FOCUS, CSS, "input[type='checkbox']"))
//                .set(SELECTED, MobileLocatorHolder.of(SELECTED, CSS, "input[type='checkbox']"))
//                .set(ENABLED, MobileLocatorHolder.of(ENABLED, CSS, "input[type='checkbox']")));
//        locators.put(MobileDropDownList.class, MobileLocatorConfiguration.builder()
//                .set(UL, MobileLocatorHolder.of(UL, CSS, "ul"))
//                .set(LABEL, MobileLocatorHolder.of(LABEL, CSS, "label")));
//        locators.put(MobileFileInput.class, MobileLocatorConfiguration.builder()
//                // focus
//                .set(LABEL, MobileLocatorHolder.of(LABEL, CSS, "label"))
//                .set(TEXT, MobileLocatorHolder.of(TEXT, CSS, "input[type='file']"))
//                .set(INPUT, MobileLocatorHolder.of(INPUT, CSS, "input[type='file']"))
//                .set(CLEAR, MobileLocatorHolder.of(CLEAR, CSS, "input[type='file']"))
//                .set(ENABLED, MobileLocatorHolder.of(ENABLED, CSS, "input[type='file']")));
//        locators.put(MobileList.class, MobileLocatorConfiguration.builder()
//                .set(LI, MobileLocatorHolder.of(LI, CSS, "li")));
//        // Задавать корневым элементом лучше первый родительский элемент от input
//        locators.put(MobileRadioButton.class, MobileLocatorConfiguration.builder()
//                .set(LABEL, MobileLocatorHolder.of(LABEL, CSS, "label"))
//                .set(FOCUS, MobileLocatorHolder.of(FOCUS, CSS, "input[type='radio']"))
//                .set(SELECTED, MobileLocatorHolder.of(SELECTED, CSS, "input[type='radio']"))
//                .set(ENABLED, MobileLocatorHolder.of(ENABLED, CSS, "input[type='radio']")));
//        locators.put(MobileRadioGroup.class, MobileLocatorConfiguration.builder()
//                .set(RADIO, MobileLocatorHolder.of(RADIO, XPATH, ".//input[@type='radio']/parent::node()").setSingle(false)));
//        locators.put(MobileTable.class, MobileLocatorConfiguration.builder()
//                .set(THEAD_ROW, MobileLocatorHolder.of(THEAD_ROW, CSS, "thead tr"))
//                .set(TBODY_ROW, MobileLocatorHolder.of(TBODY_ROW, CSS, "tbody tr").setSingle(false))
//                .set(TFOOT_ROW, MobileLocatorHolder.of(TFOOT_ROW, CSS, "tfoot tr")));
//        locators.put(MobileTextAutocomplete.class, MobileLocatorConfiguration.builder()
//                .set(CLEAR, MobileLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
//                .set(INPUT, MobileLocatorHolder.of(INPUT, CSS, "input[type='text']"))
//                .set(TEXT, MobileLocatorHolder.of(TEXT, CSS, "input[type='text']")));
//        locators.put(MobileTextDropDownList.class, MobileLocatorConfiguration.builder()
//                .set(UL, MobileLocatorHolder.of(UL, CSS, "ul"))
//                .set(LABEL, MobileLocatorHolder.of(LABEL, CSS, "label")));
//        locators.put(MobileTextInput.class, MobileLocatorConfiguration.builder()
//                .set(LABEL, MobileLocatorHolder.of(LABEL, XPATH, "self::node()/parent::node()/label").setOnlyWithinParent(false)));
////                .set(TEXT, MobileLocatorHolder.of(TEXT, CSS, "input[type='text']"))
////                .set(INPUT, MobileLocatorHolder.of(INPUT, CSS, "input[type='text']"))
////                .set(CLEAR, MobileLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
////                .set(ENABLED, MobileLocatorHolder.of(ENABLED, CSS, "input[type='text']")));
//        locators.put(MobileTextList.class, MobileLocatorConfiguration.builder()
//                .set(LI, MobileLocatorHolder.of(LI, CSS, "li")));
//        locators.put(MobileTextTable.class, MobileLocatorConfiguration.builder()
//                .set(THEAD_ROW, MobileLocatorHolder.of(THEAD_ROW, CSS, "thead tr"))
//                .set(TBODY_ROW, MobileLocatorHolder.of(TBODY_ROW, CSS, "tbody tr").setSingle(false))
//                .set(TFOOT_ROW, MobileLocatorHolder.of(TFOOT_ROW, CSS, "tfoot tr")));
        return locators;
    }

}
