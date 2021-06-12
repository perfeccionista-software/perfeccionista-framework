package io.perfeccionista.framework.cucumber.plugin;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EmbedEvent;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.WriteEvent;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.utils.ReflectionUtilsForClasses;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.utils.EnvironmentConfigurationResolver.resolveEnvironmentConfiguration;


/**
 * TestRunStarted - the first event sent.
 * TestSourceRead - sent for each feature file read, contains the feature file source.
 * SnippetsSuggestedEvent - sent for each step that could not be matched to a step definition, contains the raw snippets for the step.
 * StepDefinedEvent - sent for each step definition as it is loaded, contains the StepDefinition
 * TestCaseStarted - sent before starting the execution of a Test Case(/Pickle/Scenario), contains the Test Case
 * TestStepStarted - sent before starting the execution of a Test Step, contains the Test Step
 * EmbedEvent - calling scenario.embed in a hook triggers this event.
 * WriteEvent - calling scenario.write in a hook triggers this event.
 * TestStepFinished - sent after the execution of a Test Step, contains the Test Step and its Result.
 * TestCaseFinished - sent after the execution of a Test Case(/Pickle/Scenario), contains the Test Case and its Result.
 * TestRunFinished - the last event sent.
 *
 *             put("default_summary", () -> DefaultSummaryPrinter.class);
 *             put("html", () -> HtmlFormatter.class);
 *             put("json", () -> JsonFormatter.class);
 *             put("junit", () -> JUnitFormatter.class);
 *             put("null_summary", () -> NullSummaryPrinter.class);
 *             put("pretty", () -> PrettyFormatter.class);
 *             put("progress", () -> ProgressFormatter.class);
 *             put("message", () -> MessageFormatter.class);
 *             put("rerun", () -> RerunFormatter.class);
 *             put("summary", () -> DefaultSummaryPrinter.class);
 *             put("testng", () -> TestNGFormatter.class);
 *             put("timeline", () -> TimelineFormatter.class);
 *             put("unused", () -> UnusedStepsSummaryPrinter.class);
 *             put("usage", () -> UsageFormatter.class);
 *             put("teamcity", () -> TeamCityPlugin.class);
 *
 */
public class PerfeccionistaCucumber6Plugin implements ConcurrentEventListener {
    private static final Logger log = LoggerFactory.getLogger(PerfeccionistaCucumber6Plugin.class);

    protected static final Pattern ENVIRONMENT_CONFIGURATION_TAG_PATTERN = Pattern.compile("^@UseEnvironmentConfiguration\\((?<class>.*?)\\)$");

    private final EventHandler<TestRunStarted> testRunStartedHandler = this::handleTestRunStartedHandler;
    private final EventHandler<TestSourceRead> featureStartedHandler = this::handleFeatureStartedHandler;

//    private final EventHandler<SnippetsSuggestedEvent> snippetsSuggestedEventHandler = this::handleSnippetsSuggestedHandler;
//    private final EventHandler<StepDefinedEvent> stepDefinedEventHandler = this::handleStepDefinedHandler;

    private final EventHandler<TestCaseStarted> caseStartedHandler = this::handleTestCaseStarted;
    private final EventHandler<TestStepStarted> stepStartedHandler = this::handleTestStepStarted;
    private final EventHandler<EmbedEvent> embedEventHandler = this::handleEmbedEvent;
    private final EventHandler<WriteEvent> writeEventHandler = this::handleWriteEvent;
    private final EventHandler<TestStepFinished> stepFinishedHandler = this::handleTestStepFinished;
    private final EventHandler<TestCaseFinished> caseFinishedHandler = this::handleTestCaseFinished;
    private final EventHandler<TestRunFinished> testRunFinishedHandler = this::handleTestRunFinishedHandler;

    private LocalTime stepStartedTime = null;
    private LocalTime stepFinishedTime = null;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, testRunStartedHandler);

        publisher.registerHandlerFor(TestSourceRead.class, featureStartedHandler);

        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);

        publisher.registerHandlerFor(EmbedEvent.class, embedEventHandler);
        publisher.registerHandlerFor(WriteEvent.class, writeEventHandler);

        publisher.registerHandlerFor(TestStepFinished.class, stepFinishedHandler);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);

        publisher.registerHandlerFor(TestRunFinished.class, testRunFinishedHandler);
    }




    private void handleTestRunStartedHandler(TestRunStarted event) {
        System.out.println("   >>>   handleTestRunStartedHandler: " + LocalDateTime.now().toString());

    }

    private void handleFeatureStartedHandler(TestSourceRead event) {
        System.out.println("   >>>   handleFeatureStartedHandler: " + LocalDateTime.now().toString());
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        System.out.println("   >>>   handleTestCaseStarted: " + LocalDateTime.now().toString());

        Class<? extends EnvironmentConfiguration> environmentConfigurationClass = null;

        for (String tag : event.getTestCase().getTags()) {
            Matcher matcher = ENVIRONMENT_CONFIGURATION_TAG_PATTERN.matcher(tag);
            if (matcher.find()) {
                String fileName = matcher.group("class");
                environmentConfigurationClass = ReflectionUtilsForClasses.loadClass(fileName, EnvironmentConfiguration.class);
            }
        }
        Environment environment;
        if (Objects.isNull(environmentConfigurationClass)) {
            environment = new Environment(resolveEnvironmentConfiguration());
        } else {
            environment = new Environment(environmentConfigurationClass);
        }
        environment.setEnvironmentForCurrentThread();
        environment.init();

    }

    private void handleTestStepStarted(TestStepStarted event) {
        stepStartedTime = LocalTime.now();
        System.out.println("   >>>   handleTestStepStarted: " + stepStartedTime.toString());

    }

    private void handleEmbedEvent(EmbedEvent event) {
        System.out.println("   >>>   handleEmbedEvent: " + LocalDateTime.now().toString());

    }

    private void handleWriteEvent(WriteEvent event) {
        System.out.println("   >>>   handleWriteEvent: " + LocalDateTime.now().toString());

    }

    private void handleTestStepFinished(TestStepFinished event) {
        stepFinishedTime = LocalTime.now();
        long durationInNanos = stepFinishedTime.toNanoOfDay() - stepStartedTime.toNanoOfDay();
        System.out.println("   >>>   " + durationInNanos/1_000_000 + " ms");
        System.out.println("   >>>   handleTestStepFinished: " + stepFinishedTime.toString());

    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        System.out.println("   >>>   handleTestCaseFinished: " + LocalDateTime.now().toString());

        Throwable throwable = event.getResult().getError();
        if (Objects.nonNull(throwable)) {
            if (throwable instanceof PerfeccionistaException) {
                log.error(((PerfeccionistaException) throwable)::getAttachmentDescription);
            }
        }

        Optional<Environment> environmentInstanceForCurrentThread = Environment.get();
        environmentInstanceForCurrentThread.ifPresent(environment -> {
            environment.shutdown();
            environment.removeEnvironmentForCurrentThread();
        });

    }

    private void handleTestRunFinishedHandler(TestRunFinished event) {
        System.out.println("   >>>   handleTestRunFinishedHandler: " + LocalDateTime.now().toString());

    }

}
