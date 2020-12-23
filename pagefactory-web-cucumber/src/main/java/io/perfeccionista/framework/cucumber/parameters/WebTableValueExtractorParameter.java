package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebTableValueExtractorParameter extends CucumberStepDefinitionParameter {

    @NotNull WebTableValueExtractor<String> createExtractorFor(@NotNull String webTableColumnName, @NotNull String webCellElementName);

}
