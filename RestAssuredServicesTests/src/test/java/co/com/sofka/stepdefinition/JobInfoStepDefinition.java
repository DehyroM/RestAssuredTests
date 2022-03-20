package co.com.sofka.stepdefinition;

import co.com.sofka.model.ServiceModel;
import co.com.sofka.stepdefinition.setup.services.ServiceSetUp;
import co.com.sofka.util.KeyValues;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JobInfoStepDefinition extends ServiceSetUp {

    public static final Logger LOGER = Logger.getLogger(JobInfoStepDefinition.class);
    private static final String JOB_INFO_GIVEN_ERROR = "UNABLE TO CREATE REQUEST WITH JOB INFO";
    private static final String JOB_ID_REQUEST_ERROR = "UNABLE TO REQUEST JOB ID";
    private static final String UPDATE_REQUEST_ERROR = "UNABLE TO REQUEST THE UPDATE OF JOB INFO";
    private static final String JOB_ID_CREATE_ERROR = "UNABLE TO CREATE JOB ID";
    private static final String JOB_INFO_UPDATE_ERROR = "UNABLE TO UPDATE JOB INFO";
    private static final String JOB_ID_CREATE_SUCCESS = "JOB ID SUCCESSFULLY CREATED";
    private static final String UPDATE_JOB_INFO_SUCCESS = "JOB INFO SUCCESSFULLY UPDATED";

    private Response response;
    private RequestSpecification request;
    private final ServiceModel serviceModel = new ServiceModel();

    @Given("el trabajador ingresa en la pagina para crear o modificar su informacion de trabajo con un nombre de trabajador y nombre de trabajo que realiza")
    public void elTrabajadorIngresaEnLaPaginaParaCrearOModificarSuInformacionDeTrabajoConElNombreYElTrabajoQueRealiza() {

        try{

            generalSetUp();

            serviceModel.setUserName();
            serviceModel.setJobTitle();

            KeyValues keyValues = new KeyValues(serviceModel.getUserName(), serviceModel.getJobTitle());

            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(keyValues.fieldsJobId());

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(JOB_INFO_GIVEN_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @When("el usuario hace una peticion de creacion de identificador de trabajo")
    public void cuandoElUsuarioHaceUnaPeticionDeCreacionDeIdentificadorDeTrabajo() {

        try{

            response = request.when()
                    .post(CREATE_JOB_ID_RESOURCE)
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(JOB_ID_REQUEST_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @Then("el usuario debera ver un codigo de respuesta exitoso y la informacion de identificacion de su trabajo")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYLaInformacionDeIdentificacionDeSuTrabajo() {

        try{

            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("name", notNullValue(),
                            "job", notNullValue(),
                            "id", notNullValue(),
                            "createdAt", notNullValue())
                    .body("name",equalTo(serviceModel.getUserName()),
                            "job",equalTo(serviceModel.getJobTitle()));
            LOGER.info(JOB_ID_CREATE_SUCCESS);

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(JOB_ID_CREATE_ERROR);
            Assertions.fail(e.getMessage());
        }
    }

    @When("el usuario hace una peticion de actualizacion de la informacion del trabajador")
    public void cuandoElUsuarioHaceUnaPeticionDeActualizacionDeLaInformacionDelTrabajador() {

        try{

            response = request.when()
                    .put(SINGLE_USER_UPDATE_RESOURCE)
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(UPDATE_REQUEST_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @Then("el usuario debera ver un codigo de respuesta exitoso y la actualizacion de su informacion de trabajo")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYLaActualizacionDeSuInformacionDeTrabajo() {

        try{

            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", notNullValue(),
                            "job", notNullValue(),
                            "updatedAt", notNullValue())
                    .body("name",equalTo(serviceModel.getUserName()),
                            "job",equalTo(serviceModel.getJobTitle()));
            LOGER.info(UPDATE_JOB_INFO_SUCCESS);

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(JOB_INFO_UPDATE_ERROR);
            Assertions.fail(e.getMessage());
        }
    }




}
