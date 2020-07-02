package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.extractor.WebExtractors;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CheckboxWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FullNameWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.LinkWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SimpleStringWebMappedBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.perfeccionista.framework.pagefactory.elements.WebMappedBlock.from;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.cell;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class TableTest extends AbstractUiTest {

    /**
     *
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void tableTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Table"));

        TablePage tablePage = pc.getPage(TablePage.class);

        // Case: Вычислить размер таблицы используя различные условия сравнения
        tablePage.table()
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveSize(value.intGreaterThan(150))
                .shouldHaveSize(value.intGreaterThanOrEqual(193))
                .shouldHaveSize(value.intLessThan(200))
                .shouldHaveSize(value.intLessThanOrEqual(196))
                .shouldHaveSize(value.intNotEquals(192));

        // Case: Получить экземпляры ячеек с именем страны и их индексы и проверить их
        tablePage.table()
                .extractAllRows(cell(TablePage.SHORT_NAME, LinkWebMappedBlock.class))
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveIndex(value.intEquals(153));
//                .shouldHaveIndex(value.intContainsAll(asList(1, 2, 3, 4, 5)))
//                .shouldHaveIndex(value.intContainsAny(asList(100, 200, 300, 400, 500)));

        // Extractors
        // TODO: PresentMark
        // Case: Получить элементы чекбоксов из ячеек таблицы и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.element(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить полные названия стран и проверить из количество
        tablePage.table()
                .extractAllRows(WebExtractors.element(TablePage.FULL_NAME, from(FullNameWebMappedBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить номера стран в списке и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.textValue(TablePage.NUMBER, from(SimpleStringWebMappedBlock.class).text()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак отображения элементов с полным именем страны и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.displayedMark(TablePage.FULL_NAME, from(FullNameWebMappedBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак доступности чекбокса для выделения и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.enabledMark(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак выделения чекбокса и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.selectedMark(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить значения свойства 'Wiki link' для короткого имени страны и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.propertyValue(TablePage.SHORT_NAME, from(LinkWebMappedBlock.class).shortName(), "Wiki link"))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак состояния 'SNG' элемента 'English name' и проверить их количество
        tablePage.table()
                .extractAllRows(WebExtractors.componentDisplayedMark(TablePage.FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "SNG"))
                .shouldHaveSize(value.intEquals(193));
        // TODO: ExtractTableRows

        // Sorting
        // TODO: Sorting
        // Case: Проверить что таблица отсортирована по значениям элемента "Short name" колонки "Short Name"
        tablePage.table()
                .extractAllRows(WebExtractors.textValue(TablePage.SHORT_NAME, from(LinkWebMappedBlock.class).shortName()))
                .shouldBeSorted(String::compareToIgnoreCase);

        // Filter
        // TODO: Present filter
        // Case: Получить все экземпляры блоков с коротким именем с индексами больше 150 и проверить их количество
        MultipleResult<LinkWebMappedBlock> filteredByIndexBlocks = tablePage.table()
                .filter(with(rowIndex(value.intGreaterThan(150))))
                .shouldHaveSize(value.intEquals(42))
                .extractAllRows(cell(TablePage.SHORT_NAME, LinkWebMappedBlock.class));

        // Case: Получить экземпляр блока с индексом 122
        SingleResult<LinkWebMappedBlock> filteredByIndexBlock = tablePage.table()
                .filter(with(rowIndex(value.intEquals(122))))
                .shouldHaveSize(value.intEquals(1))
                .extractOneRow(cell(TablePage.SHORT_NAME, LinkWebMappedBlock.class));

        // Case: Получить все экземпляры блоков с полным именем, отфильтровав их по признаку наличия полного имени страны
        MultipleResult<FullNameWebMappedBlock> filteredByElementDisplayedBlocks = tablePage.table()
                .filter(with(WebConditions.displayed(TablePage.FULL_NAME, from(FullNameWebMappedBlock.class).fullName())))
                .shouldHaveSize(value.intEquals(193))
                .extractAllRows(cell(TablePage.FULL_NAME, FullNameWebMappedBlock.class));

        // Case: Проверить количество строк, в которых короткое название страны содержит 'gia'
        tablePage.table()
                .filter(with(WebConditions.containsText(TablePage.SHORT_NAME, from(LinkWebMappedBlock.class).shortName(), value.stringContains("gia"))))
                .shouldHaveSize(value.intEquals(123));

        // Case: Проверить количество всех экземпляров блоков, в которых номер страны меньше или равен 77
        tablePage.table()
                .filter(with(WebConditions.containsText(TablePage.NUMBER, from(SimpleStringWebMappedBlock.class).text(), value.intLessThanOrEqual(77))))
                .shouldHaveSize(value.intEquals(77));

        // Case: Получить все экземпляры чекбоксов, из строк в которых чекбокс доступен для выделения
        MultipleResult<WebCheckbox> filteredByElementEnabledCheckboxes = tablePage.table()
                .filter(with(WebConditions.enabled(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .extractAllRows(WebExtractors.element(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()));

        // Case: Проверить количество выделенных чекбоксов
        tablePage.table()
                .filter(with(WebConditions.selected(TablePage.CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(5));

        // Case: Получить экземпляр ячейки, в котором ссылка 'short name' ведет на адрес 'https://ru.wikipedia.org/wiki/Австралия'
        WebTableFilter elementPropertyValueFilter = with(WebConditions.containsProperty(TablePage.SHORT_NAME, from(LinkWebMappedBlock.class).shortName(),
                "Wiki link", value.stringEquals("https://ru.wikipedia.org/wiki/Австралия")));
        SingleResult<LinkWebMappedBlock> filteredByElementPropertyValueBlock = tablePage.table()
                .filter(elementPropertyValueFilter)
                .shouldHaveSize(value.intEquals(1))
                .extractOneRow(cell(TablePage.SHORT_NAME, LinkWebMappedBlock.class));

        // Case: Получить все экземпляры блоков, в которых в полном имени страны присутствует флажок СНГ
        MultipleResult<FullNameWebMappedBlock> filteredByElementComponentDisplayedBlocks = tablePage.table()
                .filter(with(WebConditions.componentDisplayed(TablePage.FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "SNG")))
                .extractAllRows(cell(TablePage.FULL_NAME, FullNameWebMappedBlock.class));

        // Case: Получить все экземпляр ячеек, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebTableFilter multipleFilter = with(WebConditions.containsText(TablePage.NUMBER, from(SimpleStringWebMappedBlock.class).text(), value.intGreaterThan(77)))
                .subtract(WebConditions.containsText(TablePage.SHORT_NAME, from(LinkWebMappedBlock.class).shortName(), value.stringContains("na")));
        MultipleResult<SimpleStringWebMappedBlock> filteredByMultipleConditionsBlocks = tablePage.table()
                .filter(multipleFilter)
                .extractAllRows(cell(TablePage.SHORT_NAME, SimpleStringWebMappedBlock.class));

        // В любых условиях, использующих ссылку на элемент вместо указания на поле можно использовать имя элемента, заданное аннотацией @Name
        // Case: Получить все экземпляры ячеек, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebTableFilter multipleFilterByName = with(WebConditions.containsText(TablePage.NUMBER, "Number", value.intGreaterThan(77)))
                .subtract(WebConditions.containsText(TablePage.SHORT_NAME, "Short name", value.stringContains("na")));
        MultipleResult<SimpleStringWebMappedBlock> filteredByMultipleConditionsBlocksByName = tablePage.table()
                .filter(multipleFilterByName)
                .extractAllRows(cell(TablePage.NUMBER, SimpleStringWebMappedBlock.class));

        // Case: Проскроллить список к блоку с индексом 138
        tablePage.table()
                .scrollToElement(with(rowIndex(value.intEquals(138))));

    }

}
