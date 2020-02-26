package io.perfeccionista.framework.pagefactory.mapping;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.Page;
import io.perfeccionista.framework.pagefactory.elements.TextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.mapping.UsePageFactoryElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.web.WebButton;
import io.perfeccionista.framework.pagefactory.elements.web.WebPage;
import io.perfeccionista.framework.pagefactory.elements.web.WebTable;
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
    @MappedBlock(SimpleStringBlock.class)
    WebUnorderedList myList();

    @Name("Payments table")
    @Locator(id = "payments-table")
    @Locator(component = THEAD_ROW, xpath = ".//TH")
    @Locator(component = TBODY_ROW, xpath = ".//TR")
    @MappedTableColumns({
            @MappedTableColumn(name = "Checkbox", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Payment number", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Payment date", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Payment sum", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Payer", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Recipient", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "Bank", mapper = SimpleColumn.class),
            @MappedTableColumn(name = "State", mapper = SimpleColumn.class)
    })
    WebTable myTable();

    // При создании экземпляра, логика метода должна браться из интерфейса
    @Override
    default OperationResult<Boolean> isPageOpen() {
        return sendLink().isDisplayed();
    }




    class SimpleColumn implements ColumnMapper {

        @Override
        @Locator(xpath = ".//*[@test-id='simpleHeaderId']")
        public Class<? extends Block> getHeader() {
            return SimpleStringBlock.class;
        }

        @Override
        @Locator(xpath = ".//*[@test-id='simpleBodyId']")
        public Class<? extends Block> getBody() {
            return SimpleStringBlock.class;
        }

        // Not used without return type and locator
        @Override
        public Class<? extends Block> getFooter() {
            return null;
        }

    }

    interface SimpleStringBlock extends Block {

        @Name("Text")
        @Locator(xpath = ".//span")
        TextBlock text();

    }

}
