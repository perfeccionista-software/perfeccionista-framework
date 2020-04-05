package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebStringList;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ListElementsPage.CountryNameBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.pagefactory.elements.mapping.WebMappingUtils.blockMock;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.block;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.displayedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.enabledMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.propertyValue;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.selectedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.stateDisplayedMark;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textValue;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.displayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.property;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.stateDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.text;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

import static java.util.Arrays.asList;

public class ListElementsTest extends AbstractElementTest {

    /**
     * Проверки выполняемые со списком, состоящим из блоков с несколькими элементами
     *
     * {@link WebListFilter}
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
        ulElementsPage.unorderedList()
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveSize(value.intGreaterThan(150))
                .shouldHaveSize(value.intGreaterThanOrEqual(193))
                .shouldHaveSize(value.intLessThan(200))
                .shouldHaveSize(value.intLessThanOrEqual(196))
                .shouldHaveSize(value.intNotEquals(192));

        // Case: Получить экземпляры блоков и их индексы и проверить их
        ulElementsPage.unorderedList()
                .extractAll(block(CountryNameBlock.class))
                .shouldHaveSize(value.intEquals(195))
                .shouldHaveIndex(value.intEquals(153));
//                .shouldHaveIndex(value.intContainsAll(asList(1, 2, 3, 4, 5)))
//                .shouldHaveIndex(value.intContainsAny(asList(100, 200, 300, 400, 500)));

        // Extractors
        // Case: Получить элементы чекбоксов из блоков и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(element(blockMock(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить полные названия стран и проверить из количество
        ulElementsPage.unorderedList()
                .extractAll(textValue(blockMock(CountryNameBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить номера стран в списке и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(textValue(blockMock(CountryNameBlock.class).number()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак отображения элементов с полным именем страны и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(displayedMark(blockMock(CountryNameBlock.class).fullName()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак доступности чекбокса для выделения и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(enabledMark(blockMock(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак выделения чекбокса и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(selectedMark(blockMock(CountryNameBlock.class).checkbox()))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить значения свойства 'Wiki link' для короткого имени страны и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(propertyValue(blockMock(CountryNameBlock.class).shortName(), "Wiki link"))
                .shouldHaveSize(value.intEquals(193));

        // Case: Получить признак состояния 'SNG' элемента 'English name' и проверить их количество
        ulElementsPage.unorderedList()
                .extractAll(stateDisplayedMark(blockMock(CountryNameBlock.class).fullName(), "SNG"))
                .shouldHaveSize(value.intEquals(193));

        // Filter
        // Case: Получить все экземпляры блоков с индексами больше 150 и проверить их количество
        MultipleResult<CountryNameBlock> filteredByIndexBlocks = ulElementsPage.unorderedList()
                .filter(with(blockIndex(value.intGreaterThan(150))))
                .shouldHaveSize(value.intEquals(42))
                .extractAll(block(CountryNameBlock.class));

        // Case: Получить экземпляр блока с индексом 122
        SingleResult<CountryNameBlock> filteredByIndexBlock = ulElementsPage.unorderedList()
                .filter(with(blockIndex(value.intEquals(122))))
                .shouldHaveSize(value.intEquals(1))
                .extractOne(block(CountryNameBlock.class));

        // Case: Получить все экземпляры блоков, отфильтровав их по признаку наличия полного имени страны
        MultipleResult<CountryNameBlock> filteredByElementDisplayedBlocks = ulElementsPage.unorderedList()
                .filter(with(displayed(blockMock(CountryNameBlock.class).fullName())))
                .shouldHaveSize(value.intEquals(193))
                .extractAll(block(CountryNameBlock.class));

        // Case: Проверить количество блоков, в которых короткое название страны содержит 'gia'
        ulElementsPage.unorderedList()
                .filter(with(text(blockMock(CountryNameBlock.class).shortName(), value.stringContains("gia"))))
                .shouldHaveSize(value.intEquals(123));

        // Case: Проверить количество всех экземпляров блоков, в которых номер страны меньше или равен 77
        ulElementsPage.unorderedList()
                .filter(with(text(blockMock(CountryNameBlock.class).number(), value.intLessThanOrEqual(77))))
                .shouldHaveSize(value.intEquals(77));

        // Case: Получить все экземпляры чекбоксов, из блоков в которых чекбокс доступен для выделения
        MultipleResult<WebCheckbox> filteredByElementEnabledCheckboxes = ulElementsPage.unorderedList()
                .filter(with(enabled(blockMock(CountryNameBlock.class).checkbox())))
                .extractAll(element(blockMock(CountryNameBlock.class).checkbox()));

        // Case: Проверить количество выделенных чекбоксов
        ulElementsPage.unorderedList()
                .filter(with(selected(blockMock(CountryNameBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(5));

        // Case: Получить экземпляр блока, в котором ссылка 'short name' ведет на адрес 'https://ru.wikipedia.org/wiki/Австралия'
        WebListFilter elementPropertyValueFilter = with(property(blockMock(CountryNameBlock.class).shortName(), "Wiki link",
                value.stringEquals("https://ru.wikipedia.org/wiki/Австралия")));
        SingleResult<CountryNameBlock> filteredByElementPropertyValueBlock = ulElementsPage.unorderedList()
                .filter(elementPropertyValueFilter)
                .shouldHaveSize(value.intEquals(1))
                .extractOne(block(CountryNameBlock.class));

        // Case: Получить все экземпляры блоков, в которых в полном имени страны присутствует флажок СНГ
        MultipleResult<CountryNameBlock> filteredByElementStateBlocks = ulElementsPage.unorderedList()
                .filter(with(stateDisplayed(blockMock(CountryNameBlock.class).fullName(), "SNG")))
                .extractAll(block(CountryNameBlock.class));

        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebListFilter multipleFilter = with(text(blockMock(CountryNameBlock.class).number(), value.intGreaterThan(77)))
                .subtract(text(blockMock(CountryNameBlock.class).shortName(), value.stringContains("na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocks = ulElementsPage.unorderedList()
                .filter(multipleFilter)
                .extractAll(block(CountryNameBlock.class));

        // В любых условиях, использующих ссылку на элемент вместо указания на поле можно использовать имя элемента, заданное аннотацией @Name
        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebListFilter multipleFilterByName = with(text("Number", value.intGreaterThan(77)))
                .subtract(text("Short name", value.stringContains("na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocksByName = ulElementsPage.unorderedList()
                .filter(multipleFilter)
                .extractAll(block(CountryNameBlock.class));

        // Case: Проскроллить список к блоку с индексом 138
        ulElementsPage.unorderedList()
                .scrollToElement(with(blockIndex(value.intEquals(138))));
    }

    /**
     * Проверки выполняемые с выпадающим списком, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebStringList}
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
                .select(with(text(blockMock(CountryNameBlock.class).shortName(), value.stringEquals("Haiti"))))
                .close()
                .shouldHaveText(value.stringEquals("Haiti"));
        // Case: Выбрать в выпадающем списке элемент с индексом '10' и проверить выбранное значение
        ulElementsPage.dropDownList()
                .open()
                .select(with(text(blockMock(CountryNameBlock.class).number(), value.intEquals(10))))
                .close()
                .shouldHaveText(value.stringEquals("Andorra"));
    }

    /**
     * Проверки выполняемые с выпадающим списком типа autocomplete, состоящим из текстовых значений.
     * Все фильтры значений аналогичны {@link WebStringList}
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
                .select(with(text(blockMock(CountryNameBlock.class).shortName(), value.stringEquals("Italy"))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Italy"))
                .clear()
                .shouldHaveText(value.stringEmpty())
                .sendKeys("G")
                .shouldBeOpen()
                .select(with(text(blockMock(CountryNameBlock.class).number(), value.intEquals(1))))
                .shouldBeClosed()
                .shouldHaveText(value.stringEquals("Gabon"));
    }

}
