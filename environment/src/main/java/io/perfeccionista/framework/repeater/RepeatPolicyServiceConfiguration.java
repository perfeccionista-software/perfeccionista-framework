package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.service.ServiceConfiguration;

public interface RepeatPolicyServiceConfiguration extends ServiceConfiguration {

    RepeatPolicy getRepeatPolicy();

}
