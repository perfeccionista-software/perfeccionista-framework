package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import io.perfeccionista.framework.matcher.dispatcher.MobileDeviceScreenDispatcherMatcher;
import io.perfeccionista.framework.measurements.Rotation3D;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface MobileDeviceScreenDispatcher {

    @NotNull MobileScreenBounds getScreenBounds();

    @NotNull ScreenOrientation getScreenOrientation();

    MobileDeviceScreenDispatcher rotateOn(@NotNull Rotation3D rotation);

    MobileDeviceScreenDispatcher rotateTo(@NotNull ScreenOrientation screenOrientation);

    @NotNull MobileDeviceScreenDispatcher startRecording();

    @NotNull MobileDeviceScreenDispatcher stopRecording();

    @NotNull Screenshot getPageScreenshot();


    // TODO: Эти свойства можно брать из конфига

//    driver.startRecordingScreen();
//    driver.startRecordingScreen(new BaseStartScreenRecordingOptions(....));

//    name	type	description
//    options	object	The following parameters of the action
//    options.remotePath	string	The path to the remote location, where the resulting video should be uploaded. The following protocols are supported http/https, ftp. Null or empty string value (the default setting) means the content of resulting file should be encoded as Base64 and passed as the endpoint response value. An exception will be thrown if the generated media file is too big to fit into the available process memory. This option only has an effect if there is screen recording process in progress and forceRestart parameter is not set to true.
//    options.username	string	The name of the user for the remote authentication.
//    options.password	string	The password for the remote authentication.
//    options.method	string	The http multipart upload method name. The 'PUT' one is used by default.
//    options.forceRestart	boolean	Whether to try to catch and upload/return the currently running screen recording (false, the default setting on server) or ignore the result of it and start a new recording immediately (true).
//    options.timeLimit	string	Recording time. 180 seconds is by default.
//    options.videoType	string	(iOS Only) The format of the screen capture to be recorded. Available formats are the output of ffmpeg -codecs such as libx264 and mpeg4. Defaults to mjpeg.
//            options.videoQuality	string	(iOS Only) The video encoding quality (low, medium, high, photo - defaults to medium).
//    options.videoFps	string	(iOS Only) The Frames Per Second rate of the recorded video. Change this value if the resulting video is too slow or too fast. Defaults to 10. This can decrease the resulting file size.
//            options.videoScale	string	(iOS Only) The scaling value to apply. Read https://trac.ffmpeg.org/wiki/Scaling for possible values. Example value of 720p scaling is '1280:720'. This can decrease/increase the resulting file size. No scale is applied by default.
//    options.bitRate	string	(Android Only) The video bit rate for the video, in megabits per second. 4 Mbp/s(4000000) is by default for Android API level below 27. 20 Mb/s(20000000) for API level 27 and above.
//    options.videoSize	string	(Android Only) The format is widthxheight. The default value is the device's native display resolution (if supported), 1280x720 if not. For best results, use a size supported by your device's Advanced Video Coding (AVC) encoder. For example, "1280x720"
//    options.bugReport	string	(Android Only) Set it to true in order to display additional information on the video overlay, such as a timestamp, that is helpful in videos captured to illustrate bugs. This option is only supported since API level 27 (Android O).


//    driver.stopRecordingScreen();
//    driver.stopRecordingScreen(new BaseStopScreenRecordingOptions(....));

//    name	description
//    remotePath	The path to the remote location, where the resulting video should be uploaded. The following protocols are supported http/https, ftp. Null or empty string value (the default setting) means the content of resulting file should be encoded as Base64 and passed as the endpoint response value. An exception will be thrown if the generated media file is too big to fit into the available process memory.
//    username	The name of the user for the remote authentication.
//    password	The password for the remote authentication.
//    method	The http multipart upload method name. The 'PUT' one is used by default.

//    TODO: Информация по системным меню
//    Map<String, Map<String, Object>> systemBars = instance.getSystemBars();
//    возвращает инфу про statusBar (вверху)

    Optional<Integer> getDensity();

    MobileDeviceScreenDispatcher should(@NotNull MobileDeviceScreenDispatcherMatcher matcher);

}
