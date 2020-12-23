package io.perfeccionista.framework.pagefactory.extractor.list.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.list.creator.WebListBlockElementPropertyValueExtractorCreator;
import io.perfeccionista.framework.pagefactory.extractor.list.creator.WebListBlockElementValueExtractorCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@CucumberResolverExpression("^property values {propertyName}$")
@CucumberResolverExpression("^значения свойств {propertyName}$")
public class WebListBlockElementPropertyValueExtractorCucumberResolver extends AbstractWebListBlockValueExtractorCucumberResolver {

    @Override
    public Optional<WebListBlockElementValueExtractorCreator> tryResolve(@NotNull String expression, @Nullable Object... args) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String propertyName = matcher.group("propertyName");
                if (isNull(propertyName)) {
                    return Optional.empty();
                }
                return Optional.of(new WebListBlockElementPropertyValueExtractorCreator(propertyName));
            }
        }
        return Optional.empty();
    }

}

