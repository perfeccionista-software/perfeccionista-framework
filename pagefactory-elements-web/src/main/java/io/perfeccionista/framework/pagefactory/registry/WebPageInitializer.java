package io.perfeccionista.framework.pagefactory.registry;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebPage;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.registry.handlers.UseElementsConfigurationAnnotationHandler.handleUseElementsConfiguration;

public class WebPageInitializer {

    public WebPage initPage(Class<? extends WebPage> pageClass, ElementsConfiguration parentConfiguration) {
        Optional<ElementsConfiguration> overriddenConfiguration = handleUseElementsConfiguration(pageClass);
        ElementsConfiguration configuration = overriddenConfiguration.orElse(parentConfiguration);



        return null;
    }



}
