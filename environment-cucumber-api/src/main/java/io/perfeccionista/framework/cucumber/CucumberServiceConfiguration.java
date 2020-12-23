package io.perfeccionista.framework.cucumber;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Set;

public interface CucumberServiceConfiguration extends ServiceConfiguration {

    Set<String> scanPackages();

}
