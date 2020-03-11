package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.MobileMappedBlock;

public interface MobileColumnMapper {

    Class<? extends MobileMappedBlock> getHeader();

    Class<? extends MobileMappedBlock> getBody();

    Class<? extends MobileMappedBlock> getFooter();

}
