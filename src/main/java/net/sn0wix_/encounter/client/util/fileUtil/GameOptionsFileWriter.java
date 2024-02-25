package net.sn0wix_.encounter.client.util.fileUtil;

import net.minecraft.client.MinecraftClient;
import net.sn0wix_.encounter.common.Encounter;

import java.io.*;
import java.util.stream.Stream;

public class GameOptionsFileWriter {
    public static void run() throws IOException {
        MinecraftClient client = MinecraftClient.getInstance();

        File newOptions = new File(client.runDirectory, Encounter.NEW_GAME_OPTIONS_FILE_NAME);
        File oldOptions = new File(client.runDirectory, "options.txt");

        if (!newOptions.exists()) {
            newOptions.createNewFile();

            if (oldOptions.exists()) {
               writeOptions(oldOptions, newOptions);
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(newOptions));
                writer.write("fov:0.0\n");
                writer.write("renderDistance:20\n");

                writer.close();
            }
        } else {
            writeOptions(newOptions, newOptions);
        }
    }

    private static void writeOptions(File readFrom, File writeTo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(readFrom));

        Stream<String> streamFromFile = reader.lines();
        StringBuilder finalFileContent = new StringBuilder();

        streamFromFile.forEach(string -> finalFileContent.append(checkStringForEditableOptions(string)).append("\n"));

        BufferedWriter writer = new BufferedWriter(new FileWriter(writeTo));
        writer.write(finalFileContent.toString());

        writer.close();
    }


    //remove \n
    private static String checkStringForEditableOptions(String s) {
        /*if (s.contains("fov:")) {
            return "fov:0.0\\n";
        } else if (s.contains("renderDistance:")) {
            int i = getIntFromTo(15, s.length() - 1, s);

            if (i >= 20) {
                return s;
            } else {
                return "renderDistance:20\\n";
            }
        }*/

        return s;
    }

    private static int getIntFromTo(int index0, int index1, String s) {
        StringBuilder builder = new StringBuilder();

        for (int i = index0; i <= index1; i++) {
            try {
                int j = Integer.parseInt(String.valueOf(s.charAt(i)));
                builder.append(j);
            } catch (NumberFormatException ignored) {
            }
        }

        return Integer.parseInt(builder.toString());
    }
}
