package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Set;

public interface MobilePageServiceConfiguration extends ServiceConfiguration {

    Set<String> getPageObjectPackages();

    MobilePageFactoryPreferences getElementsPreferences();

}
