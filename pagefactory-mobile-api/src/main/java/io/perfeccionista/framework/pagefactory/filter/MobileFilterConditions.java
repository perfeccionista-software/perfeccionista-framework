package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowEmptyCondition;
import org.jetbrains.annotations.NotNull;

public class MobileFilterConditions {

    protected MobileFilterConditions() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   MobileList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static MobileListBlockElementEmptyCondition allBlocks() {
        return new MobileListBlockElementEmptyCondition().allBlocks();
    }

    public static MobileListBlockElementEmptyCondition noBlocks() {
        return new MobileListBlockElementEmptyCondition().noBlocks();
    }

//    // Index
//
//    public static MobileListBlockIndexCondition blockIndex(@NotNull Integer expectedValue) {
//        return new MobileListBlockIndexCondition(expectedValue).withBlockIndex();
//    }
//
//    public static MobileListBlockIndexCondition blockIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileListBlockIndexCondition(expectedValue).withBlockIndex();
//    }
//
//    public static MobileListBlockIndexCondition blockIndexNot(@NotNull Integer expectedValue) {
//        return new MobileListBlockIndexCondition(expectedValue).withoutBlockIndexNot();
//    }
//
//    public static MobileListBlockIndexCondition blockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileListBlockIndexCondition(expectedValue).withoutBlockIndexNot();
//    }
//
//    // ComponentDisplayed
//
    public static MobileListBlockElementComponentDisplayedCondition componentDisplayed(@NotNull MobileChildElement elementFrame,
                                                                                       @NotNull String componentName) {
        return new MobileListBlockElementComponentDisplayedCondition(elementFrame, componentName).componentDisplayed();
    }
//
//    public static MobileListBlockElementComponentDisplayedCondition componentDisplayed(@NotNull String elementPath,
//                                                                                    @NotNull String componentName) {
//        return new MobileListBlockElementComponentDisplayedCondition(elementPath, componentName).componentDisplayed();
//    }
//
    public static MobileListBlockElementComponentDisplayedCondition componentNotDisplayed(@NotNull MobileChildElement elementFrame,
                                                                                       @NotNull String componentName) {
        return new MobileListBlockElementComponentDisplayedCondition(elementFrame, componentName).componentNotDisplayed();
    }
//
//    public static MobileListBlockElementComponentDisplayedCondition componentNotDisplayed(@NotNull String elementPath,
//                                                                                       @NotNull String componentName) {
//        return new MobileListBlockElementComponentDisplayedCondition(elementPath, componentName).componentNotDisplayed();
//    }
//
//    // ComponentPresent
//
//    public static MobileListBlockElementComponentPresentCondition componentPresent(@NotNull MobileChildElement elementFrame,
//                                                                                @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentCondition(elementFrame, componentName).componentPresent();
//    }
//
//    public static MobileListBlockElementComponentPresentCondition componentPresent(@NotNull String elementPath,
//                                                                                @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentCondition(elementPath, componentName).componentPresent();
//    }
//
//    public static MobileListBlockElementComponentPresentCondition componentNotPresent(@NotNull MobileChildElement elementFrame,
//                                                                                   @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentCondition(elementFrame, componentName).componentNotPresent();
//    }
//
//    public static MobileListBlockElementComponentPresentCondition componentNotPresent(@NotNull String elementPath,
//                                                                                   @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentCondition(elementPath, componentName).componentNotPresent();
//    }
//
//    // Displayed
//
//    public static MobileListBlockElementDisplayedCondition displayed(@NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileListBlockElementDisplayedCondition(elementFrame).displayed();
//    }
//
//    public static MobileListBlockElementDisplayedCondition displayed(@NotNull String elementPath) {
//        return new MobileListBlockElementDisplayedCondition(elementPath).displayed();
//    }
//
//    public static MobileListBlockElementDisplayedCondition notDisplayed(@NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileListBlockElementDisplayedCondition(elementFrame).notDisplayed();
//    }
//
//    public static MobileListBlockElementDisplayedCondition notDisplayed(@NotNull String elementPath) {
//        return new MobileListBlockElementDisplayedCondition(elementPath).notDisplayed();
//    }
//
//    // Present
//
//    public static MobileListBlockElementPresentCondition present(@NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileListBlockElementPresentCondition(elementFrame).present();
//    }
//
//    public static MobileListBlockElementPresentCondition present(@NotNull String elementPath) {
//        return new MobileListBlockElementPresentCondition(elementPath).present();
//    }
//
//    public static MobileListBlockElementPresentCondition notPresent(@NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileListBlockElementPresentCondition(elementFrame).notPresent();
//    }
//
//    public static MobileListBlockElementPresentCondition notPresent(@NotNull String elementPath) {
//        return new MobileListBlockElementPresentCondition(elementPath).notPresent();
//    }
//
//    // Enabled
//
//    public static MobileListBlockElementEnabledCondition enabled(@NotNull MobileIsEnabledAvailable elementFrame) {
//        return new MobileListBlockElementEnabledCondition(elementFrame).enabled();
//    }
//
//    public static MobileListBlockElementEnabledCondition enabled(@NotNull String elementPath) {
//        return new MobileListBlockElementEnabledCondition(elementPath).enabled();
//    }
//
//    public static MobileListBlockElementEnabledCondition disabled(@NotNull MobileIsEnabledAvailable elementFrame) {
//        return new MobileListBlockElementEnabledCondition(elementFrame).disabled();
//    }
//
//    public static MobileListBlockElementEnabledCondition disabled(@NotNull String elementPath) {
//        return new MobileListBlockElementEnabledCondition(elementPath).disabled();
//    }
//
//    // Selected
//
//    public static MobileListBlockElementSelectedCondition selected(@NotNull MobileIsSelectedAvailable elementFrame) {
//        return new MobileListBlockElementSelectedCondition(elementFrame).selected();
//    }
//
//    public static MobileListBlockElementSelectedCondition selected(@NotNull String elementPath) {
//        return new MobileListBlockElementSelectedCondition(elementPath).selected();
//    }
//
//    public static MobileListBlockElementSelectedCondition notSelected(@NotNull MobileIsSelectedAvailable elementFrame) {
//        return new MobileListBlockElementSelectedCondition(elementFrame).notSelected();
//    }
//
//    public static MobileListBlockElementSelectedCondition notSelected(@NotNull String elementPath) {
//        return new MobileListBlockElementSelectedCondition(elementPath).notSelected();
//    }
//
//    // ContainsLabel
//
//    public static MobileListBlockElementLabelTextCondition containsLabel(@NotNull String elementPath,
//                                                                      @NotNull String expectedText) {
//        return new MobileListBlockElementLabelTextCondition(elementPath, expectedText).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelTextCondition containsLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                      @NotNull String expectedText) {
//        return new MobileListBlockElementLabelTextCondition(elementFrame, expectedText).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelTextCondition notContainLabel(@NotNull String elementPath,
//                                                                        @NotNull String expectedText) {
//        return new MobileListBlockElementLabelTextCondition(elementPath, expectedText).notContainLabel();
//    }
//
//    public static MobileListBlockElementLabelTextCondition notContainLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                        @NotNull String expectedText) {
//        return new MobileListBlockElementLabelTextCondition(elementFrame, expectedText).notContainLabel();
//    }
//
//    public static MobileListBlockElementLabelStringValueCondition containsLabel(@NotNull String elementPath,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementLabelStringValueCondition(elementPath, expectedStringValue).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelStringValueCondition containsLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementLabelStringValueCondition(elementFrame, expectedStringValue).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelStringValueCondition notContainLabel(@NotNull String elementPath,
//                                                                               @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementLabelStringValueCondition(elementPath, expectedStringValue).notContainLabel();
//    }
//
//    public static MobileListBlockElementLabelStringValueCondition notContainLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                               @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementLabelStringValueCondition(elementFrame, expectedStringValue).notContainLabel();
//    }
//
//    public static MobileListBlockElementLabelNumberValueCondition containsLabel(@NotNull String elementPath,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementLabelNumberValueCondition(elementPath, expectedNumberValue).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelNumberValueCondition containsLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementLabelNumberValueCondition(elementFrame, expectedNumberValue).containsLabel();
//    }
//
//    public static MobileListBlockElementLabelNumberValueCondition notContainLabel(@NotNull String elementPath,
//                                                                               @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementLabelNumberValueCondition(elementPath, expectedNumberValue).notContainLabel();
//    }
//
//    public static MobileListBlockElementLabelNumberValueCondition notContainLabel(@NotNull MobileGetLabelAvailable elementFrame,
//                                                                               @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementLabelNumberValueCondition(elementFrame, expectedNumberValue).notContainLabel();
//    }
//
//    // ContainsProperty
//
//    public static MobileListBlockElementPropertyTextCondition containsProperty(@NotNull MobileChildElement elementFrame,
//                                                                            @NotNull String propertyName,
//                                                                            @NotNull String expectedText) {
//        return new MobileListBlockElementPropertyTextCondition(elementFrame, propertyName, expectedText).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyTextCondition containsProperty(@NotNull String elementPath,
//                                                                            @NotNull String propertyName,
//                                                                            @NotNull String expectedText) {
//        return new MobileListBlockElementPropertyTextCondition(elementPath, propertyName, expectedText).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyTextCondition notContainProperty(@NotNull MobileChildElement elementFrame,
//                                                                              @NotNull String propertyName,
//                                                                              @NotNull String expectedText) {
//        return new MobileListBlockElementPropertyTextCondition(elementFrame, propertyName, expectedText).notContainProperty();
//    }
//
//    public static MobileListBlockElementPropertyTextCondition notContainProperty(@NotNull String elementPath,
//                                                                              @NotNull String propertyName,
//                                                                              @NotNull String expectedText) {
//        return new MobileListBlockElementPropertyTextCondition(elementPath, propertyName, expectedText).notContainProperty();
//    }
//
//    public static MobileListBlockElementPropertyStringValueCondition containsProperty(@NotNull MobileChildElement elementFrame,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementPropertyStringValueCondition(elementFrame, propertyName, expectedStringValue).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyStringValueCondition containsProperty(@NotNull String elementPath,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementPropertyStringValueCondition(elementPath, propertyName, expectedStringValue).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyStringValueCondition notContainProperty(@NotNull MobileChildElement elementFrame,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementPropertyStringValueCondition(elementFrame, propertyName, expectedStringValue).notContainProperty();
//    }
//
//    public static MobileListBlockElementPropertyStringValueCondition notContainProperty(@NotNull String elementPath,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementPropertyStringValueCondition(elementPath, propertyName, expectedStringValue).notContainProperty();
//    }
//
//    public static MobileListBlockElementPropertyNumberValueCondition containsProperty(@NotNull MobileChildElement elementFrame,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull NumberValue<? extends Number> expectedNumberValue) {
//        return new MobileListBlockElementPropertyNumberValueCondition(elementFrame, propertyName, expectedNumberValue).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyNumberValueCondition containsProperty(@NotNull String elementPath,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull NumberValue<? extends Number> expectedNumberValue) {
//        return new MobileListBlockElementPropertyNumberValueCondition(elementPath, propertyName, expectedNumberValue).containsProperty();
//    }
//
//    public static MobileListBlockElementPropertyNumberValueCondition notContainProperty(@NotNull MobileChildElement elementFrame,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull NumberValue<? extends Number> expectedNumberValue) {
//        return new MobileListBlockElementPropertyNumberValueCondition(elementFrame, propertyName, expectedNumberValue).notContainProperty();
//    }
//
//    public static MobileListBlockElementPropertyNumberValueCondition notContainProperty(@NotNull String elementPath,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull NumberValue<? extends Number> expectedNumberValue) {
//        return new MobileListBlockElementPropertyNumberValueCondition(elementPath, propertyName, expectedNumberValue).notContainProperty();
//    }
//
//    // ContainsText
//
//    public static MobileListBlockElementTextCondition containsText(@NotNull String elementPath,
//                                                                @NotNull String expectedText) {
//        return new MobileListBlockElementTextCondition(elementPath, expectedText).containsText();
//    }
//
    public static MobileListBlockElementTextCondition containsText(@NotNull MobileGetTextAvailable elementFrame,
                                                                @NotNull String expectedText) {
        return new MobileListBlockElementTextCondition(elementFrame, expectedText).containsText();
    }
//
//    public static MobileListBlockElementTextCondition notContainText(@NotNull String elementPath,
//                                                                  @NotNull String expectedText) {
//        return new MobileListBlockElementTextCondition(elementPath, expectedText).notContainText();
//    }
//
    public static MobileListBlockElementTextCondition notContainText(@NotNull MobileGetTextAvailable elementFrame,
                                                                  @NotNull String expectedText) {
        return new MobileListBlockElementTextCondition(elementFrame, expectedText).notContainText();
    }

//    public static MobileListBlockElementTextStringValueCondition containsText(@NotNull String elementPath,
//                                                                           @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementTextStringValueCondition(elementPath, expectedStringValue).containsText();
//    }
//
//    public static MobileListBlockElementTextStringValueCondition containsText(@NotNull MobileGetTextAvailable elementFrame,
//                                                                           @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementTextStringValueCondition(elementFrame, expectedStringValue).containsText();
//    }
//
//    public static MobileListBlockElementTextStringValueCondition notContainText(@NotNull String elementPath,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementTextStringValueCondition(elementPath, expectedStringValue).notContainText();
//    }
//
//    public static MobileListBlockElementTextStringValueCondition notContainText(@NotNull MobileGetTextAvailable elementFrame,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileListBlockElementTextStringValueCondition(elementFrame, expectedStringValue).notContainText();
//    }
//
//    public static MobileListBlockElementTextNumberValueCondition containsText(@NotNull String elementPath,
//                                                                           @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementTextNumberValueCondition(elementPath, expectedNumberValue).containsText();
//    }
//
//    public static MobileListBlockElementTextNumberValueCondition containsText(@NotNull MobileGetTextAvailable elementFrame,
//                                                                           @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementTextNumberValueCondition(elementFrame, expectedNumberValue).containsText();
//    }
//
//    public static MobileListBlockElementTextNumberValueCondition notContainText(@NotNull String elementPath,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementTextNumberValueCondition(elementPath, expectedNumberValue).notContainText();
//    }
//
//    public static MobileListBlockElementTextNumberValueCondition notContainText(@NotNull MobileGetTextAvailable elementFrame,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileListBlockElementTextNumberValueCondition(elementFrame, expectedNumberValue).notContainText();
//    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   MobileTextList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static MobileTextListBlockEmptyCondition allTextBlocks() {
        return new MobileTextListBlockEmptyCondition().allTextBlocks();
    }

    public static MobileTextListBlockEmptyCondition noTextBlocks() {
        return new MobileTextListBlockEmptyCondition().noTextBlocks();
    }

