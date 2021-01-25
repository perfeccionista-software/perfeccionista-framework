package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.matcher.element.MobilePageMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.name.MobilePageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;

public interface MobilePage extends MobileParentElement {

    @NotNull MobilePageIdentifier getPageIdentifier();

    MobilePage setMobileDeviceDispatcher(MobileDeviceDispatcher mobileDeviceDispatcher);

    MobilePage setEnvironment(Environment environment);

    MobilePage should(@NotNull MobilePageMatcher matcher);

//    @Override
//    MobilePage scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement);
//    @Override
//    MobilePage scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement);

    /**
     * Если необходимо, то переопределяем и делаем необходимую проверку на
     * факт открытия страницы.
     * Если страница не открылась, то нужно кидать processed exception чтобы проверка повторялась
     */
    default void validatePageOpen() {}

}
