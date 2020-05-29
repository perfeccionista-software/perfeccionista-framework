package io.perfeccionista.framework.asserts;

import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebElementsAsserts {

    public static void assertDisplayed(IsDisplayedAvailable element) {
        if (element.isDisplayed()) {
            return;
        }
//        throw new ElementNotDisplayedException();
    }

    public static void assertNotDisplayed(IsDisplayedAvailable element) {
        if (element.isDisplayed()) {
            return;
        }
//        throw new ElementNotDisplayedException();
    }


    public static void assertSelected(IsSelectedAvailable element) {
//        if (element.isDisplayed()) {
//            return;
//        }
//        throw new ElementNotDisplayedException();
    }


    public static void assertNotSelected(IsSelectedAvailable element) {
//        if (element.isDisplayed()) {
//            return;
//        }
//        throw new ElementNotDisplayedException();
    }

    public static void assertEnabled(IsEnabledAvailable element) {
//        if (element.isDisplayed()) {
//            return;
//        }
//        throw new ElementNotDisplayedException();
    }

    public static void assertDisabled(IsEnabledAvailable element) {
//        if (element.isDisplayed()) {
//            return;
//        }
//        throw new ElementNotDisplayedException();
    }




    public static void assertElementText(StringValue expectedValue, GetTextAvailable element) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

    public static void assertElementSize(NumberValue<?> expectedValue, SizeAvailable element) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }



    public static void assertElementLabel(StringValue expectedValue, GetLabelAvailable element) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

    public static void assertElementProperty(String propertyName, StringValue value, WebElementPropertyAvailable<?> element) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

    public static void assertElementComponentDisplayed(String componentName, IsDisplayedAvailable element) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

    public static void assertElementOpened(IsOpenAvailable element) {

    }

    public static void assertElementClosed(IsOpenAvailable element) {

    }





    // TODO: Перенести в другие ассерты

    public static void assertText(StringValue expectedValue, String actualValue) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

    public static void assertNumber(NumberValue<?> expectedValue, Number actualValue) {
//        if (value.check(element.getText())) {
//            return;
//        }
//        throw new ElementValueException();
    }

}
