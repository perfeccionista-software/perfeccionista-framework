package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.PageNotFound;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
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

/**
 * Сущность является пейджфактори, которая
 */
public class WebPageService implements Service {

    private final Map<Class<? extends WebPage>, WebPage> pageInstances = new HashMap<>();
    private final Map<String, Class<? extends WebPage>> pageClassesByName = new HashMap<>();
    private final Set<Class<? extends WebPage>> availablePageClasses = new HashSet<>();

    private WebPageServiceConfiguration configuration;
    private WebPageFactory webPageFactory;
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
        findAllClasses(this.configuration.getPageObjectPackages(), WebPage.class)
                .forEach(webPageClass -> {
                    availablePageClasses.add(webPageClass);
                    List<Name> names = findRepeatableAnnotations(webPageClass, Name.class);
                    names.stream().map(Name::value).forEach(name -> {
                        if (pageClassesByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(PAGE_NAME_DUPLICATE.getMessage(name, webPageClass, pageClassesByName.get(name)));
                        }
                        pageClassesByName.put(name, webPageClass);
                    });
                });
    }

    public WebPageServiceConfiguration getConfiguration() {
        return configuration;
    }

    public @NotNull WebPageFactory getWebPageFactory() {
        if (Objects.isNull(webPageFactory)) {
            webPageFactory = new WebPageFactory(configuration.getElementsPreferences());
        }
        return webPageFactory;
    }

    public <T extends WebPage> @NotNull T getPageInstanceByClass(@NotNull Class<T> pageClass) {
        if (!availablePageClasses.contains(pageClass)) {
            throw PageNotFound.exception(PAGE_NOT_FOUND_BY_CLASS.getMessage(pageClass.getCanonicalName()));
        }
        if (!pageInstances.containsKey(pageClass)) {
            createInstance(pageClass);
        }
        return castObject(pageInstances.get(pageClass), pageClass);
    }

    public @NotNull WebPage getPageInstanceByName(@NotNull String pageName) {
        Class<? extends WebPage> pageClass = pageClassesByName.get(pageName);
        if (pageClass == null) {
            throw PageNotFound.exception(PAGE_NOT_FOUND_BY_NAME.getMessage(pageName));
        }
        if (!pageInstances.containsKey(pageClass)) {
            createInstance(pageClass);
        }
        return pageInstances.get(pageClass);
    }

    // TODO: Implement
//    public @NotNull WebNode createWebNode(@NotNull WebSelectorHolder rootSelector) {
//        getWebPageFactory().
//
//    }

//    public @NotNull WebNode createWebNode(@NotNull WebChildElement element) {
//
//    }

//    public @NotNull WebNode createWebNode(@NotNull WebChildElement parentElement,
//                                          @NotNull WebSelectorHolder rootSelector) {
//
//    }

    // TODO: Implement
//    public @NotNull WebList<WebNode> createWebList(@NotNull WebSelectorHolder rootSelector,
//                                                   @NotNull WebSelectorHolder itemSelector) {
//
//    }

//    public @NotNull WebList<WebNode> createWebList(@NotNull WebChildElement parentElement,
//                                                   @NotNull WebSelectorHolder rootSelector,
//                                                   @NotNull WebSelectorHolder itemSelector) {
//
//    }

    protected WebPageServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof WebPageServiceConfiguration) {
            return (WebPageServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

    protected void createInstance(@NotNull Class<? extends WebPage> pageClass) {
        WebPage webPageInstance = getWebPageFactory()
                .createWebPage(pageClass);
        pageInstances.put(pageClass, webPageInstance);
    }

    public static WebPageService getInstance() {
        return Environment.getCurrent().getService(WebPageService.class);
    }

}
