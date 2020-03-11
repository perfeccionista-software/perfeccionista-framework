package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;

public interface WebColumnMapper {

    Class<? extends WebMappedBlock> getHeader();

    Class<? extends WebMappedBlock> getBody();

    Class<? extends WebMappedBlock> getFooter();

}
