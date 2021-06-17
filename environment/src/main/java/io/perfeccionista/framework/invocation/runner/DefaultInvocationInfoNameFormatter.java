package io.perfeccionista.framework.invocation.runner;

import java.util.Deque;

public class DefaultInvocationInfoNameFormatter implements InvocationInfoNameFormatter {

    @Override
    public String format(InvocationInfo invocationInfo) {
        Deque<InvocationResult> invocationResults = invocationInfo.getResults();
        if (invocationResults.isEmpty()) {
            return invocationInfo.getType() + ": " + invocationInfo.getFormat();
        }
        return invocationInfo.getType() + ": " + invocationInfo.getFormat() + " -> " + invocationResults.getLast().getStatus();
    }

}
