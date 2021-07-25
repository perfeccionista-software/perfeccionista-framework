package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebListBlockValueExtractorParameter extends CucumberStepDefinitionParameter {

    @NotNull WebBlockValueExtractor<String, WebBlock> createExtractorFor(@NotNull String blockElementName);

}
