package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.Block;

public @interface MappedBlock {

    Class<? extends Block> value();

}
