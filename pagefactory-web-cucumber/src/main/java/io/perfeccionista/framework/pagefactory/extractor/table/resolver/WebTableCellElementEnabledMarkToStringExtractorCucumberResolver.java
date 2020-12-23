package io.perfeccionista.framework.pagefactory.extractor.table.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableCellElementEnabledMarkToStringExtractorCreator;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableValueExtractorCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CucumberResolverExpression("^enabled marks$")
@CucumberResolverExpression("^признаки доступности")
public class WebTableCellElementEnabledMarkToStringExtractorCucumberResolver extends AbstractWebTableValueExtractorCucumberResolver {

    @Override
    public Optional<WebTableValueExtractorCreator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                return Optional.of(new WebTableCellElementEnabledMarkToStringExtractorCreator());
            }
        }
        return Optional.empty();
    }

}
