package io.perfeccionista.framework.cucumber;

import java.util.Set;

public class DefaultCucumberServiceConfiguration implements CucumberServiceConfiguration {

    @Override
    public Set<String> scanPackages() {
        return Set.of("io.perfeccionista.framework");
    }

}
