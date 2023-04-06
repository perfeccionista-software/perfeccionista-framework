package io.perfeccionista.framework.pagefactory.extractor.list.resolver;

import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.pagefactory.extractor.list.creator.WebListBlockElementDisplayedMarkToStringExtractorCreator;
import io.perfeccionista.framework.pagefactory.extractor.list.creator.WebListBlockElementValueExtractorCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CucumberResolverExpression("^display marks$")
@CucumberResolverExpression("^признаки отображения$")
public class WebListBlockElementDisplayedMarkToStringExtractorCucumberResolver
//        extends AbstractWebListBlockValueExtractorCucumberResolver
{

//    @Override
//    public Optional<WebListBlockElementValueExtractorCreator> tryResolve(@NotNull String expression, @Nullable Object... args) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(expression);
//            if (matcher.find()) {
//                return Optional.of(new WebListBlockElementDisplayedMarkToStringExtractorCreator());
//            }
//        }
//        return Optional.empty();
//    }

}
