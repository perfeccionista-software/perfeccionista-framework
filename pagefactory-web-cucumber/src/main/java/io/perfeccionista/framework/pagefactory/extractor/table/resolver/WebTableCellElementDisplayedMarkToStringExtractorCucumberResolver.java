package io.perfeccionista.framework.pagefactory.extractor.table.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableCellElementDisplayedMarkToStringExtractorCreator;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableValueExtractorCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CucumberResolverExpression("^display marks$")
@CucumberResolverExpression("^признаки отображения$")
public class WebTableCellElementDisplayedMarkToStringExtractorCucumberResolver extends AbstractWebTableValueExtractorCucumberResolver {

    @Override
    public Optional<WebTableValueExtractorCreator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(new WebTableCellElementDisplayedMarkToStringExtractorCreator());
            }
        }
        return Optional.empty();
    }

}
