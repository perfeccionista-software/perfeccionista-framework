package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebAutocompleteImpl;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownListImpl;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebButtonImpl;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebCheckboxImpl;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebFileInputImpl;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebImageImpl;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebLinkImpl;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebListImpl;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButtonImpl;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroupImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTableImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebTextAutocompleteImpl;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.WebTextImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownListImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.WebTextInputImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextListImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementStateAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckBooleanAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsImage;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckStringAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetColor;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetElementBounds;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsEnabled;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsInFocus;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsOnTheScreen;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsSelected;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetText;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetValueAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.JsSaveImageToFile;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.JsTypeText;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClear;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumReplaceText;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumSendKeyEvents;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumTypeText;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumUploadFromClasspath;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumUploadFromFile;

import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IMAGE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.REPLACE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SAVE_IMAGE_TO_FILE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_CLASSPATH_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_FILE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_FOOTER;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.UL;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CSS;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;

public class DefaultSeleniumWebPageFactoryPreferences extends DefaultWebPageFactoryPreferences {

    public DefaultSeleniumWebPageFactoryPreferences() {
        super();
        setWebMappedBlocks(getWebMappedBlocks());
        setWebElementImplementations(getWebElementImplementations());
        setWebElementActionConfigurations(getWebElementActionConfigurations());
        setWebElementPropertyConfigurations(getWebElementPropertyConfigurations());
        setWebLocatorConfigurations(getWebLocatorConfigurations());
    }

    protected Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> getWebMappedBlocks() {
        Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> webMappedBlocks = new HashMap<>();
        webMappedBlocks.put(WebRadioGroup.class, DefaultWebRadioButtonBlock.class);
        webMappedBlocks.put(WebTextAutocomplete.class, DefaultWebTextBlock.class);
        webMappedBlocks.put(WebTextDropDownList.class, DefaultWebTextBlock.class);
        webMappedBlocks.put(WebTextList.class, DefaultWebTextBlock.class);
        return webMappedBlocks;
    }

    protected Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> getWebElementImplementations() {
        Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> implementations = new HashMap<>();
        implementations.put(WebAutocomplete.class, WebAutocompleteImpl.class);
        implementations.put(WebButton.class, WebButtonImpl.class);
        implementations.put(WebCheckbox.class, WebCheckboxImpl.class);
        implementations.put(WebDropDownList.class, WebDropDownListImpl.class);
        implementations.put(WebFileInput.class, WebFileInputImpl.class);
        implementations.put(WebImage.class, WebImageImpl.class);
        implementations.put(WebLink.class, WebLinkImpl.class);
        implementations.put(WebList.class, WebListImpl.class);
        implementations.put(WebRadioButton.class, WebRadioButtonImpl.class);
        implementations.put(WebRadioGroup.class, WebRadioGroupImpl.class);
        implementations.put(WebTable.class, WebTableImpl.class);
        implementations.put(WebTextAutocomplete.class, WebTextAutocompleteImpl.class);
        implementations.put(WebText.class, WebTextImpl.class);
        implementations.put(WebTextDropDownList.class, WebTextDropDownListImpl.class);
        implementations.put(WebTextInput.class, WebTextInputImpl.class);
        implementations.put(WebTextList.class, WebTextListImpl.class);
        return implementations;
    }

