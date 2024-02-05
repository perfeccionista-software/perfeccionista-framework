package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.preconditions.Preconditions;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.String.format;

/**
 * Класс который выводит в лог содержимое {@link Environment}
 * Он должен собирать внутри информацию обо всех сконфигурированных сервисах,
 * включая отключенные экземпляры.
 *
 *  Информация выводится в виде:
 *  -----------------------------------------------------------------------------------------------------------
 *  Environment configuration
 *  -----------------------------------------------------------------------------------------------------------
 *      Functionality                  Implementation class
 *  -----------------------------------------------------------------------------------------------------------
 *      Environment class              Environment.class
 *      Action runner configuration    MyActionRunnerConfiguration.class
 *      Timeouts                       MyTimeouts.class
 *      Repeat policy                  MyRepeatPolicy.class
 *  -----------------------------------------------------------------------------------------------------------
 *  Providers
 *  -----------------------------------------------------------------------------------------------------------
 *      Service class                  Provider configuration                             Enabled     Order
 *  -----------------------------------------------------------------------------------------------------------
 *      DataSourceInstanceProvider     MyDataSourceInstanceProviderConfiguration.class    disabled    0
 *  -----------------------------------------------------------------------------------------------------------
 *
 */
public class EnvironmentLogger {

    private final EnvironmentConfiguration environmentConfiguration;
    private final List<ConfiguredServiceHolderRecord> services = new ArrayList<>();

    private final String testName;

    private long startTime = 0;
    private long finishTime = 0;

    private int column1 = 27;
    private int column2 = 0;
    private int column3 = 8;
    private int column4 = 5;
    private int column5 = 10;

    private EnvironmentLogger(EnvironmentConfiguration environmentConfiguration, String testName) {
        this.environmentConfiguration = environmentConfiguration;
        this.testName = testName;
        column2 = getMaxLength(environmentConfiguration.getEnvironmentClass().getCanonicalName(), column2);
    }

    public static EnvironmentLogger of(@NotNull EnvironmentConfiguration environmentConfiguration,
                                       @Nullable String testName) {
        Preconditions.notNull(environmentConfiguration, "EnvironmentConfiguration must not be null");
        return new EnvironmentLogger(environmentConfiguration, testName);
    }

    public static EnvironmentLogger of(@NotNull EnvironmentConfiguration environmentConfiguration) {
        Preconditions.notNull(environmentConfiguration, "EnvironmentConfiguration must not be null");
        return new EnvironmentLogger(environmentConfiguration, null);
    }

    public EnvironmentLogger start() {
        startTime = System.nanoTime();
        return this;
    }

    public EnvironmentLogger finish() {
        finishTime = System.nanoTime();
        return this;
    }

    public void addServiceRecord(@NotNull ConfiguredServiceHolder serviceHolder, long startTime, long finishTime) {
        Preconditions.notNull(serviceHolder, "ConfiguredServiceHolder must not be null");
        services.add(ConfiguredServiceHolderRecord.of(serviceHolder, startTime, finishTime));
        column1 = getMaxLength(serviceHolder.getServiceName(), column1);
        column2 = getMaxLength(serviceHolder.getServiceConfigurationName(), column2);
    }

    /**
     * @return
     */
    @Override
    @SuppressWarnings("Duplicates")
    public String toString() {
        String environmentInitializationTime = (finishTime - startTime)/1_000_000 + " ms";
        String tab = "    ";
        String splitter = makeSplitterForLength(4 + column1 + 4 + column2 + 4 + column3 + 4 + column4 + 4 + column5 + 4);
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(splitter).append("\n");
        sb.append(Objects.nonNull(testName) ? "Environment for test [" + this.testName + "]" : "Environment").append("\n");
        sb.append(splitter).append("\n");
        sb.append(tab)
                .append(formatToColumn(column1, "Environment class"))
                .append(tab)
                .append(formatToColumn(column2, environmentConfiguration.getEnvironmentClass().getCanonicalName())).append("\n");
        sb.append(tab)
                .append(formatToColumn(column1, "Environment configuration class"))
                .append(tab)
                .append(formatToColumn(column2, environmentConfiguration.getClass().getCanonicalName())).append("\n");
        sb.append(splitter).append("\n");
        sb.append("Services").append("\n");
        sb.append(splitter).append("\n");
        sb.append(tab)
                .append(formatToColumn(column1, "Service class"))
                .append(tab)
                .append(formatToColumn(column2, "Service configuration"))
                .append(tab)
                .append(formatToColumn(column3, "Enabled"))
                .append(tab)
                .append(formatToColumn(column4, "Order"))
                .append(tab)
                .append(formatToColumn(column4, "Duration"))
                .append("\n");
        sb.append(splitter).append("\n");
        services.stream()
                .sorted(Comparator.comparing(record -> record.configuredServiceHolder.getServiceName()))
                .forEachOrdered(record -> sb.append(tab)
                        .append(formatToColumn(column1, record.getConfiguredServiceHolder().getServiceName()))
                        .append(tab)
                        .append(formatToColumn(column2, record.getConfiguredServiceHolder().getServiceConfigurationName()))
                        .append(tab)
                        .append(formatToColumn(column3, record.getConfiguredServiceHolder().isEnabled() ? "enabled" : "disabled"))
                        .append(tab)
                        .append(formatToColumn(column4, String.valueOf(record.getConfiguredServiceHolder().getOrder())))
                        .append(tab)
                        .append(formatToColumn(column4, (record.getFinishTime() - record.getStartTime())/1_000_000 + " ms"))
                        .append("\n"));
        sb.append(splitter).append("\n");
        sb.append(tab)
                .append(formatToColumn(column1, "Initialization duration"))
                .append(tab)
                .append(formatToColumn(column2, environmentInitializationTime)).append("\n");
        sb.append(splitter).append("\n");
        return sb.toString();
    }

    private int getMaxLength(String string, int columnLength) {
        return max(string.length(), columnLength);
    }

    private String makeSplitterForLength(int length) {
        int indent = Math.max(0, length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    private String formatToColumn(int columnLength, String columnText) {
        return format("%1$-" + columnLength + "s", columnText);
    }

    private static final class ConfiguredServiceHolderRecord {

        private final ConfiguredServiceHolder configuredServiceHolder;
        private long startTime = 0;
        private long finishTime = 0;

        private ConfiguredServiceHolderRecord(ConfiguredServiceHolder configuredServiceHolder, long startTime, long finishTime) {
            this.configuredServiceHolder = configuredServiceHolder;
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

        public static ConfiguredServiceHolderRecord of(ConfiguredServiceHolder configuredServiceHolder, long startTime, long finishTime) {
            return new ConfiguredServiceHolderRecord(configuredServiceHolder, startTime, finishTime);
        }

        public ConfiguredServiceHolder getConfiguredServiceHolder() {
            return configuredServiceHolder;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getFinishTime() {
            return finishTime;
        }

    }

}
