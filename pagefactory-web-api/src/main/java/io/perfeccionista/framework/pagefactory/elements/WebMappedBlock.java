package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ParentInfoAvailable;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.factory.WebMockFactory;

public interface WebMappedBlock extends WebParentElement, ParentInfoAvailable {

    static <T extends WebMappedBlock> T from(Class<T> blockClass) {
        return WebMockFactory.createWebMappedBlockMock(blockClass);
    }

}