//    // Index
//
//    public static MobileTextListBlockIndexCondition textBlockIndex(@NotNull Integer expectedValue) {
//        return new MobileTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
//    }
//
//    public static MobileTextListBlockIndexCondition textBlockIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
//    }
//
//    public static MobileTextListBlockIndexCondition textBlockIndexNot(@NotNull Integer expectedValue) {
//        return new MobileTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
//    }
//
//    public static MobileTextListBlockIndexCondition textBlockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
//    }
//
//    // ContainsText
//
//    public static MobileTextListBlockTextCondition containsTextBlock(@NotNull String expectedText) {
//        return new MobileTextListBlockTextCondition(expectedText).containsTextBlock();
//    }
//
//    public static MobileTextListBlockTextCondition notContainTextBlock(@NotNull String expectedText) {
//        return new MobileTextListBlockTextCondition(expectedText).notContainTextBlock();
//    }
//
//    public static MobileTextListBlockTextStringValueCondition containsTextBlock(@NotNull StringValue expectedStringValue) {
//        return new MobileTextListBlockTextStringValueCondition(expectedStringValue).containsTextBlock();
//    }
//
//    public static MobileTextListBlockTextStringValueCondition notContainTextBlock(@NotNull StringValue expectedStringValue) {
//        return new MobileTextListBlockTextStringValueCondition(expectedStringValue).notContainTextBlock();
//    }
//
//    public static MobileTextListBlockTextNumberValueCondition containsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTextListBlockTextNumberValueCondition(expectedNumberValue).containsTextBlock();
//    }
//
//    public static MobileTextListBlockTextNumberValueCondition notContainTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTextListBlockTextNumberValueCondition(expectedNumberValue).notContainTextBlock();
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   MobileTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static MobileTableRowEmptyCondition allRows() {
        return new MobileTableRowEmptyCondition().allRows();
    }

    public static MobileTableRowEmptyCondition noRows() {
        return new MobileTableRowEmptyCondition().noRows();
    }

