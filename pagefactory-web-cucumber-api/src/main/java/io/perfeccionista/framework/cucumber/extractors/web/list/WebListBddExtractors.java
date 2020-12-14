package io.perfeccionista.framework.cucumber.extractors.web.list;

import io.cucumber.java.en.Given;

public class WebListBddExtractors {

    @Given("text values from block")
    @Given("текстовые значения из блока")
    public WebListBlockValueExtractorHolder textValues() {
        return new WebListBlockElementTextExtractorHolder();
    }

    @Given("label values from block")
    @Given("значения лейбла")
    public WebListBlockValueExtractorHolder labelValues() {
        return new WebListBlockElementLabelExtractorHolder();
    }

    @Given("isPresent marks from block")
    @Given("признаки наличия")
    public WebListBlockValueExtractorHolder presentMarks() {
        return new WebListBlockElementPresentMarkToStringExtractorHolder();
    }

    @Given("checkIsDisplayed marks from block")
    @Given("признаки отображения")
    public WebListBlockValueExtractorHolder displayedMarks() {
        return new WebListBlockElementDisplayedMarkToStringExtractorHolder();
    }

    @Given("isEnabled marks from block")
    @Given("признаки доступности")
    public WebListBlockValueExtractorHolder enabledMarks() {
        return new WebListBlockElementEnabledMarkToStringExtractorHolder();
    }

    @Given("isSelected marks from block")
    @Given("признаки выделения")
    public WebListBlockValueExtractorHolder selectedMarks() {
        return new WebListBlockElementSelectedMarkToStringExtractorHolder();
    }

}
