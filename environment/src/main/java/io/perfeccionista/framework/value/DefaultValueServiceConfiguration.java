package io.perfeccionista.framework.value;

import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Optional;

public class DefaultValueServiceConfiguration implements ServiceConfiguration {

    @Override
    public Optional<Class<? extends Service>> getImplementation() {
        return Optional.of(DefaultValueService.class);
    }

}
