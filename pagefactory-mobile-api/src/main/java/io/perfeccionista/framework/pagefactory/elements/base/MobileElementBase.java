package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;

public interface MobileElementBase extends EnvironmentAvailable, JsonSerializable, MobileLocatorChainAvailable {

    MobileDeviceDispatcher getMobileDeviceDispatcher();

}
