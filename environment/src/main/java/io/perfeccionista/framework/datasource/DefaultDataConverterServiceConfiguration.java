package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.name.Name;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class DefaultDataConverterServiceConfiguration implements DataConverterServiceConfiguration {

    @Override
    public Map<String, DataConverter<?, ?>> getDataConverters() {
        Set<String> packagesToScan = Environment.getCurrent()
                .getEnvironmentConfiguration()
                .getScanPackages();
        Set<Class<? extends DataConverter>> dataConverterClasses = findAllClasses(validatePackageSet(packagesToScan), DataConverter.class)
                .stream()
                .filter(dataConverterClass -> !Modifier.isAbstract(dataConverterClass.getModifiers())
                        && !dataConverterClass.isInterface()
                        && !dataConverterClass.isEnum())
                .collect(Collectors.toSet());
        Map<String, DataConverter<?, ?>> dataConvertersByName = new HashMap<>();
        dataConverterClasses.forEach(dataConverterClass -> {
            DataConverter<?, ?> dataConverterInstance = newInstance(dataConverterClass);
            dataConvertersByName.put(dataConverterClass.getCanonicalName(), dataConverterInstance);
            findRepeatableAnnotations(dataConverterClass, Name.class).stream()
                    .map(Name::value)
                    .forEach(name -> {
                        if (dataConvertersByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE.getMessage(name));
                        }
                        dataConvertersByName.put(name, dataConverterInstance);
                    });
        });
        return dataConvertersByName;
    }

}
