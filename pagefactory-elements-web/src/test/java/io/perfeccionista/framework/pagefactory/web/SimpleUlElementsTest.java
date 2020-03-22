package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.filter.WebStringBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.stringblock.WebStringBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.stringblock.WebStringBlockValueCondition;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.SimpleUlElementsPage;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementClosed;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementLabel;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementOpened;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementSize;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementText;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertNumber;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertText;

public class SimpleUlElementsTest extends AbstractElementTest {

    /**
     * Проверки выполняемые со списком, состоящим из текстовых значений
     */
    @Test
    void simpleUnorderedListTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Simple UL Elements"));
        SimpleUlElementsPage ulElementsPage = pc.getPage(SimpleUlElementsPage.class);

        // Case: Вычислить размер списка используя различные условия сравнения:
        //  Размер списка равен 195
        //  Размер списка больше 150
        //  Размер списка больше или равен 193
        //  Размер списка меньше 200
        //  Размер списка меньше или равен 196
        //  Размер списка не равен 192
        assertElementSize(intValue.of("195"), ulElementsPage.simpleUnorderedList());
        assertElementSize(intValue.of("[больше]150"), ulElementsPage.simpleUnorderedList());
        assertElementSize(intValue.of("[больше или равно]193"), ulElementsPage.simpleUnorderedList());
        assertElementSize(intValue.of("[меньше]200"), ulElementsPage.simpleUnorderedList());
        assertElementSize(intValue.of("[меньше или равно]196"), ulElementsPage.simpleUnorderedList());
        assertElementSize(intValue.of("[не равно]192"), ulElementsPage.simpleUnorderedList());

        // Case: Выбрать из списка значение 'Australia' и проверить что индекс этого значения равен 2
        // Также можно выбирать по значению цифровой строки через NumberValue<?>, например значения большие 140.77.
        WebStringBlockFilter singleValueFilter = new WebStringBlockFilter(new WebStringBlockValueCondition(value.of("Australia")));
        SingleResult<String> listItemByValue = ulElementsPage.simpleUnorderedList().getValue(singleValueFilter);
        assertNumber(intValue.of("2"), listItemByValue.getIndex());

        // Case: Выбрать из списка значение с индексом 4 и проверить что оно равно 'Azerbaijan'
        WebStringBlockFilter singleIndexFilter = new WebStringBlockFilter(new WebStringBlockIndexCondition(intValue.of("4")));
        SingleResult<String> listItemByIndex = ulElementsPage.simpleUnorderedList().getValue(singleIndexFilter);
        assertText(value.of("Azerbaijan"), listItemByIndex.get());

        // Case: Выбрать из списка все значения, которые НЕ содержат символы 'gia' и проверить их количество
        WebStringBlockFilter multipleValueFilterWithSubtraction = new WebStringBlockFilter()
                .substract(new WebStringBlockValueCondition(value.of("[подстрока]gia")));
        MultipleResult<String> listSubtractedItems = ulElementsPage.simpleUnorderedList().getValues(multipleValueFilterWithSubtraction);
        assertNumber(intValue.of("127"), listSubtractedItems.get().size());

        // Case: Выбрать из списка все значения, которые содержат 'ia' и не содержат 'Rus' или равны 'Maldives' и проверить их количество
        WebStringBlockFilter multipleValueFilter = new WebStringBlockFilter(new WebStringBlockValueCondition(value.of("[подстрока]ia")))
                .substract(new WebStringBlockValueCondition(value.of("[подстрока]Rus")))
                .add(new WebStringBlockValueCondition(value.of("Maldives")));
        MultipleResult<String> listItemsByValue = ulElementsPage.simpleUnorderedList().getValues(multipleValueFilter);
        assertNumber(intValue.of("39"), listItemsByValue.get().size());

        // Case: Выбрать из списка все элементы, индекс которых больше 100 и меньше 150 и не равен 125 и проверить их количество
        WebStringBlockFilter multipleIndexFilter = new WebStringBlockFilter(new WebStringBlockIndexCondition(intValue.of("[больше]100")))
                .substract(new WebStringBlockIndexCondition(intValue.of("[больше]150")).or(new WebStringBlockIndexCondition(intValue.of("125"))));
        MultipleResult<String> listItemsByIndex = ulElementsPage.simpleUnorderedList().getValues(multipleIndexFilter);
        assertNumber(intValue.of("49"), listItemsByIndex.get().size());

        // Case: Выбрать из списка все элементы, которые присутствуют в списке (в элементе присутствует текст)
        WebStringBlockFilter multipleDisplayedFilter = new WebStringBlockFilter()
                .substract(new WebStringBlockValueCondition(value.of("")));
        MultipleResult<String> listItemsDisplayed = ulElementsPage.simpleUnorderedList().getValues(multipleDisplayedFilter);
        assertNumber(intValue.of("193"), listItemsDisplayed.get().size());

        // Case: Проверить содержит ли список значение 'Russia'
        WebStringBlockFilter multipleValueFilterByString = new WebStringBlockFilter(value.of("Russia"));
        MultipleResult<String> listValueByString = ulElementsPage.simpleUnorderedList().getValues(multipleValueFilterByString);
        assertNumber(intValue.of("1"), listValueByString.get().size());