//    // Index
//
//    public static MobileTableRowIndexCondition rowIndex(@NotNull Integer expectedValue) {
//        return new MobileTableRowIndexCondition(expectedValue).withRowIndex();
//    }
//
//    public static MobileTableRowIndexCondition rowIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTableRowIndexCondition(expectedValue).withRowIndex();
//    }
//
//    public static MobileTableRowIndexCondition rowIndexNot(@NotNull Integer expectedValue) {
//        return new MobileTableRowIndexCondition(expectedValue).withoutRowIndexNot();
//    }
//
//    public static MobileTableRowIndexCondition rowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTableRowIndexCondition(expectedValue).withoutRowIndexNot();
//    }
//
//    // ComponentDisplayed
//
//    public static MobileTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
//                                                                                    @NotNull MobileChildElement elementFrame,
//                                                                                    @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentDisplayed();
//    }
//
//    public static MobileTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
//                                                                                    @NotNull String elementPath,
//                                                                                    @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentDisplayed();
//    }
//
//    public static MobileTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
//                                                                                       @NotNull MobileChildElement elementFrame,
//                                                                                       @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentNotDisplayed();
//    }
//
//    public static MobileTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
//                                                                                       @NotNull String elementPath,
//                                                                                       @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentNotDisplayed();
//    }
//
//    // ComponentPresent
//
//    public static MobileTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
//                                                                                @NotNull MobileChildElement elementFrame,
//                                                                                @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentPresent();
//    }
//
//    public static MobileTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
//                                                                                @NotNull String elementPath,
//                                                                                @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentPresent();
//    }
//
//    public static MobileTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
//                                                                                   @NotNull MobileChildElement elementFrame,
//                                                                                   @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentNotPresent();
//    }
//
//    public static MobileTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
//                                                                                   @NotNull String elementPath,
//                                                                                   @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentNotPresent();
//    }
//
//    // Displayed
//
//    public static MobileTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileTableCellElementDisplayedCondition(columnName, elementFrame).displayed();
//    }
//
//    public static MobileTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull String elementPath) {
//        return new MobileTableCellElementDisplayedCondition(columnName, elementPath).displayed();
//    }
//
//    public static MobileTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileTableCellElementDisplayedCondition(columnName, elementFrame).notDisplayed();
//    }
//
//    public static MobileTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull String elementPath) {
//        return new MobileTableCellElementDisplayedCondition(columnName, elementPath).notDisplayed();
//    }
//
//    // Present
//
//    public static MobileTableCellElementPresentCondition present(@NotNull String columnName, @NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileTableCellElementPresentCondition(columnName, elementFrame).present();
//    }
//
//    public static MobileTableCellElementPresentCondition present(@NotNull String columnName, @NotNull String elementPath) {
//        return new MobileTableCellElementPresentCondition(columnName, elementPath).present();
//    }
//
//    public static MobileTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileTableCellElementPresentCondition(columnName, elementFrame).notPresent();
//    }
//
//    public static MobileTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull String elementPath) {
//        return new MobileTableCellElementPresentCondition(columnName, elementPath).notPresent();
//    }
//
//    // Enabled
//
//    public static MobileTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull MobileIsEnabledAvailable elementMock) {
//        return new MobileTableCellElementEnabledCondition(columnName, elementMock).enabled();
//    }
//
//    public static MobileTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull String elementName) {
//        return new MobileTableCellElementEnabledCondition(columnName, elementName).enabled();
//    }
//
//    public static MobileTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull MobileIsEnabledAvailable elementMock) {
//        return new MobileTableCellElementEnabledCondition(columnName, elementMock).disabled();
//    }
//
//    public static MobileTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull String elementName) {
//        return new MobileTableCellElementEnabledCondition(columnName, elementName).disabled();
//    }
//
//    // Selected
//
//    public static MobileTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull MobileIsSelectedAvailable elementMock) {
//        return new MobileTableCellElementSelectedCondition(columnName, elementMock).selected();
//    }
//
//    public static MobileTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull String elementName) {
//        return new MobileTableCellElementSelectedCondition(columnName, elementName).selected();
//    }
//
//    public static MobileTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull MobileIsSelectedAvailable elementMock) {
//        return new MobileTableCellElementSelectedCondition(columnName, elementMock).notSelected();
//    }
//
//    public static MobileTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull String elementName) {
//        return new MobileTableCellElementSelectedCondition(columnName, elementName).notSelected();
//    }
//
//    // ContainsLabel
//
//    public static MobileTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
//                                                                      @NotNull String elementPath,
//                                                                      @NotNull String expectedText) {
//        return new MobileTableCellElementLabelTextCondition(columnName, elementPath, expectedText).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
//                                                                      @NotNull MobileGetLabelAvailable elementFrame,
//                                                                      @NotNull String expectedText) {
//        return new MobileTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelTextCondition notContainLabel(@NotNull String columnName,
//                                                                        @NotNull String elementPath,
//                                                                        @NotNull String expectedText) {
//        return new MobileTableCellElementLabelTextCondition(columnName, elementPath, expectedText).notContainLabel();
//    }
//
//    public static MobileTableCellElementLabelTextCondition notContainLabel(@NotNull String columnName,
//                                                                        @NotNull MobileGetLabelAvailable elementFrame,
//                                                                        @NotNull String expectedText) {
//        return new MobileTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).notContainLabel();
//    }
//
//    public static MobileTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
//                                                                             @NotNull String elementPath,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
//                                                                             @NotNull MobileGetLabelAvailable elementFrame,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelStringValueCondition notContainLabel(@NotNull String columnName,
//                                                                               @NotNull String elementPath,
//                                                                               @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).notContainLabel();
//    }
//
//    public static MobileTableCellElementLabelStringValueCondition notContainLabel(@NotNull String columnName,
//                                                                               @NotNull MobileGetLabelAvailable elementFrame,
//                                                                               @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).notContainLabel();
//    }
//
//    public static MobileTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
//                                                                             @NotNull String elementPath,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
//                                                                             @NotNull MobileGetLabelAvailable elementFrame,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsLabel();
//    }
//
//    public static MobileTableCellElementLabelNumberValueCondition notContainLabel(@NotNull String columnName,
//                                                                               @NotNull String elementPath,
//                                                                               @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainLabel();
//    }
//
//    public static MobileTableCellElementLabelNumberValueCondition notContainLabel(@NotNull String columnName,
//                                                                               @NotNull MobileGetLabelAvailable elementFrame,
//                                                                               @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainLabel();
//    }
//
//    // ContainsProperty
//
//    public static MobileTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
//                                                                            @NotNull String elementPath,
//                                                                            @NotNull String propertyName,
//                                                                            @NotNull String expectedText) {
//        return new MobileTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
//                                                                            @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                            @NotNull String propertyName,
//                                                                            @NotNull String expectedText) {
//        return new MobileTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyTextCondition notContainProperty(@NotNull String columnName,
//                                                                              @NotNull String elementPath,
//                                                                              @NotNull String propertyName,
//                                                                              @NotNull String expectedText) {
//        return new MobileTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).notContainProperty();
//    }
//
//    public static MobileTableCellElementPropertyTextCondition notContainProperty(@NotNull String columnName,
//                                                                              @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                              @NotNull String propertyName,
//                                                                              @NotNull String expectedText) {
//        return new MobileTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).notContainProperty();
//    }
//
//    public static MobileTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
//                                                                                   @NotNull String elementPath,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
//                                                                                   @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyStringValueCondition notContainProperty(@NotNull String columnName,
//                                                                                     @NotNull String elementPath,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).notContainProperty();
//    }
//
//    public static MobileTableCellElementPropertyStringValueCondition notContainProperty(@NotNull String columnName,
//                                                                                     @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).notContainProperty();
//    }
//
//    public static MobileTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
//                                                                                   @NotNull String elementPath,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
//                                                                                   @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                                   @NotNull String propertyName,
//                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).containsProperty();
//    }
//
//    public static MobileTableCellElementPropertyNumberValueCondition notContainProperty(@NotNull String columnName,
//                                                                                     @NotNull String elementPath,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).notContainProperty();
//    }
//
//    public static MobileTableCellElementPropertyNumberValueCondition notContainProperty(@NotNull String columnName,
//                                                                                     @NotNull MobileElementPropertyAvailable elementFrame,
//                                                                                     @NotNull String propertyName,
//                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).notContainProperty();
//    }
//
//    // ContainsText
//
//    public static MobileTableCellElementTextCondition containsText(@NotNull String columnName,
//                                                                @NotNull String elementPath,
//                                                                @NotNull String expectedText) {
//        return new MobileTableCellElementTextCondition(columnName, elementPath, expectedText).containsText();
//    }
//
//    public static MobileTableCellElementTextCondition containsText(@NotNull String columnName,
//                                                                @NotNull MobileGetTextAvailable elementFrame,
//                                                                @NotNull String expectedText) {
//        return new MobileTableCellElementTextCondition(columnName, elementFrame, expectedText).containsText();
//    }
//
//    public static MobileTableCellElementTextCondition notContainText(@NotNull String columnName,
//                                                                  @NotNull String elementPath,
//                                                                  @NotNull String expectedText) {
//        return new MobileTableCellElementTextCondition(columnName, elementPath, expectedText).notContainText();
//    }
//
//    public static MobileTableCellElementTextCondition notContainText(@NotNull String columnName,
//                                                                  @NotNull MobileGetTextAvailable elementFrame,
//                                                                  @NotNull String expectedText) {
//        return new MobileTableCellElementTextCondition(columnName, elementFrame, expectedText).notContainText();
//    }
//
//    public static MobileTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
//                                                                           @NotNull String elementPath,
//                                                                           @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).containsText();
//    }
//
//    public static MobileTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
//                                                                           @NotNull MobileGetTextAvailable elementFrame,
//                                                                           @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).containsText();
//    }
//
//    public static MobileTableCellElementTextStringValueCondition notContainText(@NotNull String columnName,
//                                                                             @NotNull String elementPath,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).notContainText();
//    }
//
//    public static MobileTableCellElementTextStringValueCondition notContainText(@NotNull String columnName,
//                                                                             @NotNull MobileGetTextAvailable elementFrame,
//                                                                             @NotNull StringValue expectedStringValue) {
//        return new MobileTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).notContainText();
//    }
//
//    public static MobileTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
//                                                                           @NotNull String elementPath,
//                                                                           @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).containsText();
//    }
//
//    public static MobileTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
//                                                                           @NotNull MobileGetTextAvailable elementFrame,
//                                                                           @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsText();
//    }
//
//    public static MobileTableCellElementTextNumberValueCondition notContainText(@NotNull String columnName,
//                                                                             @NotNull String elementPath,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainText();
//    }
//
//    public static MobileTableCellElementTextNumberValueCondition notContainText(@NotNull String columnName,
//                                                                             @NotNull MobileGetTextAvailable elementFrame,
//                                                                             @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainText();
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   MobileTextTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static MobileTextTableRowEmptyCondition allTextRows() {
        return new MobileTextTableRowEmptyCondition().allTextRows();
    }

    public static MobileTextTableRowEmptyCondition noTextRows() {
        return new MobileTextTableRowEmptyCondition().noTextRows();
    }

