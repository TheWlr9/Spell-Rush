package com.spellrush.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import com.spellrush.services.Services;

// From the sample project
public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/spellrushDB.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Services.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
