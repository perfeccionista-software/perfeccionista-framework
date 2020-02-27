package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;

public @interface WebMappedBlock {

    Class<? extends WebBlock> value();

}
