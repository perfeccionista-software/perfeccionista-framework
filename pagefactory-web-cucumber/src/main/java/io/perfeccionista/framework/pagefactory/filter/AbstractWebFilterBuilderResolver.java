package io.perfeccionista.framework.pagefactory.filter;

import io.cucumber.datatable.DataTable;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.WebFilterOperatorNotResolved;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_FILTER_OPERATOR_NOT_RESOLVED;

public abstract class AbstractWebFilterBuilderResolver {

    protected final Environment environment;
    protected final DataTable dataTable;

    protected WebFilterResultGrouping currentFilterGrouping = null;

    protected AbstractWebFilterBuilderResolver(Environment environment, DataTable dataTable) {
        this.environment = environment;
        this.dataTable = dataTable;
    }

    protected Optional<WebConditionGrouping> tryResolveWebConditionGrouping(@Nullable String operator) {
        if (Objects.isNull(operator)) {
            return Optional.empty();
        }
        CucumberService cucumberService = environment.getService(CucumberService.class);
        return cucumberService.resolveFirst(WebConditionGroupingCucumberResolver.class, operator);
    }

    protected WebFilterResultGrouping resolveWebFilterResultGrouping(@Nullable String operator) {
        if (Objects.isNull(operator)) {
            return WebFilterResultGrouping.ADD;
        }
        CucumberService cucumberService = environment.getService(CucumberService.class);
        return cucumberService.resolveFirst(WebFilterResultGroupingCucumberResolver.class, operator)
                .orElseThrow(() -> WebFilterOperatorNotResolved
                        .exception(WEB_FILTER_OPERATOR_NOT_RESOLVED.getMessage(operator)));
    }

}
