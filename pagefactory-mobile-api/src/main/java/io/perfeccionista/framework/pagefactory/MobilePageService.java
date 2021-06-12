package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.PageNotFound;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.PAGE_NAME_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.PAGE_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.PAGE_NOT_FOUND_BY_NAME;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.CastUtils.castObject;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;

public class MobilePageService implements Service {

    private final Map<Class<? extends MobilePage>, MobilePage> pageInstances = new HashMap<>();
    private final Map<String, Class<? extends MobilePage>> pageClassesByName = new HashMap<>();
    private final Set<Class<? extends MobilePage>> availablePageClasses = new HashSet<>();

    private MobilePageServiceConfiguration configuration;
    private MobilePageFactory mobilePageFactory;
    private Environment environment;

    /**
     *      * Заполняем связку Имя страницы -> Класс страницы
     * @param environment
     * @param configuration
     */
    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
        findAllClasses(this.configuration.getPageObjectPackages(), MobilePage.class)
                .forEach(mobilePageClass -> {
                    availablePageClasses.add(mobilePageClass);
                    List<Name> names = findRepeatableAnnotations(mobilePageClass, Name.class);
                    names.stream().map(Name::value).forEach(name -> {
                        if (pageClassesByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(PAGE_NAME_DUPLICATE.getMessage(name, mobilePageClass, pageClassesByName.get(name)));
                        }
                        pageClassesByName.put(name, mobilePageClass);
                    });
                });
    }

    public MobilePageServiceConfiguration getConfiguration() {
        return configuration;
    }

    public @NotNull MobilePageFactory getMobilePageFactory() {
        if (Objects.isNull(mobilePageFactory)) {
            mobilePageFactory = new MobilePageFactory(configuration.getElementsPreferences());
        }
        return mobilePageFactory;
    }

    public <T extends MobilePage> @NotNull T getPageInstanceByClass(@NotNull Class<T> pageClass) {
        if (!availablePageClasses.contains(pageClass)) {
            throw PageNotFound.exception(PAGE_NOT_FOUND_BY_CLASS.getMessage(pageClass.getCanonicalName()));
        }
        if (!pageInstances.containsKey(pageClass)) {
            createInstance(pageClass);
        }
        return castObject(pageInstances.get(pageClass), pageClass);
    }

    public @NotNull MobilePage getPageInstanceByName(@NotNull String pageName) {
        Class<? extends MobilePage> pageClass = pageClassesByName.get(pageName);
        if (pageClass == null) {
            throw PageNotFound.exception(PAGE_NOT_FOUND_BY_NAME.getMessage(pageName));
        }
        if (!pageInstances.containsKey(pageClass)) {
            createInstance(pageClass);
        }
        return pageInstances.get(pageClass);
    }

    protected MobilePageServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof MobilePageServiceConfiguration) {
            return (MobilePageServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

    protected void createInstance(@NotNull Class<? extends MobilePage> pageClass) {
        MobilePage mobilePageInstance = getMobilePageFactory()
                .createMobilePage(pageClass);
        pageInstances.put(pageClass, mobilePageInstance);
    }

}
