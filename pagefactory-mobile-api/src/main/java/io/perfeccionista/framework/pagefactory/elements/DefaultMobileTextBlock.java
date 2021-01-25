package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;

public interface DefaultMobileTextBlock extends MobileBlock {

    @AndroidLocator(xpath = "self::node()")
    @IosLocator(xpath = "self::node()")
    MobileText textLink();

}

