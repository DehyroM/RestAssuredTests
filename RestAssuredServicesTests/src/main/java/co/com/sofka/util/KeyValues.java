package co.com.sofka.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static co.com.sofka.util.CreateKey.*;

public class KeyValues {

    private static String name;
    private static String job;
    private static String email;
    private static String password;

    protected static final String CREATE_UPDADE_JOB_INFO_JSON_PATH = "./src/test/resources/files/create/Create_Update.json";
    protected static final String LOG_IN_FAIL_JSON_PATH = "./src/test/resources/files/Login_Fail.json";

    public KeyValues(String name, String job){
        this.name = name;
        this.job = job;
    }

    public KeyValues(String email){
        this.email = email;
    }

    public static String fieldsJobId() throws IOException {

        String data = new String(Files.readAllBytes(Paths.get(CREATE_UPDADE_JOB_INFO_JSON_PATH)));

        if (data.contains(NAME.getValue())){
            data = data.replace(NAME.getValue(),name);
        }
        if (data.contains(JOB.getValue())){
            data = data.replace(JOB.getValue(),job);
        }
        return data;
    }
    public static String fieldsLogInFail() throws IOException {

        String data = new String(Files.readAllBytes(Paths.get(LOG_IN_FAIL_JSON_PATH)));

        if (data.contains(EMAIL.getValue())){
            data = data.replace(EMAIL.getValue(),email);
        }
        return data;
    }
}
