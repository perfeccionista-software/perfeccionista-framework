package io.perfeccionista.framework.pagefactory.extractor.table.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableCellElementComponentDisplayedMarkToStringExtractorCreator;
import io.perfeccionista.framework.pagefactory.extractor.table.creator.WebTableValueExtractorCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@CucumberResolverExpression("^component {component} display marks$")
@CucumberResolverExpression("^признаки отображения компонента {component}$")
public class WebTableCellElementComponentDisplayedMarkToStringExtractorCucumberResolver extends AbstractWebTableValueExtractorCucumberResolver {

    @Override
    public Optional<WebTableValueExtractorCreator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String component = matcher.group("component");
                if (isNull(component)) {
                    return Optional.empty();
                }
                return Optional.of(new WebTableCellElementComponentDisplayedMarkToStringExtractorCreator(component));
            }
        }
        return Optional.empty();
    }

}
