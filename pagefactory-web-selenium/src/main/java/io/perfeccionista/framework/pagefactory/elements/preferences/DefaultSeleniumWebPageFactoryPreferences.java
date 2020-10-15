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
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownListImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.WebTextInputImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextListImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTableImpl;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetColor;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetDimensions;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsInFocus;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsOnTheScreenCompletely;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsPresent;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsSelected;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLocation;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetTextFromValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSaveImageToFile;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeyEvents;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.interactions.JsDragAndDropInteraction;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.HrefAttributeExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.PlaceholderAttributeExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.SrcAttributeExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;

import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SAVE_TO_FILE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SET_FILENAME_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.FOCUS;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.SELECTED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;
import static io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionNames.DRAG_AND_DROP;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CSS;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;
import static io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyNames.IMAGE_SOURCE;
import static io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyNames.LINK;
import static io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyNames.PLACEHOLDER;

public class DefaultSeleniumWebPageFactoryPreferences extends DefaultWebPageFactoryPreferences {

    public DefaultSeleniumWebPageFactoryPreferences() {
        super();
        setWebMappedBlocks(getWebMappedBlocks());
        setWebElementImplementations(getWebElementImplementations());
        setWebElementActionConfigurations(getWebElementActionConfigurations());
        setWebElementInteractionConfigurations(getWebElementInteractionConfigurations());
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
        implementations.put(WebTextBlock.class, WebTextBlockImpl.class);
        implementations.put(WebTextDropDownList.class, WebTextDropDownListImpl.class);
        implementations.put(WebTextInput.class, WebTextInputImpl.class);
        implementations.put(WebTextList.class, WebTextListImpl.class);
        implementations.put(WebTextTable.class, WebTextTableImpl.class);
        return implementations;
    }

