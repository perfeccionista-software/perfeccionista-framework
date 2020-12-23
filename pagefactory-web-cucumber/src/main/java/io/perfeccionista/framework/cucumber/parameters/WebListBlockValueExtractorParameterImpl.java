package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.WebExtractorNotResolved;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.resolver.WebListBlockValueExtractorCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_EXTRACTOR_NOT_RESOLVED;

public class WebListBlockValueExtractorParameterImpl implements WebListBlockValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebListBlockValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull WebListBlockValueExtractor<String> createExtractorFor(@NotNull String blockElementName) {
        return environment.getService(CucumberService.class)
                .resolveFirst(WebListBlockValueExtractorCucumberResolver.class, rawInput)
                .orElseThrow(() -> WebExtractorNotResolved.exception(WEB_EXTRACTOR_NOT_RESOLVED.getMessage(rawInput)))
                .createForElement(blockElementName);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
