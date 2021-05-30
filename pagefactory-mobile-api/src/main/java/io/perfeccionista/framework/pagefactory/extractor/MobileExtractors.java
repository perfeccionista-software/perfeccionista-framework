package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableCellExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableCellTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableRowIndexExtractor;
import org.jetbrains.annotations.NotNull;

public class MobileExtractors {

    private MobileExtractors() {
    }

    // MobileList

    public static MobileListBlockIndexExtractor blockIndex() {
        return new MobileListBlockIndexExtractor();
    }

    public static MobileListBlockExtractor<MobileBlock> block() {
        return new MobileListBlockExtractor<>(MobileBlock.class);
    }

    public static <T extends MobileBlock> MobileListBlockExtractor<T> block(@NotNull Class<T> blockClass) {
        return new MobileListBlockExtractor<>(blockClass);
    }

    public static <T extends MobileChildElement> MobileListBlockElementExtractor<T> element(@NotNull T elementFrame) {
        return new MobileListBlockElementExtractor<>(elementFrame);
    }

    public static <T extends MobileChildElement> MobileListBlockElementExtractor<T> element(@NotNull String elementPath, @NotNull Class<T> returnType) {
        return new MobileListBlockElementExtractor<>(elementPath, returnType);
    }

//    public static MobileListBlockElementDisplayedMarkExtractor displayedMark(@NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileListBlockElementDisplayedMarkExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementDisplayedMarkExtractor displayedMark(@NotNull String elementPath) {
//        return new MobileListBlockElementDisplayedMarkExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementPresentMarkExtractor presentMark(@NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileListBlockElementPresentMarkExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementPresentMarkExtractor presentMark(@NotNull String elementPath) {
//        return new MobileListBlockElementPresentMarkExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementEnabledMarkExtractor enabledMark(@NotNull MobileIsEnabledAvailable elementFrame) {
//        return new MobileListBlockElementEnabledMarkExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementEnabledMarkExtractor enabledMark(@NotNull String elementPath) {
//        return new MobileListBlockElementEnabledMarkExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementSelectedMarkExtractor selectedMark(@NotNull MobileIsSelectedAvailable elementFrame) {
//        return new MobileListBlockElementSelectedMarkExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementSelectedMarkExtractor selectedMark(@NotNull String elementPath) {
//        return new MobileListBlockElementSelectedMarkExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementTextValueExtractor textValue(@NotNull MobileGetTextAvailable elementFrame) {
//        return new MobileListBlockElementTextValueExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementTextValueExtractor textValue(@NotNull String elementPath) {
//        return new MobileListBlockElementTextValueExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementLabelValueExtractor labelValue(@NotNull MobileGetLabelAvailable elementFrame) {
//        return new MobileListBlockElementLabelValueExtractor(elementFrame);
//    }
//
//    public static MobileListBlockElementLabelValueExtractor labelValue(@NotNull String elementPath) {
//        return new MobileListBlockElementLabelValueExtractor(elementPath);
//    }
//
//    public static MobileListBlockElementPropertyValueExtractor propertyValue(@NotNull MobileChildElement elementFrame, @NotNull String propertyName) {
//        return new MobileListBlockElementPropertyValueExtractor(elementFrame, propertyName);
//    }
//
//    public static MobileListBlockElementPropertyValueExtractor propertyValue(@NotNull String elementPath, @NotNull String propertyName) {
//        return new MobileListBlockElementPropertyValueExtractor(elementPath, propertyName);
//    }
//
//    public static MobileListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull MobileComponentAvailable elementFrame,
//                                                                                            @NotNull String componentName) {
//        return new MobileListBlockElementComponentDisplayedMarkExtractor(elementFrame, componentName);
//    }
//
//    public static MobileListBlockElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String elementPath,
//                                                                                            @NotNull String componentName) {
//        return new MobileListBlockElementComponentDisplayedMarkExtractor(elementPath, componentName);
//    }
//
//    public static MobileListBlockElementComponentPresentMarkExtractor componentPresentMark(@NotNull MobileComponentAvailable elementFrame,
//                                                                                        @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentMarkExtractor(elementFrame, componentName);
//    }
//
//    public static MobileListBlockElementComponentPresentMarkExtractor componentPresentMark(@NotNull String elementPath,
//                                                                                        @NotNull String componentName) {
//        return new MobileListBlockElementComponentPresentMarkExtractor(elementPath, componentName);
//    }

    // MobileTextList

    public static MobileTextListBlockIndexExtractor textBlockIndex() {
        return new MobileTextListBlockIndexExtractor();
    }

//    public static MobileTextListBlockExtractor textBlock() {
//        return new MobileTextListBlockExtractor();
//    }
//
//    public static MobileTextListBlockElementExtractor textBlockElement() {
//        return new MobileTextListBlockElementExtractor();
//    }

