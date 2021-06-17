package io.perfeccionista.framework.invocation.runner;

import java.time.temporal.ChronoField;
import java.util.Deque;

public class AllureInvocationStatisticsFormatter extends DefaultInvocationInfoStatisticsFormatter {

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

        return "Calls: " + longSummaryStatistics.getCount() + "\n"
                + "Min duration: " + longSummaryStatistics.getMin() + " ms\n"
                + "Max duration: " + longSummaryStatistics.getMax() + " ms\n"
                + "Avg duration: " + Math.round(longSummaryStatistics.getAverage()) + "ms";
    }

}
