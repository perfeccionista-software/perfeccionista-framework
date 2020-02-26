package io.perfeccionista.framework.pagefactory.js;

import io.perfeccionista.framework.pagefactory.driver.DriverInstance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsRepository {

    private Set<String> loadedJsFunctions = new HashSet<>();

    // Добавить оши
    public void loadIfNotLoaded(DriverInstance driverInstance, String... jsFunctionNames) {
        Arrays.stream(jsFunctionNames).forEach(jsFunctionName -> {
            if (!loadedJsFunctions.contains(jsFunctionName)) {
//                driverInstance.getDriverOperationExecutor().executeOperation(.................);
                loadedJsFunctions.add(jsFunctionName);
            }
        });
    }



}
