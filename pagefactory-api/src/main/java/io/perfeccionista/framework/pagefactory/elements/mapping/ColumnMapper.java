package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.Block;

public interface ColumnMapper {

    Class<? extends Block> getHeader();

    Class<? extends Block> getBody();

    Class<? extends Block> getFooter();

}
