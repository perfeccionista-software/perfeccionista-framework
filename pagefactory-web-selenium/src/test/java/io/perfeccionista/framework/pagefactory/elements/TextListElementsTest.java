package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.SimpleListElementsPage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsTextBlock;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.textBlockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;

public class TextListElementsTest extends AbstractUiTest {

    /**
     * Проверки выполняемые со списком, состоящим из текстовых значений
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void simpleUnorderedListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple UL Elements"));

        // Case: Вычислить размер списка используя различные условия сравнения:
        SimpleListElementsPage ulElementsPage = pc.getPage(SimpleListElementsPage.class);
        ulElementsPage.simpleUnorderedList()
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveSize(value.intGreaterThan(150))
                .shouldHaveSize(value.intGreaterThanOrEqual(193))
                .shouldHaveSize(value.intLessThan(200))
                .shouldHaveSize(value.intLessThanOrEqual(196))
                .shouldHaveSize(value.intNotEquals(192));

        // Extractors
        // Case: Получить все значения списка
        MultipleResult<String> allValues = ulElementsPage.simpleUnorderedList()
                .extractAll();

        // Sorting
        // Case: Проверить что значения отсортированы по алфавиту вне зависимости от регистра
        ulElementsPage.simpleUnorderedList()
                .extractAll()
                .shouldBeSorted(String::compareToIgnoreCase);

        // Filters
        // Case: Выбрать из списка значение 'Australia' и проверить что индекс этого значения равен 2
        ulElementsPage.simpleUnorderedList()
                .filter(with(WebConditions.containsTextBlock(value.stringEquals("Australia"))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne()
                .shouldHaveIndex(value.intEquals(2));

        // Case: Выбрать из списка значение с индексом 4 и проверить что оно равно 'Azerbaijan'
        ulElementsPage.simpleUnorderedList()
                .filter(with(textBlockIndex(value.intEquals(4))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne()
                .shouldHaveResult(value.stringEquals("Azerbaijan"));

        // Case: Выбрать из списка все значения, которые НЕ содержат символы 'gia' и проверить их количество
        ulElementsPage.simpleUnorderedList()
                .filter(without(WebConditions.containsTextBlock(value.stringContains("gia"))))
                .shouldHaveSize(value.intEquals(127));

        // Case: Выбрать из списка все значения, которые содержат 'ia' и не содержат 'Rus' или равны 'Maldives' и проверить их количество
        WebTextListFilterBuilder multipleValueFilter = with(WebConditions.containsTextBlock(value.stringContains("ia")))
                .subtract(WebConditions.containsTextBlock(value.stringContains("Rus")))
                .add(WebConditions.containsTextBlock(value.stringEquals("Maldives")));
        ulElementsPage.simpleUnorderedList()
                .filter(multipleValueFilter)
                .shouldHaveSize(value.intEquals(39));

        // Case: Выбрать из списка все элементы, индекс которых больше 100 и меньше 150 и не равен 125 и проверить их количество
        WebTextListFilterBuilder multipleIndexFilter = with(textBlockIndex(value.intGreaterThan(100)))
                .subtract(textBlockIndex(value.intGreaterThan(150)).or(textBlockIndex(value.intEquals(125))));
        ulElementsPage.simpleUnorderedList()
                .filter(multipleIndexFilter)
                .shouldHaveSize(value.intEquals(49));

        // Case: Выбрать из списка все элементы, которые присутствуют в списке (в элементе присутствует текст)
        ulElementsPage.simpleUnorderedList()
                .filter(without(WebConditions.containsTextBlock(value.stringEmpty())))
                .shouldHaveSize(value.intEquals(193));

        // Case: Проверить содержит ли список значение 'Russia'
        ulElementsPage.simpleUnorderedList()
                .filter(with(WebConditions.containsTextBlock(value.stringEquals("Russia"))))
                .shouldHaveSize(value.intEquals(1));

        // Case: Проверить содержит ли список значение с индексом 56
        ulElementsPage.simpleUnorderedList()
                .filter(with(textBlockIndex(value.intEquals(56))))
                .shouldHaveSize(value.intEquals(1));

        // Case: Выполнить скролл к элементу списка с индексом 136
        ulElementsPage.simpleUnorderedList()
                .scrollToElement(with(textBlockIndex(value.intEquals(136))));
    }

    /**
     * Проверки выполняемые с выпадающим списком, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebTextList}
     * Case: Проверяем лейбл у выпадающего списка
     *  Проверяем что выпадающий список закрыт
     *  Открываем выпадающий список
     *  Проверяем что выпадающий список открыт
     *  Прокручиваем список к элементу с индексом 136
     *  Закрываем выпадающий список
     *  Проверяем что выпадающий список закрыт
     *  Проверяем текущее выбранное значение в выпадающем списке
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void simpleDropDownListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple UL Elements"));

        SimpleListElementsPage ulElementsPage = pc.getPage(SimpleListElementsPage.class);
        ulElementsPage.simpleDropDownList()
                .shouldHaveLabel(value.stringEquals("DropDownList"))
                .shouldBeClosed()
                .open()
                .shouldBeOpen()
                .scrollToElement(with(textBlockIndex(value.intEquals(136))))
                .close()
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Abkhazia"));
        // Case: Выбрать в выпадающем списке элемент со значением 'Haiti' и проверить выбранное значение
        ulElementsPage.simpleDropDownList()
                .open()
                .clickToElement(with(WebConditions.containsTextBlock(value.stringEquals("Haiti"))))
                .close()
                .shouldHaveText(value.stringEquals("Haiti"));
        // Case: Выбрать в выпадающем списке элемент с индексом '10' и проверить выбранное значение
        ulElementsPage.simpleDropDownList()
                .open()
                .clickToElement(with(textBlockIndex(value.intEquals(10))))
                .close()
                .shouldHaveText(value.stringEquals("Andorra"));
    }

    /**
     * Проверки выполняемые с выпадающим списком типа autocomplete, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebTextList}
     * Case: Проверяем лейбл у поля ввода с подсказкой значений
     *  Проверяем что поле не заполнено
     *  Проверяем что выпадающий список вариантов закрыт
     *  Вводим в поле ввода символ 'I'
     *  Проверяем что выпадающий список вариантов открыт
     *  Проверяем размер списка вариантов
     *  Выбираем из списка вариант 'Italy'
     *  Проверяем что выпадающий список вариантов закрыт
     *  Проверяем что в поле вабрано значение 'Italy'
     *  Очищаем поле ввода с подсказкой значений
     *  Проверяем что поле не заполнено
     *  Вводим в поле ввода символ 'G'
     *  Проверяем что выпадающий список вариантов открыт
     *  Выбираем из списка вариант с индексом 1
     *  Проверяем что выпадающий список вариантов закрыт
     *  Проверяем что в поле вабрано значение 'Gabon'
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void simpleAutocompleteListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple UL Elements"));

        SimpleListElementsPage ulElementsPage = pc.getPage(SimpleListElementsPage.class);
        ulElementsPage.simpleAutocomplete()
                .shouldHaveLabel(value.stringEquals("Autocomplete"))
                .shouldHaveText(value.stringEmpty())
                .shouldBeClosed();
        ulElementsPage.simpleAutocomplete()
                .sendKeys("I")
                .shouldBeOpen()
                .shouldHaveSize(value.intEquals(8))
                .clickToElement(with(WebConditions.containsTextBlock(value.stringEquals("Italy"))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Italy"))
                .clear()
                .shouldHaveText(value.stringEmpty());
        ulElementsPage.simpleAutocomplete()
                .sendKeys("G")
                .shouldBeOpen()
                .clickToElement(with(textBlockIndex(value.intEquals(1))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Gabon"));
    }

}