    public static MobileTextListBlockTextValueExtractor textBlockValue() {
        return new MobileTextListBlockTextValueExtractor();
    }

    // MobileTable

    public static MobileTableRowIndexExtractor rowIndex() {
        return new MobileTableRowIndexExtractor();
    }

    public static MobileTableRowExtractor row() {
        return new MobileTableRowExtractor();
    }

    public static MobileTableCellExtractor<MobileBlock> cell(@NotNull String columnName) {
        return new MobileTableCellExtractor<>(columnName, MobileBlock.class);
    }

    public static <T extends MobileBlock> MobileTableCellExtractor<T> cell(@NotNull String columnName,
                                                                     @NotNull Class<T> cellClass) {
        return new MobileTableCellExtractor<>(columnName, cellClass);
    }
//
//    public static <T extends MobileChildElement> MobileTableCellElementExtractor<T> element(@NotNull String columnName,
//                                                                                      @NotNull T elementFrame) {
//        return new MobileTableCellElementExtractor<>(columnName, elementFrame);
//    }
//
//    public static <T extends MobileChildElement> MobileTableCellElementExtractor<T> element(@NotNull String columnName,
//                                                                                      @NotNull String elementPath,
//                                                                                      @NotNull Class<T> returnType) {
//        return new MobileTableCellElementExtractor<>(columnName, elementPath, returnType);
//    }
//
//    public static MobileTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
//                                                                          @NotNull MobileIsDisplayedAvailable elementFrame) {
//        return new MobileTableCellElementDisplayedMarkExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
//                                                                          @NotNull String elementPath) {
//        return new MobileTableCellElementDisplayedMarkExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
//                                                                      @NotNull MobileIsPresentAvailable elementFrame) {
//        return new MobileTableCellElementPresentMarkExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
//                                                                      @NotNull String elementPath) {
//        return new MobileTableCellElementPresentMarkExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
//                                                                      @NotNull MobileIsEnabledAvailable elementFrame) {
//        return new MobileTableCellElementEnabledMarkExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
//                                                                      @NotNull String elementPath) {
//        return new MobileTableCellElementEnabledMarkExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
//                                                                        @NotNull MobileIsSelectedAvailable elementFrame) {
//        return new MobileTableCellElementSelectedMarkExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
//                                                                        @NotNull String elementPath) {
//        return new MobileTableCellElementSelectedMarkExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementTextValueExtractor textValue(@NotNull String columnName,
//                                                                  @NotNull MobileGetTextAvailable elementFrame) {
//        return new MobileTableCellElementTextValueExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementTextValueExtractor textValue(@NotNull String columnName,
//                                                                  @NotNull String elementPath) {
//        return new MobileTableCellElementTextValueExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
//                                                                    @NotNull MobileGetLabelAvailable elementFrame) {
//        return new MobileTableCellElementLabelValueExtractor(columnName, elementFrame);
//    }
//
//    public static MobileTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
//                                                                    @NotNull String elementPath) {
//        return new MobileTableCellElementLabelValueExtractor(columnName, elementPath);
//    }
//
//    public static MobileTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
//                                                                          @NotNull MobileChildElement elementFrame,
//                                                                          @NotNull String propertyName) {
//        return new MobileTableCellElementPropertyValueExtractor(columnName, elementFrame, propertyName);
//    }
//
//    public static MobileTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
//                                                                          @NotNull String elementPath,
//                                                                          @NotNull String propertyName) {
//        return new MobileTableCellElementPropertyValueExtractor(columnName, elementPath, propertyName);
//    }
//
//    public static MobileTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
//                                                                                            @NotNull MobileChildElement elementMock,
//                                                                                            @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedMarkExtractor(columnName, elementMock, componentName);
//    }
//
//    public static MobileTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
//                                                                                            @NotNull String elementName,
//                                                                                            @NotNull String componentName) {
//        return new MobileTableCellElementComponentDisplayedMarkExtractor(columnName, elementName, componentName);
//    }
//
//    public static MobileTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
//                                                                                        @NotNull MobileChildElement elementMock,
//                                                                                        @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentMarkExtractor(columnName, elementMock, componentName);
//    }
//
//    public static MobileTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
//                                                                                        @NotNull String elementName,
//                                                                                        @NotNull String componentName) {
//        return new MobileTableCellElementComponentPresentMarkExtractor(columnName, elementName, componentName);
//    }

    // MobileTextTable

    public static MobileTextTableRowIndexExtractor textRowIndex() {
        return new MobileTextTableRowIndexExtractor();
    }

//    public static MobileTextTableRowExtractor textRow() {
//        return new MobileTextTableRowExtractor();
//    }
//
//    public static MobileTextTableCellElementExtractor textRowElement(@NotNull String columnName) {
//        return new MobileTextTableCellElementExtractor(columnName);
//    }

    public static MobileTextTableCellTextValueExtractor textCellValue(@NotNull String columnName) {
        return new MobileTextTableCellTextValueExtractor(columnName);
    }

}
