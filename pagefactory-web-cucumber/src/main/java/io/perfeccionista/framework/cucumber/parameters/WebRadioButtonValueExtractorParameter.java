package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButtonValueExtractorParameter extends CucumberStepDefinitionParameter {

    @NotNull WebRadioButtonValueExtractor<String> createExtractor();

}
