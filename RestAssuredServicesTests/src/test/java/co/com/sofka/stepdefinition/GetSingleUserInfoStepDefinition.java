package co.com.sofka.stepdefinition;

import co.com.sofka.stepdefinition.setup.services.ServiceSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetSingleUserInfoStepDefinition extends ServiceSetUp {


    public static final Logger LOGER = Logger.getLogger(GetSingleUserInfoStepDefinition.class);
    private static final String USER_INFO_GIVEN_ERROR = "UNABLE TO START REQUEST OF USER INFORMATION";
    private static final String USER_INFO_REQUEST_ERROR = "UNABLE TO REQUEST USER INFORMATION";
    private static final String USER_INFO_GET_ERROR = "UNABLE TO GET USER INFORMATION";
    private static final String USER_INFO_GET_SUCCESS = "USER INFORMATION SUCCESSFULLY OBTAINED";

    private Response response;
    private RequestSpecification request;

    @Given("se requiere solicitar la obtencion de informacion de un usuario especifico")
    public void seRequiereSolicitarLaObtencionDeInformacionDeUnUsuarioEspecifico() {

        try{

            generalSetUp();

            request = given()
                    .log()
                    .all()
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(USER_INFO_GIVEN_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @When("cuando se realiza la peticion de informacion del usuario")
    public void cuandoSeRealizaLaPeticionDeInformacionDelUsuario() {

        try{

            response = request.when()
                    .get(GET_SINGLE_USER_INFO_RESOURCE)
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(USER_INFO_REQUEST_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @Then("se debera obtener un codigo de respuesta exitoso y la informacion del usuario")
    public void seDeberaObtenerUnCodigoDeRespuestaExitosoYLaInformacionDelUsuario() {

        try{

            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("data", notNullValue(),
                            "support", notNullValue())
                    .body(containsString("janet.weaver@reqres.in"));
            LOGER.info(USER_INFO_GET_SUCCESS);

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(USER_INFO_GET_ERROR);
            Assertions.fail(e.getMessage());
        }

    }

}
