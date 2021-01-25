package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.pagefactory.MobilePageServiceConfiguration;
import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultAppiumAndroidPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;

import java.util.Set;

// TODO Move faker datasource & etc.
public class TestAppiumMobilePageServiceConfiguration implements MobilePageServiceConfiguration {

    @Override
    public Set<String> getPageObjectPackages() {
        return Set.of("io.perfeccionista.framework.pagefactory.pageobjects");
    }

    @Override
    public MobilePageFactoryPreferences getElementsPreferences() {
        return new DefaultAppiumAndroidPageFactoryPreferences();
    }

}
