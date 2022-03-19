package co.com.sofka.stepdefinition;

import co.com.sofka.model.ServiceModel;
import co.com.sofka.stepdefinition.setup.services.ServiceSetUp;
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
import static org.hamcrest.Matchers.notNullValue;

public class CreateJobIdStepDefinition extends ServiceSetUp {

    public static final Logger LOGER = Logger.getLogger(CreateJobIdStepDefinition.class);

    private Response response;
    private RequestSpecification request;
    private ServiceModel serviceModel;

    @Given("el trabajador ingresa en la pagina de creacion de identificador de trabajo con un nombre de trabajador y nombre de trabajo que realiza")
    public void elTrabajadorIngresaEnLaPaginaDeCreacionDeIdentificadorDeTrabajoConElNombreYElTrabajoQueRealiza() {

        try{

            generalSetUp();

            serviceModel = new ServiceModel();
            serviceModel.setUserName();
            serviceModel.setJobTitle();

            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body("{\n" +
                            "  \"name\": \"" + serviceModel.getUserName() + "\",\n" +
                            "  \"job\": \"" + serviceModel.getJobTitle() + "\"\n" +
                            "}");

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
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
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
}