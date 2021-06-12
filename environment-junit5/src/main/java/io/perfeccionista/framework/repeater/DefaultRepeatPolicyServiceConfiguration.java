package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.repeater.policy.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;

public class DefaultRepeatPolicyServiceConfiguration implements RepeatPolicyServiceConfiguration {

    @Override
    public RepeatPolicy getRepeatPolicy() {
        return new NoRepeatPolicy();
    }

}
