package io.perfeccionista.framework.repeater;

import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

/**
 * TODO: JavaDoc
 */
public class RepeaterInvocationContext implements TestTemplateInvocationContext {

    private final String displayName;
    private final int currentIndex;

    // TODO: Передавать сюда не индекс попытки, а суффикс, сформированный из маски суффикса (из ретрай конфигурации) и номера попытки
    public RepeaterInvocationContext(String displayName, int currentIndex) {
        this.displayName = displayName;
        this.currentIndex = currentIndex;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return displayName + " -> attempt " + currentIndex;
    }

}
