package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentPresentMarkExtractor;
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
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowIndexExtractor;
import org.jetbrains.annotations.NotNull;

// TODO: Сделать экстракторы для скриншотов, границ элемента и т.п.
public class WebExtractors {

    private WebExtractors() {
    }

    // WebRadioButton

    public static WebRadioButtonIndexExtractor index() {
        return new WebRadioButtonIndexExtractor();
    }

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

    public static WebListBlockExtractor<WebBlock> block() {
        return new WebListBlockExtractor<>(WebBlock.class);
    }

    public static <T extends WebBlock> WebListBlockExtractor<T> block(@NotNull Class<T> blockClass) {
        return new WebListBlockExtractor<>(blockClass);
    }

    public static <T extends WebChildElement> WebListBlockElementExtractor<T> element(@NotNull T elementFrame) {
        return new WebListBlockElementExtractor<>(elementFrame);
    }

    public static <T extends WebChildElement> WebListBlockElementExtractor<T> element(@NotNull String elementPath, @NotNull Class<T> returnType) {
        return new WebListBlockElementExtractor<>(elementPath, returnType);
    }

    public static WebListBlockElementDisplayedMarkExtractor displayedMark(@NotNull IsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedMarkExtractor(elementFrame);
    }

    public static WebListBlockElementDisplayedMarkExtractor displayedMark(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedMarkExtractor(elementPath);
    }

    public static WebListBlockElementPresentMarkExtractor presentMark(@NotNull IsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentMarkExtractor(elementFrame);
    }

    public static WebListBlockElementPresentMarkExtractor presentMark(@NotNull String elementPath) {
        return new WebListBlockElementPresentMarkExtractor(elementPath);
    }

    public static WebListBlockElementEnabledMarkExtractor enabledMark(@NotNull IsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledMarkExtractor(elementFrame);
    }

    public static WebListBlockElementEnabledMarkExtractor enabledMark(@NotNull String elementPath) {
        return new WebListBlockElementEnabledMarkExtractor(elementPath);
    }

    public static WebListBlockElementSelectedMarkExtractor selectedMark(@NotNull IsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedMarkExtractor(elementFrame);
    }

    public static WebListBlockElementSelectedMarkExtractor selectedMark(@NotNull String elementPath) {
        return new WebListBlockElementSelectedMarkExtractor(elementPath);
    }

    public static WebListBlockElementTextValueExtractor textValue(@NotNull GetTextAvailable elementFrame) {
        return new WebListBlockElementTextValueExtractor(elementFrame);
    }

    public static WebListBlockElementTextValueExtractor textValue(@NotNull String elementPath) {
        return new WebListBlockElementTextValueExtractor(elementPath);
    }

    public static WebListBlockElementLabelValueExtractor labelValue(@NotNull GetLabelAvailable elementFrame) {
        return new WebListBlockElementLabelValueExtractor(elementFrame);
    }

    public static WebListBlockElementLabelValueExtractor labelValue(@NotNull String elementPath) {
        return new WebListBlockElementLabelValueExtractor(elementPath);
    }

    public static WebListBlockElementPropertyValueExtractor propertyValue(@NotNull WebChildElement elementFrame, @NotNull String propertyName) {
        return new WebListBlockElementPropertyValueExtractor(elementFrame, propertyName);
    }

    public static WebListBlockElementPropertyValueExtractor propertyValue(@NotNull String elementPath, @NotNull String propertyName) {
        return new WebListBlockElementPropertyValueExtractor(elementPath, propertyName);
    }

