package io.perfeccionista.framework.cucumber;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverExpression;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolver;
import io.perfeccionista.framework.cucumber.resolver.CucumberResolverPriority;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

public class CucumberService implements Service {

    protected List<CucumberResolver<?>> cucumberResolvers = new ArrayList<>();

    private CucumberServiceConfiguration configuration;
    private Environment environment;

    @Override
    public void init(@NotNull Environment environment, @Nullable ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);

        Set<Class<?>> cucumberResolverClasses = new HashSet<>();
        Set<Class<?>> comparators = new HashSet<>();

        ClassFilter conditionClassFilter = ClassFilter.of(processedClass ->
                CucumberResolver.class.isAssignableFrom(processedClass)
                        && !processedClass.isInterface()
                        && !Modifier.isAbstract(processedClass.getModifiers()));

        long start = System.nanoTime();

        this.configuration.scanPackages()
                .forEach(packageName -> {
                    cucumberResolverClasses.addAll(ReflectionUtils.findAllClassesInPackage(packageName, conditionClassFilter));
//                    comparators.addAll();
                });

        System.out.println(" -----------------> " + (System.nanoTime() - start)/1_000_000 + " ms");

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

//        comparators.stream()

        System.out.println(" -----------------> " + (System.nanoTime() - start)/1_000_000 + " ms");
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
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
