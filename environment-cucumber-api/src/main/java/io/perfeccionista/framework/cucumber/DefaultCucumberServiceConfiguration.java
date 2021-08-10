package io.perfeccionista.framework.cucumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DefaultCucumberServiceConfiguration implements CucumberServiceConfiguration {

    @Override
    public Set<String> scanPackages() {
        return new HashSet<>(Arrays.asList("io.perfeccionista.framework"));
    }

}
