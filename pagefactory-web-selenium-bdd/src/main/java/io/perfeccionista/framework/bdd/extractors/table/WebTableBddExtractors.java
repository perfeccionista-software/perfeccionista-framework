package io.perfeccionista.framework.bdd.extractors.table;

import io.cucumber.java.en.Given;

public class WebTableBddExtractors {

    @Given("text values")
    @Given("текстовые значения")
    public WebTableCellElementValueExtractorHolder textValues() {
        return new WebTableCellElementTextExtractorHolder();
    }

    @Given("label values")
    @Given("значения лейбла")
    public WebTableCellElementValueExtractorHolder labelValues() {
        return new WebTableCellElementLabelExtractorHolder();
    }

    @Given("isPresent marks")
    @Given("признаки наличия")
    public WebTableCellElementValueExtractorHolder presentMarks() {
        return new WebTableCellElementPresentMarkToStringExtractorHolder();
    }

    @Given("checkIsDisplayed marks")
    @Given("признаки отображения")
    public WebTableCellElementValueExtractorHolder displayedMarks() {
        return new WebTableCellElementDisplayedMarkToStringExtractorHolder();
    }

    @Given("isEnabled marks")
    @Given("признаки доступности")
    public WebTableCellElementValueExtractorHolder enabledMarks() {
        return new WebTableCellElementEnabledMarkToStringExtractorHolder();
    }

    @Given("isSelected marks")
    @Given("признаки выделения")
    public WebTableCellElementValueExtractorHolder selectedMarks() {
        return new WebTableCellElementSelectedMarkToStringExtractorHolder();
    }

}
