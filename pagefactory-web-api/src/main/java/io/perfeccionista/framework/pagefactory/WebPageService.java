package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.WebPageNotFound;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ClassFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.PAGE_NAME_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.PAGE_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.PAGE_NOT_FOUND_BY_NAME;
import static org.junit.platform.commons.util.ReflectionUtils.findAllClassesInPackage;

/**
 * Сущность является пейджфактори, которая
 */
public class WebPageService implements Service {

    private final Map<Class<? extends WebPage>, WebPage> pageInstances = new HashMap<>();
    private final Map<String, Class<? extends WebPage>> pageClassesByName = new HashMap<>();
    private Set<Class<? extends WebPage>> availablePageClasses = new HashSet<>();
    private WebPageServiceConfiguration configuration;
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
        this.configuration.getPageObjectPackages()
                .forEach(pageObjectPackage -> findAllClassesInPackage(pageObjectPackage, ClassFilter.of(WebPage.class::isAssignableFrom))
                .stream()
                .map(webPageClass -> (Class<? extends WebPage>) webPageClass)
                .forEach(webPageClass -> {
                    availablePageClasses.add(webPageClass);
                    List<Name> names = AnnotationUtils.findRepeatableAnnotations(webPageClass, Name.class);
                    names.stream().map(Name::value).forEach(name -> {
                        if (pageClassesByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(PAGE_NAME_DUPLICATE.getMessage(name, webPageClass, pageClassesByName.get(name)));
                        }
                        pageClassesByName.put(name, webPageClass);
                    });
                }));
    }

    public WebPageServiceConfiguration getConfiguration() {
        return configuration;
    }

    // Инстанс возвращается без экземпляра вебдрайвера
    public <T extends WebPage> T getByClass(@NotNull Class<T> pageClass) {
        if (!availablePageClasses.contains(pageClass)) {
            throw WebPageNotFound.exception(PAGE_NOT_FOUND_BY_CLASS.getMessage(pageClass.getCanonicalName()));
        }
        if (pageInstances.containsKey(pageClass)) {
            // TODO: Сделать проверку на соответствие класса
            return (T) pageInstances.get(pageClass);
        }
        // TODO: Сделать проверку на соответствие класса
        return (T) createInstance(pageClass);
    }

    public <T extends WebPage> T getByName(@NotNull String pageName) {
        Class<? extends WebPage> pageClass = pageClassesByName.get(pageName);
        if (pageClass == null) {
            throw WebPageNotFound.exception(PAGE_NOT_FOUND_BY_NAME.getMessage(pageName));
        }
        if (pageInstances.containsKey(pageClass)) {
            // TODO: Сделать проверку на соответствие класса
            return (T) pageInstances.get(pageClass);
        }
        // TODO: Сделать проверку на соответствие класса
        return (T) createInstance(pageClass);
    }

    public WebPageFactory getWebPageFactory() {
        return new WebPageFactory(configuration.getElementsPreferences());
    }

    private WebPage createInstance(Class<? extends WebPage> pageClass) {
        WebPage webPageInstance = getWebPageFactory()
                .createWebPage(pageClass);
        webPageInstance.setEnvironment(environment);
        pageInstances.put(pageClass, webPageInstance);
        return webPageInstance;
    }

    protected WebPageServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebPageServiceConfiguration) {
            return (WebPageServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
