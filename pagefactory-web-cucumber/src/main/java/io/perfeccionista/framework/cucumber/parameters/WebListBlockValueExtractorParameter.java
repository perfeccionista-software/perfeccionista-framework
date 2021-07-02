package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebListBlockValueExtractorParameter extends CucumberStepDefinitionParameter {

    @NotNull WebListBlockValueExtractor<String, WebBlock> createExtractorFor(@NotNull String blockElementName);

}
