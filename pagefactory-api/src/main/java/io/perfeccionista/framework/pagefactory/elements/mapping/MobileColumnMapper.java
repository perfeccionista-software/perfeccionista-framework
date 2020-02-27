package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;

public interface MobileColumnMapper {

    Class<? extends WebBlock> getHeader();

    Class<? extends WebBlock> getBody();

    Class<? extends WebBlock> getFooter();

}
