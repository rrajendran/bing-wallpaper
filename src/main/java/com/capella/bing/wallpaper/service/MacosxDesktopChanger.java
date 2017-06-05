package com.capella.bing.wallpaper.service;

import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by ramesh on 05/06/2017.
 */
public class MacosxDesktopChanger implements Consumer<File> {
    public static final Logger LOGGER = getLogger(MacosxDesktopChanger.class);

    /**
     * Performs this operation on the given argument.
     *
     * @param file the input argument
     */
    @Override
    public void accept(File file) {
        String as[] = {
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + file.getAbsolutePath() + "\"",
                "-e", "end tell"
        };
        try {
            Runtime.getRuntime().exec(as);
        } catch (IOException e) {
            LOGGER.error("Error changing desktop on macosx.", e);
        }
    }


}
