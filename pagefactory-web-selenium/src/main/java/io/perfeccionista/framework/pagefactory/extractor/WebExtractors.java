package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowIndexExtractor;

// TODO: Сделать экстракторы для скриншотов, границ элемента и т.п.
public class WebExtractors {

    private WebExtractors() {
    }

    // WebRadioButton

    public static WebRadioButtonElementExtractor element() {
        return new WebRadioButtonElementExtractor();
    }

    public static WebRadioButtonEnabledMarkExtractor enabledMark() {
        return new WebRadioButtonEnabledMarkExtractor();
    }

    public static WebRadioButtonSelectedMarkExtractor selectedMark() {
        return new WebRadioButtonSelectedMarkExtractor();
    }

    public static WebRadioButtonLabelValueExtractor labelValue() {
        return new WebRadioButtonLabelValueExtractor();
    }

    // WebList

    public static WebListBlockIndexExtractor blockIndex() {
        return new WebListBlockIndexExtractor();
    }

    public static WebListBlockExtractor<WebMappedBlock> block() {
        return new WebListBlockExtractor<>(WebMappedBlock.class);
    }

    public static <T extends WebMappedBlock> WebListBlockExtractor<T> block(Class<T> blockClass) {
        return new WebListBlockExtractor<>(blockClass);
    }

    public static <T extends WebChildElement> WebListBlockElementExtractor<T> element(T elementMock) {
        return new WebListBlockElementExtractor<>(elementMock);
    }

    public static <T extends WebChildElement> WebListBlockElementExtractor<T> element(String elementName, Class<T> returnType) {
        return new WebListBlockElementExtractor<>(elementName, returnType);
    }

    public static WebListBlockElementDisplayedMarkExtractor displayedMark(IsDisplayedAvailable elementMock) {
        return new WebListBlockElementDisplayedMarkExtractor(elementMock);
    }

    public static WebListBlockElementDisplayedMarkExtractor displayedMark(String elementName) {
        return new WebListBlockElementDisplayedMarkExtractor(elementName);
    }

    public static WebListBlockElementPresentMarkExtractor presentMark(IsPresentAvailable elementMock) {
        return new WebListBlockElementPresentMarkExtractor(elementMock);
    }

    public static WebListBlockElementPresentMarkExtractor presentMark(String elementName) {
        return new WebListBlockElementPresentMarkExtractor(elementName);
    }

    public static WebListBlockElementEnabledMarkExtractor enabledMark(IsEnabledAvailable elementMock) {
        return new WebListBlockElementEnabledMarkExtractor(elementMock);
    }

    public static WebListBlockElementEnabledMarkExtractor enabledMark(String elementName) {
        return new WebListBlockElementEnabledMarkExtractor(elementName);
    }

    public static WebListBlockElementSelectedMarkExtractor selectedMark(IsSelectedAvailable elementMock) {
        return new WebListBlockElementSelectedMarkExtractor(elementMock);
    }

    public static WebListBlockElementSelectedMarkExtractor selectedMark(String elementName) {
        return new WebListBlockElementSelectedMarkExtractor(elementName);
    }

    public static WebListBlockElementTextValueExtractor textValue(GetTextAvailable elementMock) {
        return new WebListBlockElementTextValueExtractor(elementMock);
    }

    public static WebListBlockElementTextValueExtractor textValue(String elementName) {
        return new WebListBlockElementTextValueExtractor(elementName);
    }

    public static WebListBlockElementLabelValueExtractor labelValue(GetLabelAvailable elementMock) {
        return new WebListBlockElementLabelValueExtractor(elementMock);
    }

    public static WebListBlockElementLabelValueExtractor labelValue(String elementName) {
        return new WebListBlockElementLabelValueExtractor(elementName);
    }

    public static WebListBlockElementPropertyValueExtractor propertyValue(WebChildElement elementMock, String propertyName) {
        return new WebListBlockElementPropertyValueExtractor(elementMock, propertyName);
    }

    public static WebListBlockElementPropertyValueExtractor propertyValue(String elementName, String propertyName) {
        return new WebListBlockElementPropertyValueExtractor(elementName, propertyName);
    }

    public static WebListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(WebChildElement elementMock, String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor(elementMock, componentName);
    }

    public static WebListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(String elementName, String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor(elementName, componentName);
    }

    // WebTable

    public static WebTableRowIndexExtractor rowIndex() {
        return new WebTableRowIndexExtractor();
    }

    public static WebTableRowExtractor row() {
        return new WebTableRowExtractor();
    }

    public static WebTableCellExtractor<WebMappedBlock> cell(String columnName) {
        return new WebTableCellExtractor<>(columnName, WebMappedBlock.class);
    }

    public static <T extends WebMappedBlock> WebTableCellExtractor<T> cell(String columnName, Class<T> cellClass) {
        return new WebTableCellExtractor<>(columnName, cellClass);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(String columnName, T elementMock) {
        return new WebTableCellElementExtractor<>(columnName, elementMock);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(String columnName, String elementName, Class<T> returnType) {
        return new WebTableCellElementExtractor<>(columnName, elementName, returnType);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(String columnName, IsDisplayedAvailable elementMock) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementMock);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(String columnName, String elementName) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementName);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(String columnName, IsPresentAvailable elementMock) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementMock);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(String columnName, String elementName) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementName);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(String columnName, IsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementMock);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(String columnName, String elementName) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementName);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(String columnName, IsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementMock);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(String columnName, String elementName) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementName);
    }

    public static WebTableCellElementTextValueExtractor textValue(String columnName, GetTextAvailable elementMock) {
        return new WebTableCellElementTextValueExtractor(columnName, elementMock);
    }

    public static WebTableCellElementTextValueExtractor textValue(String columnName, String elementName) {
        return new WebTableCellElementTextValueExtractor(columnName, elementName);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(String columnName, GetLabelAvailable elementMock) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementMock);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(String columnName, String elementName) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementName);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(String columnName, WebChildElement elementMock, String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementMock, propertyName);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(String columnName, String elementName, String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementName, propertyName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(String columnName, WebChildElement elementMock, String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(String columnName, String elementName, String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementName, componentName);
    }

}
