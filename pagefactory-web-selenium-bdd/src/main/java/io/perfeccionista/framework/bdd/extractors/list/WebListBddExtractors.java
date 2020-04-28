package io.perfeccionista.framework.bdd.extractors.list;

import io.cucumber.java.en.Given;

public class WebListBddExtractors {

    @Given("text values")
    @Given("текстовые значения")
    public WebListBlockValueExtractorHolder textValues() {
        return new WebListBlockElementTextExtractorHolder();
    }

    @Given("label values")
    @Given("значения лейбла")
    public WebListBlockValueExtractorHolder labelValues() {
        return new WebListBlockElementLabelExtractorHolder();
    }

    @Given("isPresent marks")
    @Given("признаки наличия")
    public WebListBlockValueExtractorHolder presentMarks() {
        return new WebListBlockElementPresentMarkToStringExtractorHolder();
    }

    @Given("isDisplayed marks")
    @Given("признаки отображения")
    public WebListBlockValueExtractorHolder displayedMarks() {
        return new WebListBlockElementDisplayedMarkToStringExtractorHolder();
    }

    @Given("isEnabled marks")
    @Given("признаки доступности")
    public WebListBlockValueExtractorHolder enabledMarks() {
        return new WebListBlockElementEnabledMarkToStringExtractorHolder();
    }

    @Given("isSelected marks")
    @Given("признаки выделения")
    public WebListBlockValueExtractorHolder selectedMarks() {
        return new WebListBlockElementSelectedMarkToStringExtractorHolder();
    }

}
