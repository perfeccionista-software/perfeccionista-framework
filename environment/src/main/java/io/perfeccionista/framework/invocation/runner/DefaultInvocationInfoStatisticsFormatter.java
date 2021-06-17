package io.perfeccionista.framework.invocation.runner;

import java.time.temporal.ChronoField;
import java.util.Deque;

public class DefaultInvocationInfoStatisticsFormatter implements InvocationInfoStatisticsFormatter {

    @Override
    public String format(InvocationInfo invocationInfo) {
        Deque<InvocationResult> invocationResults = invocationInfo.getResults();
        if (invocationResults.isEmpty()) {
            return "";
        }
        var longSummaryStatistics = invocationResults.stream()
                .map(invocationResult -> invocationResult.getDuration(ChronoField.MILLI_OF_DAY))
                .mapToLong(Long::longValue)
                .summaryStatistics();

        return "[Calls: " + longSummaryStatistics.getCount()
                + "; Min duration: " + longSummaryStatistics.getMin()
                + "ms; Max duration: " + longSummaryStatistics.getMax()
                + "ms; Avg duration: " + Math.round(longSummaryStatistics.getAverage()) + "ms]";
    }

}
