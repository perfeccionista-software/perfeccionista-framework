package io.perfeccionista.framework.bdd;

import io.perfeccionista.framework.Environment;

public interface EnvironmentAvailable {

    default Environment getEnvironment() {
        return null;
    }

}
