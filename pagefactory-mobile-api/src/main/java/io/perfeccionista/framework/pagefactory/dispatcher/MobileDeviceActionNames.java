package io.perfeccionista.framework.pagefactory.dispatcher;

public class MobileDeviceActionNames {

    private MobileDeviceActionNames() {
    }

    public static final String IS_MOBILE_APPLICATION_INSTALLED_METHOD = "IsMobileApplicationInstalled";
    public static final String GET_MOBILE_APPLICATION_STATE_METHOD = "GetMobileApplicationState";
    public static final String INSTALL_MOBILE_APPLICATION_METHOD = "InstallMobileApplication";
    public static final String REMOVE_MOBILE_APPLICATION_METHOD = "RemoveMobileApplication";
    public static final String ACTIVATE_MOBILE_APPLICATION_METHOD = "ActivateMobileApplication";
    public static final String TERMINATE_MOBILE_APPLICATION_METHOD = "TerminateMobileApplication";
    public static final String SEND_MOBILE_APPLICATION_TO_BACKGROUND_METHOD = "SendMobileApplicationToBackground";
    public static final String MOBILE_APPLICATION_SHOULD_BE_INSTALLED_METHOD = "MobileApplicationShouldBeInstalled";
    public static final String MOBILE_APPLICATION_SHOULD_NOT_BE_INSTALLED_METHOD = "MobileApplicationShouldNotBeInstalled";



    public static final String GET_SCREEN_BOUNDS_METHOD = "GetScreenBounds";
    public static final String GET_SCREEN_ORIENTATION_METHOD = "GetScreenOrientation";
    public static final String DEVICE_SHAKE_METHOD = "DeviceShake";
    public static final String DEVICE_IS_LOCKED_METHOD = "DeviceIsLocked";
    public static final String DEVICE_LOCK_METHOD = "DeviceLock";
    public static final String DEVICE_UNLOCK_METHOD = "DeviceUnlock";
    public static final String DEVICE_ROTATE_ON_METHOD = "DeviceRotateOn";
    public static final String DEVICE_ROTATE_TO_METHOD = "DeviceRotateTo";
    public static final String DEVICE_PERFORM_TOUCH_ID_METHOD = "PerformTouchId";
    public static final String DEVICE_SEND_SMS_METHOD = "SendSms";
    public static final String DEVICE_CALL_METHOD = "Call";


    public static final String MOBILE_DEVICE_SHOULD_BE_LOCKED_METHOD = "DeviceShouldBeLocked";
    public static final String MOBILE_DEVICE_SHOULD_BE_UNLOCKED_METHOD = "DeviceShouldBeUnlocked";

    public static final String MOBILE_DEVICE_SHOULD_HAVE_SCREEN_ORIENTATION_METHOD = "DeviceShouldHaveScreenOrientation";


}
