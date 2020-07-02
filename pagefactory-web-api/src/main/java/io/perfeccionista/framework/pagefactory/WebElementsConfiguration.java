package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebBlock;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebPage;

import java.util.Map;

public interface WebElementsConfiguration {

    Class<? extends AbstractWebPage> getWebPageImplementation();

    Class<? extends AbstractWebBlock> getWebBlockImplementation();

    Class<? extends AbstractWebMappedBlock> getWebMappedBlockImplementation();

    Map<Class<? extends WebChildElement>, Class<? extends AbstractWebChildElement>> getElementImplementations();

}
