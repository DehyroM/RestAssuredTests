package co.com.sofka.stepdefinition.setup.services;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class ServiceSetUp {

    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";
    protected static final String CREATE_JOB_ID_RESOURCE = "/users";
    protected static final String SINGLE_USER_UPDATE_RESOURCE = "/users/2";
    protected static final String LOGIN_RESOURCE = "/login";

    protected void generalSetUp(){

        setUpLog4j2();
        setUpBases();

    }

    private void setUpLog4j2(){
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/"+LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

    }


}
