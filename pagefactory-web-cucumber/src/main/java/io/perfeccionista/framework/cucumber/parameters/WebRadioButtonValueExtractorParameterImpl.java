package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.WebExtractorNotResolved;
import io.perfeccionista.framework.pagefactory.extractor.radio.resolver.WebRadioButtonValueExtractorCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_EXTRACTOR_NOT_RESOLVED;

public class WebRadioButtonValueExtractorParameterImpl implements WebRadioButtonValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebRadioButtonValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

//    @Override
//    public @NotNull WebRadioButtonValueExtractor<String> createExtractor() {
//        return environment.getService(CucumberService.class)
//                .resolveFirst(WebRadioButtonValueExtractorCucumberResolver.class, rawInput)
//                .orElseThrow(() -> WebExtractorNotResolved.exception(WEB_EXTRACTOR_NOT_RESOLVED.getMessage(rawInput)));
//    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
