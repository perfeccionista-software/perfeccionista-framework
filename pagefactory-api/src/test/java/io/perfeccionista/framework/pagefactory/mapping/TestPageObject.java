package io.perfeccionista.framework.pagefactory.mapping;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.mapping.UsePageFactoryElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.web.WebButton;
import io.perfeccionista.framework.pagefactory.elements.web.WebPage;
import io.perfeccionista.framework.pagefactory.elements.web.WebTable;
import io.perfeccionista.framework.pagefactory.elements.web.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.web.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.web.impl.WebLinkImpl;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;

@Name("Payments")
@UsePageFactoryElementsConfiguration(ElementsConfiguration.class)
public interface TestPageObject extends WebPage {

    // Return implementation
    @Name("Send")
    @Locator(id = "send--link")
    WebLinkImpl sendLink();

    // Return interface
    @Name("Ok")
    @Locator(id = "ok--button")
    WebButton okButton();

    @Name("Numbers list")
    @Locator(id = "number--List")
    @Locator(component = LI, xpath = ".//LI")
    @WebMappedBlock(SimpleStringBlock.class)
    WebUnorderedList myList();

    @Name("Payments table")
    @Locator(id = "payments-table")
    @Locator(component = THEAD_ROW, xpath = ".//TH")
    @Locator(component = TBODY_ROW, xpath = ".//TR")
    @MappedTableColumns({
            @MappedTableColumn(name = "Checkbox", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Payment number", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Payment date", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Payment sum", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Payer", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Recipient", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "Bank", mapper = SimpleWebColumn.class),
            @MappedTableColumn(name = "State", mapper = SimpleWebColumn.class)
    })
    WebTable myTable();

    // При создании экземпляра, логика метода должна браться из интерфейса
    @Override
    default OperationResult<Boolean> isPageOpen() {
        return sendLink().isDisplayed();
    }




    class SimpleWebColumn implements WebColumnMapper {

        @Override
        @Locator(xpath = ".//*[@test-id='simpleHeaderId']")
        public Class<? extends WebBlock> getHeader() {
            return SimpleStringBlock.class;
        }

        @Override
        @Locator(xpath = ".//*[@test-id='simpleBodyId']")
        public Class<? extends WebBlock> getBody() {
            return SimpleStringBlock.class;
        }

        // Not used without return type and locator
        @Override
        public Class<? extends WebBlock> getFooter() {
            return null;
        }

    }

    interface SimpleStringBlock extends WebBlock {

        @Name("Text")
        @Locator(xpath = ".//span")
        WebTextBlock text();

    }

}