    protected Map<Class<? extends WebChildElementBase>, WebElementActionConfiguration> getWebElementActionConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebElementActionConfiguration> actionConfigurations = new HashMap<>();
        // Elements
        actionConfigurations.put(WebAutocomplete.class, WebElementActionConfiguration.builder()
                .set(GET_TEXT_METHOD, new JsGetTextFromValue()));
        actionConfigurations.put(WebImage.class, WebElementActionConfiguration.builder()
                .set(SAVE_TO_FILE_METHOD, new JsSaveImageToFile()));
        actionConfigurations.put(WebFileInput.class, WebElementActionConfiguration.builder()
                .set(GET_TEXT_METHOD, new JsGetTextFromValue())
                .set(SET_FILENAME_METHOD, new SeleniumSendKeys()));                                 // SeleniumImpl
        actionConfigurations.put(WebTextAutocomplete.class, WebElementActionConfiguration.builder()
                .set(GET_TEXT_METHOD, new JsGetTextFromValue()));
        actionConfigurations.put(WebTextInput.class, WebElementActionConfiguration.builder()
                .set(GET_TEXT_METHOD, new JsGetTextFromValue()));
        // Interfaces
        actionConfigurations.put(ClearAvailable.class, WebElementActionConfiguration.builder()
                .set(CLEAR_METHOD, new SeleniumClear()));                                           // SeleniumImpl
        actionConfigurations.put(ClickAvailable.class, WebElementActionConfiguration.builder()
                .set(CLICK_METHOD, new SeleniumClick()));                                           // SeleniumImpl
        actionConfigurations.put(CloseAvailable.class, WebElementActionConfiguration.builder()
                .set(CLOSE_METHOD, new SeleniumClick()));                                           // SeleniumImpl
        actionConfigurations.put(GetColorAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_COLOR_METHOD, new JsGetColor()));
        actionConfigurations.put(GetDimensionsAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_DIMENSIONS_METHOD, new JsGetDimensions()));
        actionConfigurations.put(GetLabelAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_LABEL_METHOD, new JsGetLabel()));
        actionConfigurations.put(GetLocationAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_LOCATION_METHOD, new JsGetLocation()));
        actionConfigurations.put(GetScreenshotAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_SCREENSHOT_METHOD, new JsGetScreenshot()));
        actionConfigurations.put(GetTextAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_TEXT_METHOD, new JsGetText()));
        actionConfigurations.put(HoverToAvailable.class, WebElementActionConfiguration.builder()
                .set(HOVER_TO_METHOD, new SeleniumHoverTo()));                                      // SeleniumImpl
        actionConfigurations.put(IsDisplayedAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_DISPLAYED_METHOD, new JsGetIsDisplayed()));
        actionConfigurations.put(IsEnabledAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_ENABLED_METHOD, new JsGetIsEnabled()));
        actionConfigurations.put(IsInFocusAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_IN_FOCUS_METHOD, new JsGetIsInFocus()));
        actionConfigurations.put(IsOnTheScreenAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_ON_THE_SCREEN_METHOD, new JsGetIsOnTheScreenCompletely()));
        actionConfigurations.put(IsOpenAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_OPEN_METHOD, new JsGetIsDisplayed()));
        actionConfigurations.put(IsPresentAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_PRESENT_METHOD, new JsGetIsPresent()));
        actionConfigurations.put(IsSelectedAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_SELECTED_METHOD, new JsGetIsSelected()));
        actionConfigurations.put(OpenAvailable.class, WebElementActionConfiguration.builder()
                .set(OPEN_METHOD, new SeleniumClick()));                                            // SeleniumImpl
        actionConfigurations.put(ScrollToAvailable.class, WebElementActionConfiguration.builder()
                .set(SCROLL_TO_METHOD, new JsScrollTo()));
        actionConfigurations.put(SendKeysAvailable.class, WebElementActionConfiguration.builder()
                .set(SEND_KEYS_METHOD, new SeleniumSendKeys())                                      // SeleniumImpl
                .set(SEND_KEYS_EVENTS_METHOD, new SeleniumSendKeyEvents()));                        // SeleniumImpl
        actionConfigurations.put(WebComponentAvailable.class, WebElementActionConfiguration.builder()
                .set(IS_COMPONENT_PRESENT_METHOD, new JsGetIsPresent())
                .set(IS_COMPONENT_DISPLAYED_METHOD, new JsGetIsDisplayed()));
        actionConfigurations.put(WebElementPropertyAvailable.class, WebElementActionConfiguration.builder()
                .set(GET_PROPERTY_VALUE_METHOD, new JsGetPropertyValue()));
        return actionConfigurations;
    }

    protected Map<Class<? extends WebChildElementBase>, WebElementInteractionConfiguration> getWebElementInteractionConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebElementInteractionConfiguration> interactionConfigurations = new HashMap<>();
        interactionConfigurations.put(WebChildElement.class, WebElementInteractionConfiguration.builder()
                .set(DRAG_AND_DROP, new JsDragAndDropInteraction()));
        return interactionConfigurations;
    }

    protected Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> getWebElementPropertyConfigurations() {
        Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> properties = new HashMap<>();
        properties.put(WebImage.class, WebElementPropertyConfiguration.builder()
                .set(IMAGE_SOURCE, WebElementPropertyHolder.of(IMAGE_SOURCE, null, new SrcAttributeExtractor())));
        properties.put(WebButton.class, WebElementPropertyConfiguration.builder()
                .set(LINK, WebElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
        properties.put(WebLink.class, WebElementPropertyConfiguration.builder()
                .set(LINK, WebElementPropertyHolder.of(LINK, null, new HrefAttributeExtractor())));
        WebLocatorHolder inputLocatorHolder = WebLocatorHolder.of(INPUT, CSS, "input[placeholder]");
        properties.put(WebAutocomplete.class, WebElementPropertyConfiguration.builder()
                .set(PLACEHOLDER, WebElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
        properties.put(WebTextAutocomplete.class, WebElementPropertyConfiguration.builder()
                .set(PLACEHOLDER, WebElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
        properties.put(WebTextInput.class, WebElementPropertyConfiguration.builder()
                .set(PLACEHOLDER, WebElementPropertyHolder.of(PLACEHOLDER, inputLocatorHolder, new PlaceholderAttributeExtractor())));
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
                .set(LI, WebLocatorHolder.of(LI, CSS, "li")));
        // Задавать корневым элементом лучше первый родительский элемент от input
        locators.put(WebRadioButton.class, WebLocatorConfiguration.builder()
                .set(LABEL, WebLocatorHolder.of(LABEL, CSS, "label"))
                .set(FOCUS, WebLocatorHolder.of(FOCUS, CSS, "input[type='radio']"))
                .set(SELECTED, WebLocatorHolder.of(SELECTED, CSS, "input[type='radio']"))
                .set(ENABLED, WebLocatorHolder.of(ENABLED, CSS, "input[type='radio']")));
        locators.put(WebRadioGroup.class, WebLocatorConfiguration.builder()
                .set(RADIO, WebLocatorHolder.of(RADIO, XPATH, ".//input[@type='radio']/parent::node()").setSingle(false)));
        locators.put(WebTable.class, WebLocatorConfiguration.builder()
                .set(THEAD_ROW, WebLocatorHolder.of(THEAD_ROW, CSS, "thead tr"))
                .set(TBODY_ROW, WebLocatorHolder.of(TBODY_ROW, CSS, "tbody tr").setSingle(false))
                .set(TFOOT_ROW, WebLocatorHolder.of(TFOOT_ROW, CSS, "tfoot tr")));
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
                .set(LI, WebLocatorHolder.of(LI, CSS, "li")));
        locators.put(WebTextTable.class, WebLocatorConfiguration.builder()
                .set(THEAD_ROW, WebLocatorHolder.of(THEAD_ROW, CSS, "thead tr"))
                .set(TBODY_ROW, WebLocatorHolder.of(TBODY_ROW, CSS, "tbody tr").setSingle(false))
                .set(TFOOT_ROW, WebLocatorHolder.of(TFOOT_ROW, CSS, "tfoot tr")));
        return locators;
    }

}