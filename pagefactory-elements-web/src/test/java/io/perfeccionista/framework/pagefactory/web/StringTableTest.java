package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringTableFilter;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.SimpleTablePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.filter.WebConditions.cellText;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.stringRowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static io.perfeccionista.framework.pagefactory.web.pageobjects.SimpleTablePage.FULL_NAME;
import static io.perfeccionista.framework.pagefactory.web.pageobjects.SimpleTablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.web.pageobjects.SimpleTablePage.SHORT_NAME;

public class StringTableTest extends AbstractElementTest {

    /**
     * Проверки выполняемые с простой таблицей, состоящей из колонок с текстовыми значениями
     *
     *  Case: Вычислить размер тела таблицы используя различные условия сравнения:
     *   Размер таблицы равен 195
     *   Размер таблицы больше 150
     *   Размер таблицы больше или равен 193
     *   Размер таблицы меньше 200
     *   Размер таблицы меньше или равен 196
     *   Размер таблицы не равен 192
     *
     */
    @Test
    void simpleTableTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple Table"));

        SimpleTablePage tablePage = pc.getPage(SimpleTablePage.class);
        tablePage.simpleTable()
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveSize(value.intGreaterThan(150))
                .shouldHaveSize(value.intGreaterThanOrEqual(193))
                .shouldHaveSize(value.intLessThan(200))
                .shouldHaveSize(value.intLessThanOrEqual(196))
                .shouldHaveSize(value.intNotEquals(192));

        // Case: Получить заголовок колонки 'Short name'
        SingleResult<String> shortNameHeader = tablePage.simpleTable()
                .extractHeader(SHORT_NAME);

        // Case: Получить заголовки всех колонок таблицы
        Map<String, SingleResult<String>> allColumnHeaders = tablePage.simpleTable()
                .extractHeader(Set.of(NUMBER, SHORT_NAME, FULL_NAME));

        // Case: Получить подвал колонки 'Number'
        SingleResult<String> numberFooter = tablePage.simpleTable()
                .extractFooter(NUMBER);

        // Case: Получить подвалы всех колонок таблицы
        Map<String, SingleResult<String>> allColumnFooters = tablePage.simpleTable()
                .extractFooter(Set.of(NUMBER, SHORT_NAME, FULL_NAME));

        // Case: Получить все значения колонки "Название страны"
        MultipleResult<String> shortNameValues = tablePage.simpleTable()
                .extractAll(SHORT_NAME)
                .shouldHaveSize(value.intEquals(195));

        // Case: Получить все значения всех колонок таблицы
        Map<String, MultipleResult<String>> allColumnValues = tablePage.simpleTable()
                .extractAll(Set.of(NUMBER, SHORT_NAME, FULL_NAME));

        // Sorting
        // Case: Проверить что значения отсортированы по алфавиту вне зависимости от регистра
        tablePage.simpleTable()
                .extractAll(SHORT_NAME)
                .shouldBeSorted(String::compareToIgnoreCase);

        // Case: Выбрать из колонки таблицы 'Short name' значение 'Australia' и проверить что индекс этого значения равен 2
        tablePage.simpleTable()
                .filter(with(cellText(SHORT_NAME, value.stringEquals("Australia"))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne(SHORT_NAME)
                .shouldHaveIndex(value.intEquals(2));

        // Case: Выбрать из таблицы строку с индексом 4 и проверить что значение колонки 'Short name' равно 'Azerbaijan'
        tablePage.simpleTable()
                .filter(with(stringRowIndex(value.intEquals(4))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne(SHORT_NAME)
                .shouldHaveResult(value.stringEquals("Azerbaijan"));

        // Case: Выбрать из таблицы все строки, в которых значение колонки 'Short name' НЕ содержат символы 'gia' и проверить их количество
        tablePage.simpleTable()
                .filter(without(cellText(SHORT_NAME, value.stringContains("gia"))))
                .shouldHaveSize(value.intEquals(127));

        // Case: Выбрать из таблицы все строки, в которых значение колонки 'Short name' содержит 'ia' и не содержат 'Rus' или равны 'Maldives' и проверить их количество
        WebStringTableFilter multipleValueFilter = with(cellText(SHORT_NAME, value.stringContains("ia")))
                .subtract(cellText(SHORT_NAME, value.stringContains("Rus")))
                .add(cellText(SHORT_NAME, value.stringEquals("Maldives")));
        tablePage.simpleTable()
                .filter(multipleValueFilter)
                .shouldHaveSize(value.intEquals(39));

        // Case: Выбрать из таблицы все строки, индекс которых больше 100 и меньше 150 и не равен 125 и проверить их количество
        WebStringTableFilter multipleIndexFilter = with(stringRowIndex(value.intGreaterThan(100)))
                .subtract(stringRowIndex(value.intGreaterThan(150)).or(stringRowIndex(value.intEquals(125))));
        tablePage.simpleTable()
                .filter(multipleIndexFilter)
                .shouldHaveSize(value.intEquals(49));

        // Case: Выбрать из таблицы все строки, в которых в колонке 'Short name' присутствуют значения (в элементе присутствует текст)
        tablePage.simpleTable()
                .filter(without(cellText(SHORT_NAME, value.stringEmpty())))
                .shouldHaveSize(value.intEquals(193));

        // Case: Проверить содержит ли таблица строку в которой в колонке 'Short name' значение равно 'Russia'
        tablePage.simpleTable()
                .filter(with(cellText(SHORT_NAME, value.stringEquals("Russia"))))
                .shouldHaveSize(value.intEquals(1));

        // Case: Проверить содержит ли таблица строку с индексом 56
        tablePage.simpleTable()
                .filter(with(stringRowIndex(value.intEquals(56))))
                .shouldHaveSize(value.intEquals(1));

        // Case: Выполнить скролл к строке таблицы с индексом 136
        tablePage.simpleTable()
                .scrollToElement(with(stringRowIndex(value.intEquals(136))));
    }

}