        // Case: Проверить содержит ли список значение с индексом 56
        WebStringBlockFilter multipleValueFilterByIndex = new WebStringBlockFilter(intValue.of("56"));
        MultipleResult<String> listValueByIndex = ulElementsPage.simpleUnorderedList().getValues(multipleValueFilterByIndex);
        assertNumber(intValue.of("1"), listValueByIndex.get().size());

        // Case: Выполнить скролл к элементу списка с индексом 136
        ulElementsPage.simpleUnorderedList().scrollToElement(new WebStringBlockFilter(intValue.of("136")));

        env.getService(WebDriverService.class).closeAll();
    }

    /**
     * Проверки выполняемые с выпадающим списком, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link io.perfeccionista.framework.pagefactory.elements.WebSimpleUnorderedList}
     */
    @Test
    void simpleDropDownListTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Simple UL Elements"));
        SimpleUlElementsPage ulElementsPage = pc.getPage(SimpleUlElementsPage.class);

        // Case: Проверяем лейбл у выпадающего списка
        //  Проверяем что выпадающий список закрыт
        //  Открываем выпадающий список
        //  Проверяем что выпадающий список открыт
        //  Прокручиваем список к элементу с индексом 136
        //  Закрываем выпадающий список
        //  Проверяем что выпадающий список закрыт
        //  Проверяем текущее выбранное значение в выпадающем списке
        assertElementLabel(value.of("DropDownList"), ulElementsPage.simpleDropDownList());
        assertElementClosed(ulElementsPage.simpleDropDownList());
        ulElementsPage.simpleDropDownList().open();
        assertElementOpened(ulElementsPage.simpleDropDownList());
        ulElementsPage.simpleDropDownList().scrollToElement(new WebStringBlockFilter(intValue.of("136")));
        ulElementsPage.simpleDropDownList().close();
        assertElementClosed(ulElementsPage.simpleDropDownList());
        assertElementText(value.of("Abkhazia"), ulElementsPage.simpleDropDownList());
        // Case: Выбрать в выпадающем списке элемент со значением 'Haiti' и проверить выбранное значение
        ulElementsPage.simpleDropDownList().open();
        ulElementsPage.simpleDropDownList().select(new WebStringBlockFilter(value.of("Haiti")));
        ulElementsPage.simpleDropDownList().close();
        assertElementText(value.of("Haiti"), ulElementsPage.simpleDropDownList());
        // Case: Выбрать в выпадающем списке элемент с индексом '10' и проверить выбранное значение
        ulElementsPage.simpleDropDownList().open();
        ulElementsPage.simpleDropDownList().select(new WebStringBlockFilter(intValue.of("10")));
        ulElementsPage.simpleDropDownList().close();
        assertElementText(value.of("Andorra"), ulElementsPage.simpleDropDownList());

        env.getService(WebDriverService.class).closeAll();
    }

    /**
     * Проверки выполняемые с выпадающим списком типа autocomplete, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link io.perfeccionista.framework.pagefactory.elements.WebSimpleUnorderedList}
     */
    @Test
    void simpleAutocompleteListTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Simple UL Elements"));
        SimpleUlElementsPage ulElementsPage = pc.getPage(SimpleUlElementsPage.class);

        // Case: Проверяем лейбл у поля ввода с подсказкой значений
        //  Проверяем что поле не заполнено
        //  Проверяем что выпадающий список вариантов закрыт
        //  Вводим в поле ввода символ 'I'
        //  Проверяем что выпадающий список вариантов открыт
        //  Проверяем размер списка вариантов
        //  Выбираем из списка вариант 'Italy'
        //  Проверяем что выпадающий список вариантов закрыт
        //  Проверяем что в поле вабрано значение 'Italy'
        //  Очищаем поле ввода с подсказкой значений
        //  Проверяем что поле не заполнено
        //  Вводим в поле ввода символ 'G'
        //  Проверяем что выпадающий список вариантов открыт
        //  Выбираем из списка вариант с индексом 1
        //  Проверяем что выпадающий список вариантов закрыт
        //  Проверяем что в поле вабрано значение 'Gabon'
        assertElementLabel(value.of("Autocomplete"), ulElementsPage.simpleAutocomplete());
        assertElementText(value.of(""), ulElementsPage.simpleAutocomplete());
        assertElementClosed(ulElementsPage.simpleAutocomplete());
        ulElementsPage.simpleAutocomplete().sendKeys("I");
        assertElementOpened(ulElementsPage.simpleAutocomplete());
        assertElementSize(intValue.of("8"), ulElementsPage.simpleAutocomplete());
        ulElementsPage.simpleAutocomplete().select(new WebStringBlockFilter(value.of("Italy")));
        assertElementClosed(ulElementsPage.simpleAutocomplete());
        assertElementText(value.of("Italy"), ulElementsPage.simpleAutocomplete());
        ulElementsPage.simpleAutocomplete().clear();
        assertElementText(value.of(""), ulElementsPage.simpleAutocomplete());
        ulElementsPage.simpleAutocomplete().sendKeys("G");
        assertElementOpened(ulElementsPage.simpleAutocomplete());
        // для фильтра с одним условием можно его создавать напрямую из value
        ulElementsPage.simpleAutocomplete().select(new WebStringBlockFilter(intValue.of("1")));
        assertElementClosed(ulElementsPage.simpleAutocomplete());
        assertElementText(value.of("Gabon"), ulElementsPage.simpleAutocomplete());

        env.getService(WebDriverService.class).closeAll();
    }

}