    protected Map<Class<? extends WebChildElementBase>, WebEndpointHandlerConfiguration> getWebElementActionConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebEndpointHandlerConfiguration> actionConfigurations = new HashMap<>();
        // Elements
        actionConfigurations.put(WebAutocomplete.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_TEXT_METHOD, JsGetValueAttributeValue.class));
        actionConfigurations.put(WebImage.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_IMAGE_METHOD, JsCheckIsImage.class)
                .set(SAVE_IMAGE_TO_FILE_METHOD, JsSaveImageToFile.class));
        actionConfigurations.put(WebFileInput.class, WebEndpointHandlerConfiguration.builder()
                .set(CLEAR_METHOD, SeleniumClear.class)
                .set(GET_TEXT_METHOD, JsGetValueAttributeValue.class)
                .set(REPLACE_TEXT_METHOD, SeleniumReplaceText.class)
                .set(TYPE_TEXT_METHOD, JsTypeText.class)
                .set(UPLOAD_FROM_CLASSPATH_METHOD, SeleniumUploadFromClasspath.class)
                .set(UPLOAD_FROM_FILE_METHOD, SeleniumUploadFromFile.class));                       // SeleniumImpl
        actionConfigurations.put(WebTextAutocomplete.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_TEXT_METHOD, JsGetValueAttributeValue.class));
        actionConfigurations.put(WebTextInput.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_TEXT_METHOD, JsGetValueAttributeValue.class));
        // Interfaces
        actionConfigurations.put(WebClickAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(CLICK_METHOD, SeleniumClick.class));                                           // SeleniumImpl
        actionConfigurations.put(WebGetColorAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_COLOR_METHOD, JsGetColor.class));
        actionConfigurations.put(WebGetLabelAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_LABEL_METHOD, JsGetText.class));
        actionConfigurations.put(WebGetElementBoundsAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_ELEMENT_BOUNDS_METHOD, JsGetElementBounds.class));
        actionConfigurations.put(WebGetScreenshotAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_SCREENSHOT_METHOD, JsGetScreenshot.class));
        actionConfigurations.put(WebGetTextAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_TEXT_METHOD, JsGetText.class));
        actionConfigurations.put(WebHoverToAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(HOVER_TO_METHOD, SeleniumHoverTo.class));                                      // SeleniumImpl
        actionConfigurations.put(WebIsDisplayedAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_DISPLAYED_METHOD, JsGetIsDisplayed.class));
        actionConfigurations.put(WebIsEnabledAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_ENABLED_METHOD, JsGetIsEnabled.class));
        actionConfigurations.put(WebIsInFocusAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_IN_FOCUS_METHOD, JsGetIsInFocus.class));
        actionConfigurations.put(WebIsOnTheScreenAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_ON_THE_SCREEN_METHOD, JsGetIsOnTheScreen.class));
        actionConfigurations.put(WebDropDownAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_OPEN_METHOD, JsGetIsDisplayed.class)
                .set(OPEN_METHOD, SeleniumClick.class)
                .set(CLOSE_METHOD, SeleniumClick.class));
        actionConfigurations.put(WebIsPresentAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_PRESENT_METHOD, JsGetIsPresent.class));
        actionConfigurations.put(WebIsSelectedAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_SELECTED_METHOD, JsGetIsSelected.class));
        actionConfigurations.put(WebScrollToAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(SCROLL_TO_METHOD, JsScrollTo.class));
        actionConfigurations.put(WebInputTextAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(CLEAR_METHOD, SeleniumClear.class)                                             // SeleniumImpl
                .set(TYPE_TEXT_METHOD, SeleniumTypeText.class)                                      // SeleniumImpl
                .set(REPLACE_TEXT_METHOD, SeleniumReplaceText.class)                                // SeleniumImpl
                .set(SEND_KEY_EVENTS_METHOD, SeleniumSendKeyEvents.class));                         // SeleniumImpl
        actionConfigurations.put(WebComponentAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(IS_COMPONENT_PRESENT_METHOD, JsGetIsPresent.class)
                .set(IS_COMPONENT_DISPLAYED_METHOD, JsGetIsDisplayed.class));
        actionConfigurations.put(WebElementPropertyAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(GET_STRING_ATTRIBUTE_VALUE_METHOD, JsGetAttributeValue.class));
        actionConfigurations.put(WebElementStateAvailable.class, WebEndpointHandlerConfiguration.builder()
                .set(CHECK_IS_DISPLAYED_METHOD, JsCheckIsDisplayed.class)
                .set(CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD, JsCheckBooleanAttributeValue.class)
                .set(CHECK_STRING_ATTRIBUTE_VALUE_METHOD, JsCheckStringAttributeValue.class));
        return actionConfigurations;
    }

    protected Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> getWebElementPropertyConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> properties = new HashMap<>();
        return properties;
    }

    protected Map<Class<? extends WebChildElementBase>, WebLocatorConfiguration> getWebLocatorConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebLocatorConfiguration> locators = new HashMap<>();
        locators.put(WebAutocomplete.class, WebLocatorConfiguration.builder()
                .set(CLEAR, WebLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
                .set(INPUT, WebLocatorHolder.of(INPUT, CSS, "input[type='text']"))
                .set(TEXT, WebLocatorHolder.of(TEXT, CSS, "input[type='text']")));
        locators.put(WebCheckbox.class, WebLocatorConfiguration.builder()
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label"))
                .set(FOCUS, WebLocatorHolder.of(FOCUS, CSS, "input[type='checkbox']"))
                .set(SELECTED, WebLocatorHolder.of(SELECTED, CSS, "input[type='checkbox']"))
                .set(ENABLED, WebLocatorHolder.of(ENABLED, CSS, "input[type='checkbox']")));
        locators.put(WebDropDownList.class, WebLocatorConfiguration.builder()
                .set(UL, WebLocatorHolder.of(UL, CSS, "ul"))
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label")));
        locators.put(WebFileInput.class, WebLocatorConfiguration.builder()
                // focus
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label"))
                .set(TEXT, WebLocatorHolder.of(TEXT, CSS, "input[type='file']"))
                .set(INPUT, WebLocatorHolder.of(INPUT, CSS, "input[type='file']"))
                .set(CLEAR, WebLocatorHolder.of(CLEAR, CSS, "input[type='file']"))
                .set(ENABLED, WebLocatorHolder.of(ENABLED, CSS, "input[type='file']")));
        locators.put(WebList.class, WebLocatorConfiguration.builder()
                .set(ITEM, WebLocatorHolder.of(ITEM, CSS, "li").setSingle(false).setStrictSearch(false)));
        // Задавать корневым элементом лучше первый родительский элемент от input
        locators.put(WebRadioButton.class, WebLocatorConfiguration.builder()
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label"))
                .set(FOCUS, WebLocatorHolder.of(FOCUS, CSS, "input[type='radio']"))
                .set(SELECTED, WebLocatorHolder.of(SELECTED, CSS, "input[type='radio']"))
                .set(ENABLED, WebLocatorHolder.of(ENABLED, CSS, "input[type='radio']")));
        locators.put(WebRadioGroup.class, WebLocatorConfiguration.builder()
                .set(ITEM, WebLocatorHolder.of(ITEM, XPATH, ".//input[@type='radio']/parent::node()").setSingle(false)));
        locators.put(WebTable.class, WebLocatorConfiguration.builder()
                .set(TABLE_HEADER, WebLocatorHolder.of(TABLE_HEADER, CSS, "thead tr"))
                .set(ITEM, WebLocatorHolder.of(ITEM, CSS, "tbody tr").setSingle(false).setStrictSearch(false))
                .set(TABLE_FOOTER, WebLocatorHolder.of(TABLE_FOOTER, CSS, "tfoot tr")));
        locators.put(WebTextAutocomplete.class, WebLocatorConfiguration.builder()
                .set(CLEAR, WebLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
                .set(INPUT, WebLocatorHolder.of(INPUT, CSS, "input[type='text']"))
                .set(TEXT, WebLocatorHolder.of(TEXT, CSS, "input[type='text']")));
        locators.put(WebTextDropDownList.class, WebLocatorConfiguration.builder()
                .set(UL, WebLocatorHolder.of(UL, CSS, "ul"))
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label")));
        locators.put(WebTextInput.class, WebLocatorConfiguration.builder()
                .set(LABEL, WebLocatorHolder.of(LABEL, XPATH, "self::node()/parent::node()/label").setOnlyWithinParent(false)));
//                .set(TEXT, WebLocatorHolder.of(TEXT, CSS, "input[type='text']"))
//                .set(INPUT, WebLocatorHolder.of(INPUT, CSS, "input[type='text']"))
//                .set(CLEAR, WebLocatorHolder.of(CLEAR, CSS, "input[type='text']"))
//                .set(ENABLED, WebLocatorHolder.of(ENABLED, CSS, "input[type='text']")));
        locators.put(WebTextList.class, WebLocatorConfiguration.builder()
                .set(ITEM, WebLocatorHolder.of(ITEM, CSS, "li")));
        return locators;
    }

}
