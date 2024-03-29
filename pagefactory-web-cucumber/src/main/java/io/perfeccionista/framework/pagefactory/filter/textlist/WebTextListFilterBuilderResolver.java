package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.cucumber.datatable.DataTable;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.IncorrectDataTableFormat;
import io.perfeccionista.framework.exceptions.WebFilterConditionNotResolved;
import io.perfeccionista.framework.pagefactory.filter.AbstractWebFilterBuilderResolver;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.resolver.WebTextListBlockConditionCucumberResolver;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.Web.with;
import static io.perfeccionista.framework.Web.without;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.INCORRECT_WEB_TEXT_LIST_FILTER_BUILDER_DATA_TABLE_FORMAT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_FILTER_CONDITION_NOT_RESOLVED;

public class  WebTextListFilterBuilderResolver
//        extends AbstractWebFilterBuilderResolver
{

//    WebTextBlockFilterBuilder filterBuilder = null;
//    WebTextBlockCondition currentCondition = null;
//
//    protected WebTextListFilterBuilderResolver(Environment environment, DataTable dataTable) {
//        super(environment, dataTable);
//    }
//
//    public static WebTextListFilterBuilderResolver of(@NotNull Environment environment, @NotNull DataTable dataTable) {
//        return new WebTextListFilterBuilderResolver(environment, dataTable);
//    }
//
//    public @NotNull WebTextBlockFilterBuilder resolve() {
//        List<List<String>> dataTableAsLists = dataTable.asLists();
//
//        // если одна строка и одна колонка
//        if (dataTableAsLists.size() == 1 && dataTableAsLists.get(0).size() == 1) {
//            List<String> stringEntries = dataTableAsLists.get(0);
//            if (stringEntries.size() <= 2) {
//                return with(resolveStringEntry(stringEntries.get(0)));
//            } else {
//                throw IncorrectDataTableFormat.exception(INCORRECT_WEB_TEXT_LIST_FILTER_BUILDER_DATA_TABLE_FORMAT.getMessage());
//            }
//        }
//
//        // если несколько строк
//        for (List<String> dataTableRow : dataTableAsLists) {
//            if (dataTableRow.size() != 2) {
//                throw IncorrectDataTableFormat.exception(INCORRECT_WEB_TEXT_LIST_FILTER_BUILDER_DATA_TABLE_FORMAT.getMessage());
//            }
//            WebTextBlockCondition processedCondition = resolveStringEntry(dataTableRow.get(0));
//            String operator = dataTableRow.get(1);
//            // если группировка кондишенов
//            Optional<ConditionGrouping> optionalWebConditionGrouping = tryResolveWebConditionGrouping(operator);
//            if (optionalWebConditionGrouping.isPresent()) {
//                processConditionGrouping(processedCondition, optionalWebConditionGrouping.get());
//                continue;
//            }
//            // если группировка результатов
//            if (Objects.nonNull(currentCondition)) {
//                // если до этого мы набирали условие, его нужно теперь добавить в фильтр
//                processFilterResultGrouping();
//            }
//            currentFilterGrouping = resolveWebFilterResultGrouping(operator);
//            currentCondition = processedCondition;
//        }
//
//        processFilterResultGrouping();
//        return filterBuilder;
//    }
//
//    protected void processConditionGrouping(WebTextBlockCondition processed, ConditionGrouping grouping) {
//        if (Objects.isNull(currentCondition)) {
//            currentCondition = processed;
//            return;
//        }
//        switch (grouping) {
//            case AND: {
//                currentCondition.and(processed);
//                break;
//            }
//            case OR: {
//                currentCondition.or(processed);
//                break;
//            }
//            default: break;
//        }
//    }
//
//    protected void processFilterResultGrouping() {
//        if (Objects.isNull(currentFilterGrouping)) {
//            currentFilterGrouping = FilterResultGrouping.ADD;
//        }
//        switch (currentFilterGrouping) {
//            case ADD: {
//                if (Objects.isNull(filterBuilder)) {
//                    filterBuilder = with(currentCondition);
//                } else {
//                    filterBuilder.add(currentCondition);
//                }
//                break;
//            }
//            case SUBTRACT: {
//                if (Objects.isNull(filterBuilder)) {
//                    filterBuilder = without(currentCondition);
//                } else {
//                    filterBuilder.subtract(currentCondition);
//                }
//                break;
//            }
//            default: break;
//        }
//    }
//
//    protected WebTextBlockCondition resolveStringEntry(String stringEntry) {
//        CucumberService cucumberService = environment.getService(CucumberService.class);
//        return cucumberService.resolveFirst(WebTextListBlockConditionCucumberResolver.class, stringEntry)
//                .orElseThrow(() -> WebFilterConditionNotResolved
//                        .exception(WEB_FILTER_CONDITION_NOT_RESOLVED.getMessage(stringEntry, WebTextBlockFilterBuilder.class.getCanonicalName())));
//    }

}
