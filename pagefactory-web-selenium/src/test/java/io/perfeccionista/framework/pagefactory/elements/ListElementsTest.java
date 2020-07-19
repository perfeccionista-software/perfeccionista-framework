package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage.CountryNameBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.pagefactory.elements.WebMappedBlock.from;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.block;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.displayedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.enabledMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.propertyValue;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.selectedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.componentDisplayedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textValue;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.displayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.componentDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class ListElementsTest extends AbstractUiTest {

    /**
     * Проверки выполняемые со списком, состоящим из блоков с несколькими элементами
     *
     * {@link WebListFilterBuilder}
     * позволяют отфильтровать получаемый результат по необходимым условиям. Условий может быть неограниченное количество.
     * Одно условие требует один запрос. Особенностью использования фильтров является проверка элемента на изменения, то есть, если в процессе
     * выполнения запросов состояние элемента поменяется, то вся операция будет выполнена заново.
     * Если ваш запрос получает строго одно значение, то необходимо использовать метод getValue(extractor, filter);
     * В любом фильтре может использоваться несколько условий разного типа,
     * которые либо добавляют к найденному ранее набору дополнительные записи, либо исключают их из найденного ранее набора.
     *
     * {@link WebListBlockValueExtractor}
     * позволяют описать декларативно путь к требуемому результату, поэтому с их помощью
     * можно получать любые типы значения из блоков за 1 запрос к странице
     * Любые экстракторы могут быть скомбинированы с любыми фильтрами.
     *
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void unorderedListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("UL Elements"));

        ListElementsPage ulElementsPage = pc.getPage(ListElementsPage.class);

        // Case: Вычислить размер списка используя различные условия сравнения
        ulElementsPage.webList()
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveSize(value.intGreaterThan(150))
                .shouldHaveSize(value.intGreaterThanOrEqual(193))
                .shouldHaveSize(value.intLessThan(200))
                .shouldHaveSize(value.intLessThanOrEqual(196))
                .shouldHaveSize(value.intNotEquals(192));

        // Case: Получить экземпляры блоков и их индексы и проверить их
        ulElementsPage.webList()
                .extractAll(block(CountryNameBlock.class))
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveIndex(value.intEquals(153));
//                .shouldHaveIndex(value.intContainsAll(asList(1, 2, 3, 4, 5)))
//                .shouldHaveIndex(value.intContainsAny(asList(100, 200, 300, 400, 500)));

        // Extractors
        // TODO: PresentMark
        // Case: Получить элементы чекбоксов из блоков и проверить их количество
        ulElementsPage.webList()
                .extractAll(element(from(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить полные названия стран и проверить из количество
        ulElementsPage.webList()
                .extractAll(textValue(from(CountryNameBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить номера стран в списке и проверить их количество
        ulElementsPage.webList()
                .extractAll(textValue(from(CountryNameBlock.class).number()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак отображения элементов с полным именем страны и проверить их количество
        ulElementsPage.webList()
                .extractAll(displayedMark(from(CountryNameBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак доступности чекбокса для выделения и проверить их количество
        ulElementsPage.webList()
                .extractAll(enabledMark(from(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак выделения чекбокса и проверить их количество
        ulElementsPage.webList()
                .extractAll(selectedMark(from(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить значения свойства 'Wiki link' для короткого имени страны и проверить их количество
        ulElementsPage.webList()
                .extractAll(propertyValue(from(CountryNameBlock.class).shortName(), "Wiki link"))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак состояния 'SNG' элемента 'English name' и проверить их количество
        ulElementsPage.webList()
                .extractAll(componentDisplayedMark(from(CountryNameBlock.class).fullName(), "SNG"))
                .shouldHaveSize(value.intEquals(193));

        // Sorting
        // TODO: Sorting для разных экстракторов и форматов
        // Case: Проверить что все блоки списка отсортированы по значению элемента 'Short name'
        ulElementsPage.webList()
                .extractAll(textValue(from(CountryNameBlock.class).shortName()))
                .shouldBeSorted(String::compareToIgnoreCase);

        // Filter
        // TODO: Present filter
        // Case: Получить все экземпляры блоков с индексами больше 150 и проверить их количество
        MultipleResult<CountryNameBlock> filteredByIndexBlocks = ulElementsPage.webList()
                .filter(with(blockIndex(value.intGreaterThan(150))))
                .shouldHaveSize(value.intEquals(42))
                .extractAll(block(CountryNameBlock.class));

        // Case: Получить экземпляр блока с индексом 122
        SingleResult<CountryNameBlock> filteredByIndexBlock = ulElementsPage.webList()
                .filter(with(blockIndex(value.intEquals(122))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne(block(CountryNameBlock.class));

        // Case: Получить все экземпляры блоков, отфильтровав их по признаку наличия полного имени страны
        MultipleResult<CountryNameBlock> filteredByElementDisplayedBlocks = ulElementsPage.webList()
                .filter(with(displayed(from(CountryNameBlock.class).fullName())))
                .shouldHaveSize(value.intEquals(193))
                .extractAll(block(CountryNameBlock.class));

        // Case: Проверить количество блоков, в которых короткое название страны содержит 'gia'
        ulElementsPage.webList()
                .filter(with(containsText(from(CountryNameBlock.class).shortName(), value.stringContains("gia"))))
                .shouldHaveSize(value.intEquals(123));

        // Case: Проверить количество всех экземпляров блоков, в которых номер страны меньше или равен 77
        ulElementsPage.webList()
                .filter(with(containsText(from(CountryNameBlock.class).number(), value.intLessThanOrEqual(77))))
                .shouldHaveSize(value.intEquals(77));

        // Case: Получить все экземпляры чекбоксов, из блоков в которых чекбокс доступен для выделения
        MultipleResult<WebCheckbox> filteredByElementEnabledCheckboxes = ulElementsPage.webList()
                .filter(with(enabled(from(CountryNameBlock.class).checkbox())))
                .extractAll(element(from(CountryNameBlock.class).checkbox()));

        // Case: Проверить количество выделенных чекбоксов
        ulElementsPage.webList()
                .filter(with(selected(from(CountryNameBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(5));

        // Case: Получить экземпляр блока, в котором ссылка 'short name' ведет на адрес 'https://ru.wikipedia.org/wiki/Австралия'
        WebListFilterBuilder elementPropertyValueFilter = with(containsProperty(from(CountryNameBlock.class).shortName(), "Wiki link",
                value.stringEquals("https://ru.wikipedia.org/wiki/Австралия")));
        SingleResult<CountryNameBlock> filteredByElementPropertyValueBlock = ulElementsPage.webList()
                .filter(elementPropertyValueFilter)
                .shouldHaveSize(value.intEquals(1))
                .extractOne(block(CountryNameBlock.class));

        // Case: Получить все экземпляры блоков, в которых в полном имени страны присутствует флажок СНГ
        MultipleResult<CountryNameBlock> filteredByElementComponentDisplayedBlocks = ulElementsPage.webList()
                .filter(with(componentDisplayed(from(CountryNameBlock.class).fullName(), "SNG")))
                .extractAll(block(CountryNameBlock.class));

        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebListFilterBuilder multipleFilter = with(containsText(from(CountryNameBlock.class).number(), value.intGreaterThan(77)))
                .subtract(containsText(from(CountryNameBlock.class).shortName(), value.stringContains("na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocks = ulElementsPage.webList()
                .filter(multipleFilter)
                .extractAll(block(CountryNameBlock.class));

        // В любых условиях, использующих ссылку на элемент вместо указания на поле можно использовать имя элемента, заданное аннотацией @Name
        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebListFilterBuilder multipleFilterByName = with(containsText("Number", value.intGreaterThan(77)))
                .subtract(containsText("Short name", value.stringContains("na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocksByName = ulElementsPage.webList()
                .filter(multipleFilter)
                .extractAll(block(CountryNameBlock.class));

        // Case: Проскроллить список к блоку с индексом 138
        ulElementsPage.webList()
                .scrollToElement(with(blockIndex(value.intEquals(138))));
    }

    /**
     * Проверки выполняемые с выпадающим списком, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebTextList}
     *
     * Case: Проверяем лейбл у выпадающего списка
     *  Проверяем что выпадающий список закрыт
     *  Открываем выпадающий список
     *  Проверяем что выпадающий список открыт
     *  Прокручиваем список к элементу с индексом 136
     *  Закрываем выпадающий список
     *  Проверяем что выпадающий список закрыт
     *  Проверяем текущее выбранное значение в выпадающем списке
     *
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void simpleDropDownListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple UL Elements"));

        ListElementsPage ulElementsPage = pc.getPage(ListElementsPage.class);
        ulElementsPage.dropDownList()
                .shouldHaveLabel(value.stringEquals("DropDownList"))
                .shouldBeClosed()
                .open()
                .shouldBeOpen()
                .scrollToElement(with(blockIndex(value.intEquals(138))))
                .close()
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Abkhazia"));
        // Case: Выбрать в выпадающем списке элемент со значением 'Haiti' и проверить выбранное значение
        ulElementsPage.dropDownList()
                .open()
                .clickToElement(with(containsText(from(CountryNameBlock.class).shortName(), value.stringEquals("Haiti"))))
                .close()
                .shouldHaveText(value.stringEquals("Haiti"));
        // Case: Выбрать в выпадающем списке элемент с индексом '10' и проверить выбранное значение
        ulElementsPage.dropDownList()
                .open()
                .clickToElement(with(containsText(from(CountryNameBlock.class).number(), value.intEquals(10))))
                .close()
                .shouldHaveText(value.stringEquals("Andorra"));
    }

    /**
     * Проверки выполняемые с выпадающим списком типа autocomplete, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebTextList}
     *
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
     *
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void simpleAutocompleteListTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Simple UL Elements"));

        ListElementsPage ulElementsPage = pc.getPage(ListElementsPage.class);
        ulElementsPage.autocomplete()
                .shouldHaveLabel(value.stringEquals("Autocomplete"))
                .shouldHaveText(value.stringEmpty())
                .shouldBeClosed()
                .sendKeys("I")
                .shouldBeOpen()
                .shouldHaveSize(value.intEquals(8))
                .clickToElement(with(containsText(from(CountryNameBlock.class).shortName(), value.stringEquals("Italy"))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Italy"))
                .clear()
                .shouldHaveText(value.stringEmpty())
                .sendKeys("G")
                .shouldBeOpen()
                .clickToElement(with(containsText(from(CountryNameBlock.class).number(), value.intEquals(1))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Gabon"));
    }

}
