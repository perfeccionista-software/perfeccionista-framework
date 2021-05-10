package io.perfeccionista.framework.cucumber;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolver;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverPriority;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtils.findAllClasses;

@DefaultServiceConfiguration(DefaultCucumberServiceConfiguration.class)
public class CucumberService implements Service {

    protected List<CucumberResolver<?>> cucumberResolvers = new ArrayList<>();
    // TODO: Implement
    protected Set<Class<?>> comparators = new HashSet<>();

    private CucumberServiceConfiguration configuration;
    private Environment environment;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);

        Set<String> scanPackages = validatePackageSet(this.configuration.scanPackages());
        Set<Class<? extends CucumberResolver>> cucumberResolverClasses = findAllClasses(scanPackages, CucumberResolver.class)
                .stream()
                .filter(cucumberResolverClass -> !Modifier.isAbstract(cucumberResolverClass.getModifiers())
                        && !cucumberResolverClass.isInterface()
                        && !cucumberResolverClass.isEnum())
                .collect(Collectors.toSet());

        cucumberResolvers = cucumberResolverClasses.stream()
                .map(processedClass -> (Class<? extends CucumberResolver<?>>) processedClass)
                .map(processedClass -> {
                    List<String> patterns = Arrays
                            .stream(processedClass.getAnnotationsByType(CucumberResolverExpression.class))
                            .map(CucumberResolverExpression::value)
                            .map(Arrays::asList)
                            .reduce(new ArrayList<>(), (previous, next) -> {
                                previous.addAll(next);
                                return previous;
                            });
                    CucumberResolver<?> cucumberResolver = ReflectionUtils.newInstance(processedClass);
                    cucumberResolver.init(environment, patterns);
                    Optional.ofNullable(processedClass.getAnnotation(CucumberResolverPriority.class))
                            .ifPresent(priority -> cucumberResolver.setPriority(priority.value()));
                    return cucumberResolver;
                })
                .sorted((o1, o2) -> o2.getPriority() - o1.getPriority())
                .collect(Collectors.toList());
    }

    public <T> Optional<T> resolveFirst(@NotNull Class<? extends CucumberResolver<T>> conditionResolverType,
                                        @NotNull String expression,
                                        @Nullable Object... args) {
        List<CucumberResolver<T>> resolvers = cucumberResolvers.stream()
                .sequential()
                .filter(cucumberResolver -> conditionResolverType.isAssignableFrom(cucumberResolver.getClass()))
                .map(cucumberResolver -> (CucumberResolver<T>) cucumberResolver)
                .collect(Collectors.toList());
        for (CucumberResolver<T> resolver : resolvers) {
            Optional<T> resolvedExpression = resolver.tryResolve(expression, args);
            if (resolvedExpression.isPresent()) {
                return resolvedExpression;
            }
        }
        return Optional.empty();
    }

    public <T> Set<T> resolveAll(@NotNull Class<? extends CucumberResolver<T>> conditionResolverType,
                                 @NotNull String expression,
                                 @Nullable Object... args) {
        List<CucumberResolver<T>> resolvers = cucumberResolvers.stream()
                .sequential()
                .filter(cucumberResolver -> conditionResolverType.isAssignableFrom(cucumberResolver.getClass()))
                .map(cucumberResolver -> (CucumberResolver<T>) cucumberResolver)
                .collect(Collectors.toList());
        Set<T> resolvedExpressions = new HashSet<>();
        for (CucumberResolver<T> resolver : resolvers) {
            resolver.tryResolve(expression, args)
                    .ifPresent(resolvedExpressions::add);
        }
        return resolvedExpressions;
    }

    protected CucumberServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof CucumberServiceConfiguration) {
            return (CucumberServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
