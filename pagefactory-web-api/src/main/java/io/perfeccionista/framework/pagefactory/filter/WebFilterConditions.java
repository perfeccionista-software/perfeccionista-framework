package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextStringValueCondition;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public class WebFilterConditions {

    protected WebFilterConditions() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebRadioButton
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebRadioButtonEmptyCondition allRadioButtons() {
        return new WebRadioButtonEmptyCondition().allRadioButtons();
    }

    public static WebRadioButtonEmptyCondition noRadioButtons() {
        return new WebRadioButtonEmptyCondition().noRadioButtons();
    }

    // Index

    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull Integer expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull Integer expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
    }

    // Enabled

    public static WebRadioButtonEnabledCondition enabled() {
        return new WebRadioButtonEnabledCondition().enabled();
    }

    public static WebRadioButtonEnabledCondition disabled() {
        return new WebRadioButtonEnabledCondition().disabled();
    }

    // Label

    public static WebRadioButtonLabelCondition containsLabel(@NotNull String expectedText) {
        return new WebRadioButtonLabelCondition(expectedText).containsLabel();
    }

    public static WebRadioButtonLabelCondition containsLabel(@NotNull StringValue expectedValue) {
        return new WebRadioButtonLabelCondition(expectedValue).containsLabel();
    }

    public static WebRadioButtonLabelCondition containsLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebRadioButtonLabelCondition(expectedValue).containsLabel();
    }

    public static WebRadioButtonLabelCondition notContainsLabel(@NotNull String expectedText) {
        return new WebRadioButtonLabelCondition(expectedText).notContainsLabel();
    }

    public static WebRadioButtonLabelCondition notContainsLabel(@NotNull StringValue expectedValue) {
        return new WebRadioButtonLabelCondition(expectedValue).notContainsLabel();
    }

    public static WebRadioButtonLabelCondition notContainsLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebRadioButtonLabelCondition(expectedValue).notContainsLabel();
    }

    // Selected

    public static WebRadioButtonSelectedCondition selected() {
        return new WebRadioButtonSelectedCondition().selected();
    }

    public static WebRadioButtonSelectedCondition notSelected() {
        return new WebRadioButtonSelectedCondition().notSelected();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebListBlockElementEmptyCondition allBlocks() {
        return new WebListBlockElementEmptyCondition().allBlocks();
    }

    public static WebListBlockElementEmptyCondition noBlocks() {
        return new WebListBlockElementEmptyCondition().noBlocks();
    }

    // Index

    public static WebListBlockIndexCondition blockIndex(@NotNull Integer expectedValue) {
        return new WebListBlockIndexCondition(expectedValue).withBlockIndex();
    }

    public static WebListBlockIndexCondition blockIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebListBlockIndexCondition(expectedValue).withBlockIndex();
    }

    public static WebListBlockIndexCondition blockIndexNot(@NotNull Integer expectedValue) {
        return new WebListBlockIndexCondition(expectedValue).withoutBlockIndexNot();
    }

    public static WebListBlockIndexCondition blockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebListBlockIndexCondition(expectedValue).withoutBlockIndexNot();
    }

    // ComponentDisplayed

    public static WebListBlockElementComponentDisplayedCondition componentDisplayed(@NotNull WebChildElement elementFrame,
                                                                                    @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementFrame, componentName).componentDisplayed();
    }

    public static WebListBlockElementComponentDisplayedCondition componentDisplayed(@NotNull String elementPath,
                                                                                    @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementPath, componentName).componentDisplayed();
    }

    public static WebListBlockElementComponentDisplayedCondition componentNotDisplayed(@NotNull WebChildElement elementFrame,
                                                                                       @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementFrame, componentName).componentNotDisplayed();
    }

    public static WebListBlockElementComponentDisplayedCondition componentNotDisplayed(@NotNull String elementPath,
                                                                                       @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition(elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static WebListBlockElementComponentPresentCondition componentPresent(@NotNull WebChildElement elementFrame,
                                                                                @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementFrame, componentName).componentPresent();
    }

    public static WebListBlockElementComponentPresentCondition componentPresent(@NotNull String elementPath,
                                                                                @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementPath, componentName).componentPresent();
    }

    public static WebListBlockElementComponentPresentCondition componentNotPresent(@NotNull WebChildElement elementFrame,
                                                                                   @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementFrame, componentName).componentNotPresent();
    }

    public static WebListBlockElementComponentPresentCondition componentNotPresent(@NotNull String elementPath,
                                                                                   @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition(elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static WebListBlockElementDisplayedCondition displayed(@NotNull IsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedCondition(elementFrame).displayed();
    }

    public static WebListBlockElementDisplayedCondition displayed(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedCondition(elementPath).displayed();
    }

    public static WebListBlockElementDisplayedCondition notDisplayed(@NotNull IsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedCondition(elementFrame).notDisplayed();
    }

    public static WebListBlockElementDisplayedCondition notDisplayed(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedCondition(elementPath).notDisplayed();
    }

    // Present

    public static WebListBlockElementPresentCondition present(@NotNull IsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentCondition(elementFrame).present();
    }

    public static WebListBlockElementPresentCondition present(@NotNull String elementPath) {
        return new WebListBlockElementPresentCondition(elementPath).present();
    }

    public static WebListBlockElementPresentCondition notPresent(@NotNull IsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentCondition(elementFrame).notPresent();
    }

    public static WebListBlockElementPresentCondition notPresent(@NotNull String elementPath) {
        return new WebListBlockElementPresentCondition(elementPath).notPresent();
    }

    // Enabled

    public static WebListBlockElementEnabledCondition enabled(@NotNull IsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledCondition(elementFrame).enabled();
    }

    public static WebListBlockElementEnabledCondition enabled(@NotNull String elementPath) {
        return new WebListBlockElementEnabledCondition(elementPath).enabled();
    }

    public static WebListBlockElementEnabledCondition disabled(@NotNull IsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledCondition(elementFrame).disabled();
    }

    public static WebListBlockElementEnabledCondition disabled(@NotNull String elementPath) {
        return new WebListBlockElementEnabledCondition(elementPath).disabled();
    }

    // Selected

    public static WebListBlockElementSelectedCondition selected(@NotNull IsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedCondition(elementFrame).selected();
    }

    public static WebListBlockElementSelectedCondition selected(@NotNull String elementPath) {
        return new WebListBlockElementSelectedCondition(elementPath).selected();
    }

    public static WebListBlockElementSelectedCondition notSelected(@NotNull IsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedCondition(elementFrame).notSelected();
    }

    public static WebListBlockElementSelectedCondition notSelected(@NotNull String elementPath) {
        return new WebListBlockElementSelectedCondition(elementPath).notSelected();
    }

    // ContainsLabel

    public static WebListBlockElementLabelTextCondition containsLabel(@NotNull String elementPath,
                                                                      @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition(elementPath, expectedText).containsLabel();
    }

    public static WebListBlockElementLabelTextCondition containsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                      @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition(elementFrame, expectedText).containsLabel();
    }

    public static WebListBlockElementLabelTextCondition notContainsLabel(@NotNull String elementPath,
                                                                         @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition(elementPath, expectedText).notContainsLabel();
    }

    public static WebListBlockElementLabelTextCondition notContainsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                         @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition(elementFrame, expectedText).notContainsLabel();
    }

    public static WebListBlockElementLabelStringValueCondition containsLabel(@NotNull String elementPath,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition(elementPath, expectedStringValue).containsLabel();
    }

    public static WebListBlockElementLabelStringValueCondition containsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition(elementFrame, expectedStringValue).containsLabel();
    }

    public static WebListBlockElementLabelStringValueCondition notContainsLabel(@NotNull String elementPath,
                                                                                @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition(elementPath, expectedStringValue).notContainsLabel();
    }

    public static WebListBlockElementLabelStringValueCondition notContainsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                                @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition(elementFrame, expectedStringValue).notContainsLabel();
    }

    public static WebListBlockElementLabelNumberValueCondition containsLabel(@NotNull String elementPath,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition(elementPath, expectedNumberValue).containsLabel();
    }

    public static WebListBlockElementLabelNumberValueCondition containsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition(elementFrame, expectedNumberValue).containsLabel();
    }

    public static WebListBlockElementLabelNumberValueCondition notContainsLabel(@NotNull String elementPath,
                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition(elementPath, expectedNumberValue).notContainsLabel();
    }

    public static WebListBlockElementLabelNumberValueCondition notContainsLabel(@NotNull GetLabelAvailable elementFrame,
                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition(elementFrame, expectedNumberValue).notContainsLabel();
    }

    // ContainsProperty

    public static WebListBlockElementPropertyTextCondition containsProperty(@NotNull WebChildElement elementFrame,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition(elementFrame, propertyName, expectedText).containsProperty();
    }

    public static WebListBlockElementPropertyTextCondition containsProperty(@NotNull String elementPath,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition(elementPath, propertyName, expectedText).containsProperty();
    }

    public static WebListBlockElementPropertyTextCondition notContainsProperty(@NotNull WebChildElement elementFrame,
                                                                               @NotNull String propertyName,
                                                                               @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition(elementFrame, propertyName, expectedText).notContainsProperty();
    }

    public static WebListBlockElementPropertyTextCondition notContainsProperty(@NotNull String elementPath,
                                                                               @NotNull String propertyName,
                                                                               @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition(elementPath, propertyName, expectedText).notContainsProperty();
    }

    public static WebListBlockElementPropertyStringValueCondition containsProperty(@NotNull WebChildElement elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition(elementFrame, propertyName, expectedStringValue).containsProperty();
    }

    public static WebListBlockElementPropertyStringValueCondition containsProperty(@NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition(elementPath, propertyName, expectedStringValue).containsProperty();
    }

    public static WebListBlockElementPropertyStringValueCondition notContainsProperty(@NotNull WebChildElement elementFrame,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition(elementFrame, propertyName, expectedStringValue).notContainsProperty();
    }

    public static WebListBlockElementPropertyStringValueCondition notContainsProperty(@NotNull String elementPath,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition(elementPath, propertyName, expectedStringValue).notContainsProperty();
    }

    public static WebListBlockElementPropertyNumberValueCondition containsProperty(@NotNull WebChildElement elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition(elementFrame, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebListBlockElementPropertyNumberValueCondition containsProperty(@NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition(elementPath, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebListBlockElementPropertyNumberValueCondition notContainsProperty(@NotNull WebChildElement elementFrame,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition(elementFrame, propertyName, expectedNumberValue).notContainsProperty();
    }

    public static WebListBlockElementPropertyNumberValueCondition notContainsProperty(@NotNull String elementPath,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition(elementPath, propertyName, expectedNumberValue).notContainsProperty();
    }

    // ContainsText

    public static WebListBlockElementTextCondition containsText(@NotNull String elementPath,
                                                                @NotNull String expectedText) {
        return new WebListBlockElementTextCondition(elementPath, expectedText).containsText();
    }

    public static WebListBlockElementTextCondition containsText(@NotNull GetTextAvailable elementFrame,
                                                                @NotNull String expectedText) {
        return new WebListBlockElementTextCondition(elementFrame, expectedText).containsText();
    }

    public static WebListBlockElementTextCondition notContainsText(@NotNull String elementPath,
                                                                   @NotNull String expectedText) {
        return new WebListBlockElementTextCondition(elementPath, expectedText).notContainsText();
    }

    public static WebListBlockElementTextCondition notContainsText(@NotNull GetTextAvailable elementFrame,
                                                                   @NotNull String expectedText) {
        return new WebListBlockElementTextCondition(elementFrame, expectedText).notContainsText();
    }

    public static WebListBlockElementTextStringValueCondition containsText(@NotNull String elementPath,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition(elementPath, expectedStringValue).containsText();
    }

    public static WebListBlockElementTextStringValueCondition containsText(@NotNull GetTextAvailable elementFrame,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition(elementFrame, expectedStringValue).containsText();
    }

    public static WebListBlockElementTextStringValueCondition notContainsText(@NotNull String elementPath,
                                                                              @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition(elementPath, expectedStringValue).notContainsText();
    }

    public static WebListBlockElementTextStringValueCondition notContainsText(@NotNull GetTextAvailable elementFrame,
                                                                              @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition(elementFrame, expectedStringValue).notContainsText();
    }

    public static WebListBlockElementTextNumberValueCondition containsText(@NotNull String elementPath,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition(elementPath, expectedNumberValue).containsText();
    }

    public static WebListBlockElementTextNumberValueCondition containsText(@NotNull GetTextAvailable elementFrame,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition(elementFrame, expectedNumberValue).containsText();
    }

    public static WebListBlockElementTextNumberValueCondition notContainsText(@NotNull String elementPath,
                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition(elementPath, expectedNumberValue).notContainsText();
    }

    public static WebListBlockElementTextNumberValueCondition notContainsText(@NotNull GetTextAvailable elementFrame,
                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition(elementFrame, expectedNumberValue).notContainsText();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTextList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTextListBlockEmptyCondition allTextBlocks() {
        return new WebTextListBlockEmptyCondition().allTextBlocks();
    }

    public static WebTextListBlockEmptyCondition noTextBlocks() {
        return new WebTextListBlockEmptyCondition().noTextBlocks();
    }

    // Index

    public static WebTextListBlockIndexCondition textBlockIndex(@NotNull Integer expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndexNot(@NotNull Integer expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    // ContainsText

    public static WebTextListBlockTextCondition containsTextBlock(@NotNull String expectedText) {
        return new WebTextListBlockTextCondition(expectedText).containsTextBlock();
    }

    public static WebTextListBlockTextCondition notContainsTextBlock(@NotNull String expectedText) {
        return new WebTextListBlockTextCondition(expectedText).notContainsTextBlock();
    }

    public static WebTextListBlockTextStringValueCondition containsTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextListBlockTextStringValueCondition(expectedStringValue).containsTextBlock();
    }

    public static WebTextListBlockTextStringValueCondition notContainsTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextListBlockTextStringValueCondition(expectedStringValue).notContainsTextBlock();
    }

    public static WebTextListBlockTextNumberValueCondition containsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextListBlockTextNumberValueCondition(expectedNumberValue).containsTextBlock();
    }

    public static WebTextListBlockTextNumberValueCondition notContainsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextListBlockTextNumberValueCondition(expectedNumberValue).notContainsTextBlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTableRowEmptyCondition allRows() {
        return new WebTableRowEmptyCondition().allRows();
    }

    public static WebTableRowEmptyCondition noRows() {
        return new WebTableRowEmptyCondition().noRows();
    }

    // Index

    public static WebTableRowIndexCondition rowIndex(@NotNull Integer expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withRowIndex();
    }

    public static WebTableRowIndexCondition rowIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withRowIndex();
    }

    public static WebTableRowIndexCondition rowIndexNot(@NotNull Integer expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withoutRowIndexNot();
    }

    public static WebTableRowIndexCondition rowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withoutRowIndexNot();
    }

    // ComponentDisplayed

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
                                                                                    @NotNull WebChildElement elementFrame,
                                                                                    @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
                                                                                    @NotNull String elementPath,
                                                                                    @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
                                                                                       @NotNull WebChildElement elementFrame,
                                                                                       @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentNotDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
                                                                                       @NotNull String elementPath,
                                                                                       @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static WebTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
                                                                                @NotNull WebChildElement elementFrame,
                                                                                @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
                                                                                @NotNull String elementPath,
                                                                                @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
                                                                                   @NotNull WebChildElement elementFrame,
                                                                                   @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentNotPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static WebTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull IsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedCondition(columnName, elementFrame).displayed();
    }

    public static WebTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementDisplayedCondition(columnName, elementPath).displayed();
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull IsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedCondition(columnName, elementFrame).notDisplayed();
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementDisplayedCondition(columnName, elementPath).notDisplayed();
    }

    // Present

    public static WebTableCellElementPresentCondition present(@NotNull String columnName, @NotNull IsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentCondition(columnName, elementFrame).present();
    }

    public static WebTableCellElementPresentCondition present(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementPresentCondition(columnName, elementPath).present();
    }

    public static WebTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull IsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentCondition(columnName, elementFrame).notPresent();
    }

    public static WebTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementPresentCondition(columnName, elementPath).notPresent();
    }

    // Enabled

    public static WebTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock).enabled();
    }

    public static WebTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName).enabled();
    }

    public static WebTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock).disabled();
    }

    public static WebTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName).disabled();
    }

    // Selected

    public static WebTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock).selected();
    }

    public static WebTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName).selected();
    }

    public static WebTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock).notSelected();
    }

    public static WebTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName).notSelected();
    }

    // ContainsLabel

    public static WebTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
                                                                      @NotNull String elementPath,
                                                                      @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementPath, expectedText).containsLabel();
    }

    public static WebTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
                                                                      @NotNull GetLabelAvailable elementFrame,
                                                                      @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).containsLabel();
    }

    public static WebTableCellElementLabelTextCondition notContainsLabel(@NotNull String columnName,
                                                                         @NotNull String elementPath,
                                                                         @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementPath, expectedText).notContainsLabel();
    }

    public static WebTableCellElementLabelTextCondition notContainsLabel(@NotNull String columnName,
                                                                         @NotNull GetLabelAvailable elementFrame,
                                                                         @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).notContainsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).containsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull GetLabelAvailable elementFrame,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).containsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition notContainsLabel(@NotNull String columnName,
                                                                                @NotNull String elementPath,
                                                                                @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).notContainsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition notContainsLabel(@NotNull String columnName,
                                                                                @NotNull GetLabelAvailable elementFrame,
                                                                                @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).notContainsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).containsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull GetLabelAvailable elementFrame,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition notContainsLabel(@NotNull String columnName,
                                                                                @NotNull String elementPath,
                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition notContainsLabel(@NotNull String columnName,
                                                                                @NotNull GetLabelAvailable elementFrame,
                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainsLabel();
    }

    // ContainsProperty

    public static WebTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
                                                                            @NotNull String elementPath,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).containsProperty();
    }

    public static WebTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
                                                                            @NotNull WebElementPropertyAvailable elementFrame,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).containsProperty();
    }

    public static WebTableCellElementPropertyTextCondition notContainsProperty(@NotNull String columnName,
                                                                               @NotNull String elementPath,
                                                                               @NotNull String propertyName,
                                                                               @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).notContainsProperty();
    }

    public static WebTableCellElementPropertyTextCondition notContainsProperty(@NotNull String columnName,
                                                                               @NotNull WebElementPropertyAvailable elementFrame,
                                                                               @NotNull String propertyName,
                                                                               @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).notContainsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).containsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull WebElementPropertyAvailable elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).containsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition notContainsProperty(@NotNull String columnName,
                                                                                      @NotNull String elementPath,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).notContainsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition notContainsProperty(@NotNull String columnName,
                                                                                      @NotNull WebElementPropertyAvailable elementFrame,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).notContainsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull WebElementPropertyAvailable elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition notContainsProperty(@NotNull String columnName,
                                                                                      @NotNull String elementPath,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).notContainsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition notContainsProperty(@NotNull String columnName,
                                                                                      @NotNull WebElementPropertyAvailable elementFrame,
                                                                                      @NotNull String propertyName,
                                                                                      @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).notContainsProperty();
    }

    // ContainsText

    public static WebTableCellElementTextCondition containsText(@NotNull String columnName,
                                                                @NotNull String elementPath,
                                                                @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementPath, expectedText).containsText();
    }

    public static WebTableCellElementTextCondition containsText(@NotNull String columnName,
                                                                @NotNull GetTextAvailable elementFrame,
                                                                @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementFrame, expectedText).containsText();
    }

    public static WebTableCellElementTextCondition notContainsText(@NotNull String columnName,
                                                                   @NotNull String elementPath,
                                                                   @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementPath, expectedText).notContainsText();
    }

    public static WebTableCellElementTextCondition notContainsText(@NotNull String columnName,
                                                                   @NotNull GetTextAvailable elementFrame,
                                                                   @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementFrame, expectedText).notContainsText();
    }

    public static WebTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull String elementPath,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).containsText();
    }

    public static WebTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull GetTextAvailable elementFrame,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).containsText();
    }

    public static WebTableCellElementTextStringValueCondition notContainsText(@NotNull String columnName,
                                                                              @NotNull String elementPath,
                                                                              @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).notContainsText();
    }

    public static WebTableCellElementTextStringValueCondition notContainsText(@NotNull String columnName,
                                                                              @NotNull GetTextAvailable elementFrame,
                                                                              @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).notContainsText();
    }

    public static WebTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull String elementPath,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).containsText();
    }

    public static WebTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull GetTextAvailable elementFrame,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsText();
    }

    public static WebTableCellElementTextNumberValueCondition notContainsText(@NotNull String columnName,
                                                                              @NotNull String elementPath,
                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainsText();
    }

    public static WebTableCellElementTextNumberValueCondition notContainsText(@NotNull String columnName,
                                                                              @NotNull GetTextAvailable elementFrame,
                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainsText();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTextTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTextTableRowEmptyCondition allTextRows() {
        return new WebTextTableRowEmptyCondition().allTextRows();
    }

    public static WebTextTableRowEmptyCondition noTextRows() {
        return new WebTextTableRowEmptyCondition().noTextRows();
    }

    // Index

    public static WebTextTableRowIndexCondition textRowIndex(@NotNull Integer expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndexNot(@NotNull Integer expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
    }

    // ContainsText

    public static WebTextTableRowTextCondition containsTextCell(@NotNull String columnName, @NotNull String expectedText) {
        return new WebTextTableRowTextCondition(columnName, expectedText).containsTextCell();
    }

    public static WebTextTableRowTextCondition notContainsTextCell(@NotNull String columnName, @NotNull String expectedText) {
        return new WebTextTableRowTextCondition(columnName, expectedText).notContainsTextCell();
    }

    public static WebTextTableRowTextStringValueCondition containsTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
        return new WebTextTableRowTextStringValueCondition(columnName, expectedStringValue).containsTextCell();
    }

    public static WebTextTableRowTextStringValueCondition notContainsTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
        return new WebTextTableRowTextStringValueCondition(columnName, expectedStringValue).notContainsTextCell();
    }

    public static WebTextTableRowTextNumberValueCondition containsTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).containsTextCell();
    }

    public static WebTextTableRowTextNumberValueCondition notContainsTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).notContainsTextCell();
    }

}
