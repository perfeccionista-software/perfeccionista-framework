package io.perfeccionista.framework.pagefactory.registry;

import io.perfeccionista.framework.exceptions.RegisterDuplicateException;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ClassFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.PAGE_NAME_DUPLICATE;
import static org.junit.platform.commons.util.ReflectionUtils.findAllClassesInPackage;

public class WebPageRegistry implements PageRegistry<WebPage> {

    private ElementsConfiguration parentElementConfiguration;
    private Map<String, Class<? extends WebPage>> pageClasses = new HashMap<>();

    private Map<Class<? extends WebPage>, WebPage> pagesByClass = new HashMap<>();
    private Map<String, WebPage> pagesByName = new HashMap<>();

    public WebPageRegistry(@NotNull ElementsConfiguration parentElementConfiguration) {
        this.parentElementConfiguration = parentElementConfiguration;
    }

    public void init(Set<String> packages) {
        packages.forEach(pageObjectPackage -> findAllClassesInPackage(pageObjectPackage, ClassFilter.of(WebPage.class::isAssignableFrom))
                .stream()
                .map(pageClass -> (Class<? extends WebPage>) pageClass)
                .forEach(pageClass -> {
                    List<Name> names = AnnotationUtils.findRepeatableAnnotations(pageClass, Name.class);
                    names.stream().map(Name::value).forEach(name -> {
                        if (pageClasses.containsKey(name)) {
                            throw new RegisterDuplicateException(PAGE_NAME_DUPLICATE.getMessage(name, pageClass, pageClasses.get(name)));
                        }
                        pageClasses.put(name, pageClass);
                    });
                }));
    }

    public Optional<WebPage> getByClass(@NotNull Class<? extends WebPage> pageClass) {
        if (pagesByClass.containsKey(pageClass)) {
            return Optional.of(pagesByClass.get(pageClass));
        }
        return Optional.of(createInstance(pageClass));
    }

    public Optional<WebPage> getByName(@NotNull String pageName) {
        if (pagesByName.containsKey(pageName)) {
            return Optional.of(pagesByName.get(pageName));
        }
        if (pageClasses.containsKey(pageName)) {
            Class<? extends WebPage> pageClass = pageClasses.get(pageName);
            return Optional.of(createInstance(pageClass));
        }
        return Optional.empty();
    }

    private WebPage createInstance(Class<? extends WebPage> pageClass) {
        WebPage webPageInstance = new WebPageInitializer().initPage(pageClass, this.parentElementConfiguration);
        pagesByClass.put(pageClass, webPageInstance);
        webPageInstance.getNames().forEach(webPageInstanceName -> pagesByName.put(webPageInstanceName, webPageInstance));
        return webPageInstance;
    }

}
