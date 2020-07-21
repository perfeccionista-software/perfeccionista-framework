package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.exceptions.ElementInFocusException;
import io.perfeccionista.framework.exceptions.ElementIsDisabledException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.ElementIsEnabledException;
import io.perfeccionista.framework.exceptions.ElementIsPresentException;
import io.perfeccionista.framework.exceptions.ElementIsSelectedException;
import io.perfeccionista.framework.exceptions.ElementLabelValueException;
import io.perfeccionista.framework.exceptions.ElementLocationException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.ElementNotSelectedException;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.WebElementColor;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
class WebRadioButtonElementTest  extends AbstractUiTest {

    @Test
    void webRadioButtonInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        assertNotNull(radioButtonOne.getEnvironment());
        assertNotNull(radioButtonOne.getLocator(ROOT));
        assertNotNull(radioButtonOne.getLocatorChain());
        assertEquals(elementsPage, radioButtonOne.getParent());
        WebBrowserDispatcher webBrowserDispatcher = radioButtonOne.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebCheckbox
        assertNotNull(radioButtonOne.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_SELECTED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_SELECTED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_BE_SELECTED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_LABEL_METHOD, String.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class));
        // WebChildElement
        assertNotNull(radioButtonOne.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(radioButtonOne.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = radioButtonOne.getElementIdentifier();
        assertEquals("radioButtonOne", elementIdentifier.getElementMethod().getName());
        assertEquals("radioButtonOne", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("RadioButton one"));
        assertFalse(elementIdentifier.isNameDeprecated("RadioButton one"));
        assertEquals(1, elementIdentifier.names().size());
        WebRadioButton radioButtonOneElement = elementsPage.getElementRegistry().getElementByPath("RadioButton one", WebRadioButton.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("RadioButton one")));
        assertNotNull(radioButtonOneElement);
        assertEquals("RadioButton one", elementIdentifier.getLastUsedName());
        assertNotNull(radioButtonOne.toString());
        // Смотрим описание элемента
        System.out.println(radioButtonOne.toString());
    }

    @Test
    void webRadioButtonPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextBlock radioButtonText = elementsPage.radioButtonText()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Label 2"));
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldBeEnabled()
                .shouldNotBeSelected()
                .shouldNotBeInFocus()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(176.3d, 24.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345.0d, 713.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(LABEL, "color", WebElementColor.of(33, 37, 41, 1.0d))
                .hoverTo(true) // 280 ms
                .shouldHaveLabel(value.stringEquals("Label 1"))
                .shouldNotHaveLabel(value.stringContains("Label 2"))
                .shouldHavePropertyValue("name", value.stringEquals("RadioButton 1"))
                .click()
                .shouldBeInFocus()
                .shouldBeSelected()
                .should(simpleButtonElement -> {
                    if (simpleButtonElement.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(simpleButtonElement.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", simpleButtonElement.toJson()));
                });
        radioButtonText.shouldHaveText(value.stringEquals("Label 1"));
        assertTrue(radioButtonOne.isPresent());
        assertTrue(radioButtonOne.isDisplayed());
        assertTrue(radioButtonOne.isInFocus());
        assertEquals(Dimensions.of(176.3d, 24.0d).setInaccuracy(0.2d), radioButtonOne.getDimensions(ROOT));
        assertEquals(Location.absolute(345.0d, 713.4d).setInaccuracy(0.2d), radioButtonOne.getLocation(ROOT));
        assertEquals(WebElementColor.of(33, 37, 41, 1.0d), radioButtonOne.getColor(LABEL, "color"));
        Screenshot screenshot = radioButtonOne.getScreenshot(ROOT); // 400 ms
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(3500).check(screenshot.getRaw().length));
        radioButtonText.shouldHaveText(value.stringContainsAll("Label 1"));
    }

    @Test
    void webRadioButtonNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioButtonOne.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, radioButtonOne::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, radioButtonOne::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, radioButtonOne::shouldBeInFocus);
        assertThrows(ElementIsEnabledException.class, radioButtonOne::shouldBeDisabled);
        assertThrows(ElementNotSelectedException.class, radioButtonOne::shouldBeSelected);
        Dimensions elementDimensions = Dimensions.of(176.3d, 24.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> radioButtonOne.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(345.0d, 713.4d, 345.0d, 713.4d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> radioButtonOne.componentShouldHaveLocation(ROOT, elementLocation.offset(12d, 10d)));
        assertThrows(ElementLocationException.class, () -> radioButtonOne.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(33, 37, 41, 1.0d);
        assertThrows(ElementColorException.class, () -> radioButtonOne.componentShouldNotHaveColor(LABEL, "color", elementColor));
        assertThrows(ElementLabelValueException.class, () -> radioButtonOne.shouldNotHaveLabel(value.stringEquals("Label 1")));
        assertThrows(ElementLabelValueException.class, () -> radioButtonOne.shouldHaveLabel(value.stringEquals("Label unknown")));
        radioButtonOne.click();
        assertThrows(ElementIsSelectedException.class, radioButtonOne::shouldNotBeSelected);
        assertThrows(ElementInFocusException.class, radioButtonOne::shouldNotBeInFocus);
        assertThrows(ElementIsDisabledException.class, () -> elementsPage.checkboxThree().shouldBeEnabled());
    }

}
