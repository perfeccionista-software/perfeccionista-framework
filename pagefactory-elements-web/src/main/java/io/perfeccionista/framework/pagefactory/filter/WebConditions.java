package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementStateDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.stringlist.WebStringListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.stringlist.WebStringListBlockTextCondition;
import io.perfeccionista.framework.pagefactory.filter.stringtable.WebStringTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.stringtable.WebStringTableCellTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementPropertyCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementStateDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowIndexCondition;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebConditions {

    // WebRadioButton

    public static WebRadioButtonIndexCondition radioButtonIndex(NumberValue<Integer> integerValue) {
        return new WebRadioButtonIndexCondition();
    }

    public static WebRadioButtonEnabledCondition enabled() {
        return new WebRadioButtonEnabledCondition();
    }

    public static WebRadioButtonSelectedCondition selected() {
        return new WebRadioButtonSelectedCondition();
    }

    public static WebRadioButtonLabelCondition label(StringValue stringValue) {
        return new WebRadioButtonLabelCondition(stringValue);
    }

    public static WebRadioButtonLabelCondition label(NumberValue<? extends Number> numberValue) {
        return new WebRadioButtonLabelCondition(numberValue);
    }

    // WebList

    public static WebListBlockIndexCondition blockIndex(NumberValue<Integer> integerValue) {
        return new WebListBlockIndexCondition(integerValue);
    }

    public static WebListBlockElementDisplayedCondition displayed(IsDisplayedAvailable elementMock) {
        return new WebListBlockElementDisplayedCondition(elementMock);
    }

    public static WebListBlockElementDisplayedCondition displayed(String elementName) {
        return new WebListBlockElementDisplayedCondition(elementName);
    }

    public static WebListBlockElementEnabledCondition enabled(IsEnabledAvailable elementMock) {
        return new WebListBlockElementEnabledCondition(elementMock);
    }

    public static WebListBlockElementEnabledCondition enabled(String elementName) {
        return new WebListBlockElementEnabledCondition(elementName);
    }

    public static WebListBlockElementSelectedCondition selected(IsSelectedAvailable elementMock) {
        return new WebListBlockElementSelectedCondition(elementMock);
    }

    public static WebListBlockElementSelectedCondition selected(String elementName) {
        return new WebListBlockElementSelectedCondition(elementName);
    }

    public static WebListBlockElementTextCondition text(GetTextAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue);
    }

    public static WebListBlockElementTextCondition text(GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementMock, stringValue);
    }

    public static WebListBlockElementTextCondition text(String elementName, StringValue stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue);
    }

    public static WebListBlockElementTextCondition text(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementTextCondition(elementName, stringValue);
    }

    public static WebListBlockElementLabelCondition label(GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue);
    }

    public static WebListBlockElementLabelCondition label(GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementMock, stringValue);
    }

    public static WebListBlockElementLabelCondition label(String elementName, StringValue stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue);
    }

    public static WebListBlockElementLabelCondition label(String elementName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementLabelCondition(elementName, stringValue);
    }

    public static WebListBlockElementStateDisplayedCondition stateDisplayed(WebChildElement elementMock, String stateName) {
        return new WebListBlockElementStateDisplayedCondition(elementMock, stateName);
    }

    public static WebListBlockElementStateDisplayedCondition stateDisplayed(String elementName, String stateName) {
        return new WebListBlockElementStateDisplayedCondition(elementName, stateName);
    }

    public static WebListBlockElementPropertyCondition property(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition property(WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementMock, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition property(String elementName, String propertyName, StringValue stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue);
    }

    public static WebListBlockElementPropertyCondition property(String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebListBlockElementPropertyCondition(elementName, propertyName, stringValue);
    }

    // WebStringList

    public static WebStringListBlockIndexCondition stringBlockIndex(NumberValue<Integer> integerValue) {
        return new WebStringListBlockIndexCondition(integerValue);
    }

    public static WebStringListBlockTextCondition blockText(StringValue stringValue) {
        return new WebStringListBlockTextCondition(stringValue);
    }

    public static WebStringListBlockTextCondition blockText(NumberValue<? extends Number> integerValue) {
        return new WebStringListBlockTextCondition(integerValue);
    }

    // WebTable

    public static WebTableRowIndexCondition rowIndex(NumberValue<Integer> integerValue) {
        return new WebTableRowIndexCondition(integerValue);
    }

    public static WebTableCellElementDisplayedCondition displayed(String columnName, IsDisplayedAvailable elementMock) {
        return new WebTableCellElementDisplayedCondition(columnName, elementMock);
    }

    public static WebTableCellElementDisplayedCondition displayed(String columnName, String elementName) {
        return new WebTableCellElementDisplayedCondition(columnName, elementName);
    }

    public static WebTableCellElementEnabledCondition enabled(String columnName, IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock);
    }

    public static WebTableCellElementEnabledCondition enabled(String columnName, String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName);
    }

    public static WebTableCellElementSelectedCondition selected(String columnName, IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock);
    }

    public static WebTableCellElementSelectedCondition selected(String columnName, String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName);
    }

    public static WebTableCellElementTextCondition text(String columnName, GetTextAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementTextCondition text(String columnName, GetTextAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementTextCondition text(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementTextCondition text(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementTextCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementLabelCondition label(String columnName, GetLabelAvailable elementMock, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementLabelCondition label(String columnName, GetLabelAvailable elementMock, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementMock, stringValue);
    }

    public static WebTableCellElementLabelCondition label(String columnName, String elementName, StringValue stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementLabelCondition label(String columnName, String elementName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementLabelCondition(columnName, elementName, stringValue);
    }

    public static WebTableCellElementStateDisplayedCondition stateDisplayed(String columnName, WebChildElement elementMock, String stateName) {
        return new WebTableCellElementStateDisplayedCondition(columnName, elementMock, stateName);
    }

    public static WebTableCellElementStateDisplayedCondition stateDisplayed(String columnName, String elementName, String stateName) {
        return new WebTableCellElementStateDisplayedCondition(columnName, elementName, stateName);
    }

    public static WebTableCellElementPropertyCondition property(String columnName, WebChildElement elementMock, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition property(String columnName, WebChildElement elementMock, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementMock, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition property(String columnName, String elementName, String propertyName, StringValue stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue);
    }

    public static WebTableCellElementPropertyCondition property(String columnName, String elementName, String propertyName, NumberValue<? extends Number> stringValue) {
        return new WebTableCellElementPropertyCondition(columnName, elementName, propertyName, stringValue);
    }

    // WebStringTable

    public static WebStringTableRowIndexCondition stringRowIndex(NumberValue<Integer> integerValue) {
        return new WebStringTableRowIndexCondition(integerValue);
    }

    public static WebStringTableCellTextCondition cellText(String columnName, StringValue stringValue) {
        return new WebStringTableCellTextCondition(columnName, stringValue);
    }

    public static WebStringTableCellTextCondition cellText(String columnName, NumberValue<Integer> integerValue) {
        return new WebStringTableCellTextCondition(columnName, integerValue);
    }

}