//    // Index
//
//    public static MobileTextTableRowIndexCondition textRowIndex(@NotNull Integer expectedValue) {
//        return new MobileTextTableRowIndexCondition(expectedValue).withTextRowIndex();
//    }
//
//    public static MobileTextTableRowIndexCondition textRowIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTextTableRowIndexCondition(expectedValue).withTextRowIndex();
//    }
//
//    public static MobileTextTableRowIndexCondition textRowIndexNot(@NotNull Integer expectedValue) {
//        return new MobileTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
//    }
//
//    public static MobileTextTableRowIndexCondition textRowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new MobileTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
//    }
//
//    // ContainsText
//
//    public static MobileTextTableRowTextCondition containsTextCell(@NotNull String columnName, @NotNull String expectedText) {
//        return new MobileTextTableRowTextCondition(columnName, expectedText).containsTextCell();
//    }
//
//    public static MobileTextTableRowTextCondition notContainTextCell(@NotNull String columnName, @NotNull String expectedText) {
//        return new MobileTextTableRowTextCondition(columnName, expectedText).notContainTextCell();
//    }
//
//    public static MobileTextTableRowTextStringValueCondition containsTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
//        return new MobileTextTableRowTextStringValueCondition(columnName, expectedStringValue).containsTextCell();
//    }
//
//    public static MobileTextTableRowTextStringValueCondition notContainTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
//        return new MobileTextTableRowTextStringValueCondition(columnName, expectedStringValue).notContainTextCell();
//    }
//
//    public static MobileTextTableRowTextNumberValueCondition containsTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).containsTextCell();
//    }
//
//    public static MobileTextTableRowTextNumberValueCondition notContainTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
//        return new MobileTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).notContainTextCell();
//    }

}