    public static WebListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull WebComponentAvailable elementFrame,
                                                                                            @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor(elementFrame, componentName);
    }

    public static WebListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String elementPath,
                                                                                            @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor(elementPath, componentName);
    }

    public static WebListBlockElementComponentPresentMarkExtractor componentPresentMark(@NotNull WebComponentAvailable elementFrame,
                                                                                        @NotNull String componentName) {
        return new WebListBlockElementComponentPresentMarkExtractor(elementFrame, componentName);
    }

    public static WebListBlockElementComponentPresentMarkExtractor componentPresentMark(@NotNull String elementPath,
                                                                                        @NotNull String componentName) {
        return new WebListBlockElementComponentPresentMarkExtractor(elementPath, componentName);
    }

    // WebTextList

    public static WebTextListBlockIndexExtractor textBlockIndex() {
        return new WebTextListBlockIndexExtractor();
    }

    public static WebTextListBlockExtractor textBlock() {
        return new WebTextListBlockExtractor();
    }

    public static WebTextListBlockElementExtractor textBlockElement() {
        return new WebTextListBlockElementExtractor();
    }

    public static WebTextListBlockTextValueExtractor textBlockValue() {
        return new WebTextListBlockTextValueExtractor();
    }

    // WebTable

    public static WebTableRowIndexExtractor rowIndex() {
        return new WebTableRowIndexExtractor();
    }

    public static WebTableRowExtractor row() {
        return new WebTableRowExtractor();
    }

    public static WebTableCellExtractor<WebBlock> cell(@NotNull String columnName) {
        return new WebTableCellExtractor<>(columnName, WebBlock.class);
    }

    public static <T extends WebBlock> WebTableCellExtractor<T> cell(@NotNull String columnName,
                                                                     @NotNull Class<T> cellClass) {
        return new WebTableCellExtractor<>(columnName, cellClass);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(@NotNull String columnName,
                                                                                      @NotNull T elementFrame) {
        return new WebTableCellElementExtractor<>(columnName, elementFrame);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(@NotNull String columnName,
                                                                                      @NotNull String elementPath,
                                                                                      @NotNull Class<T> returnType) {
        return new WebTableCellElementExtractor<>(columnName, elementPath, returnType);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
                                                                          @NotNull IsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
                                                                          @NotNull String elementPath) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
                                                                      @NotNull IsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
                                                                      @NotNull String elementPath) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
                                                                      @NotNull IsEnabledAvailable elementFrame) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
                                                                      @NotNull String elementPath) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
                                                                        @NotNull IsSelectedAvailable elementFrame) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
                                                                        @NotNull String elementPath) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementTextValueExtractor textValue(@NotNull String columnName,
                                                                  @NotNull GetTextAvailable elementFrame) {
        return new WebTableCellElementTextValueExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementTextValueExtractor textValue(@NotNull String columnName,
                                                                  @NotNull String elementPath) {
        return new WebTableCellElementTextValueExtractor(columnName, elementPath);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
                                                                    @NotNull GetLabelAvailable elementFrame) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
                                                                    @NotNull String elementPath) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementPath);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
                                                                          @NotNull WebChildElement elementFrame,
                                                                          @NotNull String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementFrame, propertyName);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
                                                                          @NotNull String elementPath,
                                                                          @NotNull String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementPath, propertyName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
                                                                                            @NotNull WebChildElement elementMock,
                                                                                            @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
                                                                                            @NotNull String elementName,
                                                                                            @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementName, componentName);
    }

    public static WebTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
                                                                                        @NotNull WebChildElement elementMock,
                                                                                        @NotNull String componentName) {
        return new WebTableCellElementComponentPresentMarkExtractor(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
                                                                                        @NotNull String elementName,
                                                                                        @NotNull String componentName) {
        return new WebTableCellElementComponentPresentMarkExtractor(columnName, elementName, componentName);
    }

    // WebTextTable

    public static WebTextTableRowIndexExtractor textRowIndex() {
        return new WebTextTableRowIndexExtractor();
    }

    public static WebTextTableRowExtractor textRow() {
        return new WebTextTableRowExtractor();
    }

    public static WebTextTableCellElementExtractor textRowElement(@NotNull String columnName) {
        return new WebTextTableCellElementExtractor(columnName);
    }

    public static WebTextTableCellTextValueExtractor textCellValue(@NotNull String columnName) {
        return new WebTextTableCellTextValueExtractor(columnName);
    }

}