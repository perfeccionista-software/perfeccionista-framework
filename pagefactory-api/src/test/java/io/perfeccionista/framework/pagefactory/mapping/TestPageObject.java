package io.perfeccionista.framework.pagefactory.mapping;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.Button;
import io.perfeccionista.framework.pagefactory.elements.Page;
import io.perfeccionista.framework.pagefactory.elements.Table;
import io.perfeccionista.framework.pagefactory.elements.TextBlock;
import io.perfeccionista.framework.pagefactory.elements.UnorderedList;
import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.MappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.mapping.UsePageFactoryElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.web.WebLink;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.BODY;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.HEADER;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;

@Name("Payments")
@UsePageFactoryElementsConfiguration(ElementsConfiguration.class)
public interface TestPageObject extends Page {

    // Return implementation
    @Name("Send")
    @Locator(id = "send--link")
    WebLink sendLink();

    // Return interface
    @Name("Ok")
    @Locator(id = "ok--button")
    Button okButton();

    @Name("Numbers list")
    @Locator(id = "number--List")
    @Locator(component = LI, xpath = ".//LI")
    @MappedBlock(SimpleStringBlock.class)
    UnorderedList myList();

    @Name("Payments table")
    @Locator(id = "payments-table")
    @Locator(component = HEADER, xpath = ".//TH")
    @Locator(component = BODY, xpath = ".//TR")
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
    Table myTable();

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
