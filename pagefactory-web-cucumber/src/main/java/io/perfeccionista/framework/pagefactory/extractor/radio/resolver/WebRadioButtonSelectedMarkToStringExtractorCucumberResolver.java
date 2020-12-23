package io.perfeccionista.framework.pagefactory.extractor.radio.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CucumberResolverExpression("^select marks$")
@CucumberResolverExpression("^признаки выделения")
public class WebRadioButtonSelectedMarkToStringExtractorCucumberResolver extends AbstractWebRadioButtonValueExtractorCucumberResolver {

    @Override
    public Optional<WebRadioButtonValueExtractor<String>> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(new WebRadioButtonSelectedMarkToStringExtractor());
            }
        }
        return Optional.empty();
    }

}
