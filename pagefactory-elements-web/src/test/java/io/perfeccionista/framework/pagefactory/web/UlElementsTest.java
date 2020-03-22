package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementDisplayedExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementEnabledExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementSelectedExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementStateExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementTextExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockExtractor;
import io.perfeccionista.framework.pagefactory.filter.WebBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementPropertyValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementStateDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockElementValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.UlElementsPage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.UlElementsPage.CountryNameBlock;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementClosed;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementLabel;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementOpened;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementSize;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementText;
import static io.perfeccionista.framework.pagefactory.elements.mapping.WebMappingUtils.ulBlock;

public class UlElementsTest extends AbstractElementTest {

    @Test
    void unorderedListTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("UL Elements"));
        UlElementsPage ulElementsPage = pc.getPage(UlElementsPage.class);

        // Case: Вычислить размер списка используя различные условия сравнения
        assertElementSize(intValue.of("195"), ulElementsPage.unorderedList());
        assertElementSize(intValue.of("[больше]150"), ulElementsPage.unorderedList());
        assertElementSize(intValue.of("[больше или равно]193"), ulElementsPage.unorderedList());
        assertElementSize(intValue.of("[меньше]200"), ulElementsPage.unorderedList());
        assertElementSize(intValue.of("[меньше или равно]196"), ulElementsPage.unorderedList());
        assertElementSize(intValue.of("[не равно]192"), ulElementsPage.unorderedList());

        // Extractors позволяют описать декларативно путь к требуемому результату, поэтому с их помощью
        // можно получать любые типы значения из блоков за 1 запрос к странице
        // Любые экстракторы могут быть скомбинированы с любыми фильтрами.
        // Case: Получить экземпляры блоков и их индексы
        MultipleResult<CountryNameBlock> blocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class));
        Map<Integer, CountryNameBlock> indexedBlocks = blocks.get();

        // Case: Получить элементы чекбоксов из блоков
        MultipleResult<WebCheckbox> checkboxes = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementExtractor<>(ulBlock(CountryNameBlock.class).checkbox()));

        // Case: Получить полные названия стран
        MultipleResult<String> fullNames = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementTextExtractor(ulBlock(CountryNameBlock.class).fullName()));

        // Case: Получить номера стран в списке
        MultipleResult<String> countryNumbers = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementTextExtractor(ulBlock(CountryNameBlock.class).number()));

        // Case: Получить признак отображения элементов с короткими именем страны
        MultipleResult<Boolean> fullNameExists = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementDisplayedExtractor(ulBlock(CountryNameBlock.class).fullName()));

        // Case: Получить признак доступности чекбокса для выделения
        MultipleResult<Boolean> checkboxesEnabled = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementEnabledExtractor(ulBlock(CountryNameBlock.class).checkbox()));

        // Case: Получить признак выделения чекбокса
        MultipleResult<Boolean> checkboxesSelected = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementSelectedExtractor(ulBlock(CountryNameBlock.class).checkbox()));

        // Case: Получить значения свойства 'Wiki link' для короткого имени страны
        MultipleResult<String> wikiLinks = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementPropertyExtractor(ulBlock(CountryNameBlock.class).checkbox(), "Wiki link"));

        // Case: Получить признак состояния 'SNG' элемента 'English name'
        MultipleResult<Boolean> sngExists = ulElementsPage.unorderedList()
                .getValues(new WebBlockElementStateExtractor(ulBlock(CountryNameBlock.class).checkbox(), "SNG"));


        // Filters позволяют отфильтровать получаемый результат по необходимым условиям. Условий может быть неограниченное количество.
        // Одно условие требует один запрос. Особенностью использования фильтров является проверка элемента на изменения, то есть, если в процессе
        // выполнения запросов состояние элемента поменяется, то вся операция будет выполнена заново.
        // Если ваш запрос получает строго одно значение, то необходимо использовать метод getValue(extractor, filter);
        // Case: Получить все экземпляры блоков с индексами больше 150
        WebBlockFilter blockIndexFilter = new WebBlockFilter(new WebBlockIndexCondition(intValue.of("[больше]150")));
        MultipleResult<CountryNameBlock> filteredByIndexBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), blockIndexFilter);
        // Case: Получить экземпляр блока с индексом 122
        WebBlockFilter singleBlockIndexFilter = new WebBlockFilter(new WebBlockIndexCondition(intValue.of("122")));
        SingleResult<CountryNameBlock> blockByIndex = ulElementsPage.unorderedList()
                .getValue(new WebBlockExtractor<>(CountryNameBlock.class), singleBlockIndexFilter);

        // Case: Получить все экземпляры блоков, отфильтровав их по признаку наличия полного имени страны
        WebBlockFilter elementDisplayedFilter = new WebBlockFilter(new WebBlockElementDisplayedCondition(ulBlock(CountryNameBlock.class).fullName()));
        MultipleResult<CountryNameBlock> filteredByFullNameDisplayedBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementDisplayedFilter);

        // Case: Получить все экземпляры блоков, в которых короткое название страны содержит 'gia'
        WebBlockFilter elementStringValueFilter = new WebBlockFilter()
                .add(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).shortName(), value.of("[подстрока]gia")));
        MultipleResult<CountryNameBlock> filteredByShortNameValueBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementStringValueFilter);

        // Case: Получить все экземпляры блоков, в которых номер страны меньше или равен 77
        WebBlockFilter elementNumberValueFilter = new WebBlockFilter()
                .add(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).number(), intValue.of("[меньше или равно]77")));
        MultipleResult<CountryNameBlock> filteredByNumberValueBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementNumberValueFilter);

        // Case: Получить все экземпляры блоков, в которых чекбокс доступен для выделения
        WebBlockFilter elementEnabledFilter = new WebBlockFilter()
                .add(new WebBlockElementEnabledCondition(ulBlock(CountryNameBlock.class).checkbox()));
        MultipleResult<CountryNameBlock> filteredByElementEnabledBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementEnabledFilter);

        // Case: Получить все экземпляры блоков, в которых чекбокс выделен
        WebBlockFilter elementSelectedFilter = new WebBlockFilter()
                .add(new WebBlockElementSelectedCondition(ulBlock(CountryNameBlock.class).checkbox()));
        MultipleResult<CountryNameBlock> filteredByElementSelectedBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementSelectedFilter);

        // Case: Получить экземпляр блока, в котором ссылка 'short name' ведет на адрес 'https://ru.wikipedia.org/wiki/Австралия'
        WebBlockFilter elementPropertyValueFilter = new WebBlockFilter(new WebBlockElementPropertyValueCondition(
                ulBlock(CountryNameBlock.class).shortName(),
                "Wiki link",
                value.of("https://ru.wikipedia.org/wiki/Австралия")));
        SingleResult<CountryNameBlock> filteredByElementPropertyValueBlock = ulElementsPage.unorderedList()
                .getValue(new WebBlockExtractor<>(CountryNameBlock.class), elementPropertyValueFilter);

        // Case: Получить все экземпляры блоков, в которых в полном имени страны присутствует флажок СНГ
        WebBlockFilter elementStateDisplayedFilter = new WebBlockFilter()
                .add(new WebBlockElementStateDisplayedCondition(ulBlock(CountryNameBlock.class).checkbox(), "SNG"));
        MultipleResult<CountryNameBlock> filteredByElementStateBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), elementStateDisplayedFilter);

        // В любом фильтре может использоваться несколько условий разного типа,
        // которые либо добавляют к найденному ранее набору дополнительные записи, либо исключают их из найденного ранее набора.
        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebBlockFilter multipleFilter = new WebBlockFilter()
                .add(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).number(), intValue.of("[больше]77")))
                .substract(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).shortName(), value.of("[подстрока]na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocks = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), multipleFilter);

        // В любых условиях, использующих ссылку на элемент вместо указания на поле можно использовать имя элемента,
        // заданное аннотацией @Name
        // Case: Получить все экземпляр блоков, в которых номер страны больше 77 и короткое имя страны НЕ содержит 'na'
        WebBlockFilter multipleFilterByName = new WebBlockFilter()
                .add(new WebBlockElementValueCondition("Number", intValue.of("[больше]77")))
                .substract(new WebBlockElementValueCondition("Short name", value.of("[подстрока]na")));
        MultipleResult<CountryNameBlock> filteredByMultipleConditionsBlocksByName = ulElementsPage.unorderedList()
                .getValues(new WebBlockExtractor<>(CountryNameBlock.class), multipleFilter);

        // Case: Проскроллить список к блоку с индексом 138
        ulElementsPage.unorderedList().scrollToElement(new WebBlockFilter(new WebBlockIndexCondition(intValue.of("138"))));

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
        UlElementsPage ulElementsPage = pc.getPage(UlElementsPage.class);

        // Case: Проверяем лейбл у выпадающего списка
        //  Проверяем что выпадающий список закрыт
        //  Открываем выпадающий список
        //  Проверяем что выпадающий список открыт
        //  Прокручиваем список к элементу с индексом 136
        //  Закрываем выпадающий список
        //  Проверяем что выпадающий список закрыт
        //  Проверяем текущее выбранное значение в выпадающем списке
        assertElementLabel(value.of("DropDownList"), ulElementsPage.dropDownList());
        assertElementClosed(ulElementsPage.dropDownList());
        ulElementsPage.dropDownList().open();
        assertElementOpened(ulElementsPage.dropDownList());
        ulElementsPage.dropDownList().scrollToElement(new WebBlockFilter(new WebBlockIndexCondition(intValue.of("138"))));
        ulElementsPage.dropDownList().close();
        assertElementClosed(ulElementsPage.dropDownList());
        assertElementText(value.of("Abkhazia"), ulElementsPage.dropDownList());
        // Case: Выбрать в выпадающем списке элемент со значением 'Haiti' и проверить выбранное значение
        ulElementsPage.dropDownList().open();
        WebBlockFilter stringValueFilter = new WebBlockFilter(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).shortName(), value.of("Haiti")));
        ulElementsPage.dropDownList().select(stringValueFilter);
        ulElementsPage.dropDownList().close();
        assertElementText(value.of("Haiti"), ulElementsPage.dropDownList());
        // Case: Выбрать в выпадающем списке элемент с индексом '10' и проверить выбранное значение
        ulElementsPage.dropDownList().open();
        WebBlockFilter intValueFilter = new WebBlockFilter(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).number(), intValue.of("10")));
        ulElementsPage.dropDownList().select(intValueFilter);
        ulElementsPage.dropDownList().close();
        assertElementText(value.of("Andorra"), ulElementsPage.dropDownList());

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
        UlElementsPage ulElementsPage = pc.getPage(UlElementsPage.class);

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
        assertElementLabel(value.of("Autocomplete"), ulElementsPage.autocomplete());
        assertElementText(value.of(""), ulElementsPage.autocomplete());
        assertElementClosed(ulElementsPage.autocomplete());
        ulElementsPage.autocomplete().sendKeys("I");
        assertElementOpened(ulElementsPage.autocomplete());
        assertElementSize(intValue.of("8"), ulElementsPage.autocomplete());
        WebBlockFilter stringValueFilter = new WebBlockFilter(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).shortName(), value.of("Italy")));
        ulElementsPage.autocomplete().select(stringValueFilter);
        assertElementClosed(ulElementsPage.autocomplete());
        assertElementText(value.of("Italy"), ulElementsPage.autocomplete());
        ulElementsPage.autocomplete().clear();
        assertElementText(value.of(""), ulElementsPage.autocomplete());
        ulElementsPage.autocomplete().sendKeys("G");
        assertElementOpened(ulElementsPage.autocomplete());
        WebBlockFilter intValueFilter = new WebBlockFilter(new WebBlockElementValueCondition(ulBlock(CountryNameBlock.class).number(), intValue.of("1")));
        ulElementsPage.autocomplete().select(intValueFilter);
        assertElementClosed(ulElementsPage.autocomplete());
        assertElementText(value.of("Gabon"), ulElementsPage.autocomplete());

        env.getService(WebDriverService.class).closeAll();
    }

}
