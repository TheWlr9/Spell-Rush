package com.spellrush.persistence.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.spellrush.services.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBHelper {
    // FROM SAMPLE PROJECT - Copies database to the device
    public static void copyDatabaseToDevice(Context context) {
        final String DB_PATH = "db";

        String[] assetNames;
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(context, assetNames, dataDirectory);

            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());

            //System.out.println("Database Path: " + Services.getDBPathName());

        } catch (final IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    // FROM SAMPLE PROJECT - goes through each database asset and copies into device
    private static void copyAssetsToDirectory(Context context, String[] assets, File directory) throws IOException {
        AssetManager assetManager = context.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
