package io.perfeccionista.framework.pagefactory.mapping;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.impl.WebLinkImpl;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;

@Name("Payments")
@UseElementsConfiguration(ElementsConfiguration.class)
public interface TestPageObject extends WebPage {

    // Return implementation
    @Name("Send")
    @WebLocator(id = "send--link")
    WebLinkImpl sendLink();

    // Return interface
    @Name("Ok")
    @WebLocator(id = "ok--button")
    WebButton okButton();

    @Name("Numbers list")
    @WebLocator(id = "number--List")
    @WebLocator(component = LI, xpath = ".//LI")
    @UseWebMappedBlock(SimpleStringBlock.class)
    WebUnorderedList myList();

    @Name("Payments table")
    @WebLocator(id = "payments-table")
    @WebLocator(component = THEAD_ROW, xpath = ".//TH")
    @WebLocator(component = TBODY_ROW, xpath = ".//TR")
    @UseWebMappedTableColumns({
            @UseWebMappedTableColumn(name = "Checkbox", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Payment number", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Payment date", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Payment sum", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Payer", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Recipient", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "Bank", mapper = SimpleWebColumn.class),
            @UseWebMappedTableColumn(name = "State", mapper = SimpleWebColumn.class)
    })
    WebTable myTable();

    // При создании экземпляра, логика метода должна браться из интерфейса
    @Override
    default OperationResult<Boolean> isPageOpen() {
        return sendLink().isDisplayed();
    }




    class SimpleWebColumn implements WebColumnMapper {

        @Override
        @WebLocator(xpath = ".//*[@test-id='simpleHeaderId']")
        public Class<? extends WebMappedBlock> getHeader() {
            return SimpleStringBlock.class;
        }

        @Override
        @WebLocator(xpath = ".//*[@test-id='simpleBodyId']")
        public Class<? extends WebMappedBlock> getBody() {
            return SimpleStringBlock.class;
        }

        // Not used without return type and locator
        @Override
        public Class<? extends WebMappedBlock> getFooter() {
            return null;
        }

    }

    interface SimpleStringBlock extends WebMappedBlock {

        @Name("Text")
        @WebLocator(xpath = ".//span")
        WebTextBlock text();

    }

}
