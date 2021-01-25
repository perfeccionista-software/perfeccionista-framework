package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.CommandExecutionFailed;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.COMMAND_EXECUTION_FAILED;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.COMMAND_EXECUTION_FAILED_WITH_CODE;

public class RuntimeUtils {

    private RuntimeUtils() {
    }

    public static String executeCommandAndGetOutput(@NotNull String commandLine) {
        try {
            Process process = Runtime.getRuntime().exec("adb shell wm size");
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                return new BufferedReader(new InputStreamReader(process.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
            } else {
                String output = new BufferedReader(new InputStreamReader(process.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
                throw CommandExecutionFailed.exception(COMMAND_EXECUTION_FAILED_WITH_CODE.getMessage(exitVal))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("CommandLine", commandLine))
                        .addLastAttachmentEntry(StringAttachmentEntry.of("Output", output));
            }
        } catch (InterruptedException | IOException e) {
                throw CommandExecutionFailed.exception(COMMAND_EXECUTION_FAILED.getMessage(), e);
        }
    }

}
