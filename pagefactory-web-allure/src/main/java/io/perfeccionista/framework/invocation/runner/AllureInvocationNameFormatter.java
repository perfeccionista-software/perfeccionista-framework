package io.perfeccionista.framework.invocation.runner;

public class AllureInvocationNameFormatter extends DefaultInvocationInfoNameFormatter {

    @Override
    public String format(InvocationInfo invocationInfo) {
        return invocationInfo.getType() + ": " + invocationInfo.getFormat();
    }

}
