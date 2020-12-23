package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.WebExtractorNotResolved;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.resolver.WebTableValueExtractorCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_EXTRACTOR_NOT_RESOLVED;

public class WebTableValueExtractorParameterImpl implements WebTableValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebTableValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull WebTableValueExtractor<String> createExtractorFor(@NotNull String webTableColumnName, @NotNull String webCellElementName) {
        return environment.getService(CucumberService.class)
                .resolveFirst(WebTableValueExtractorCucumberResolver.class, rawInput)
                .orElseThrow(() -> WebExtractorNotResolved.exception(WEB_EXTRACTOR_NOT_RESOLVED.getMessage(rawInput)))
                .createForElement(webTableColumnName, webCellElementName);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
