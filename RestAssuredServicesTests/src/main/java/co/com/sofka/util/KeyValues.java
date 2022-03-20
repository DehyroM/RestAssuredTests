package co.com.sofka.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static co.com.sofka.util.CreateKey.NAME;
import static co.com.sofka.util.CreateKey.JOB;

public class KeyValues {

    private static String name;
    private static String job;
    protected static final String JSON_FILE_PATH = "./src/test/resources/files/create/Create.json";

    public KeyValues(String name, String job){
        this.name = name;
        this.job = job;
    }

    public static String fieldsJobId() throws IOException {

        String data = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));

        if (data.contains(NAME.getValue())){
            data = data.replace(NAME.getValue(),name);
        }
        if (data.contains(JOB.getValue())){
            data = data.replace(JOB.getValue(),job);
        }
        return data;
    }
}
