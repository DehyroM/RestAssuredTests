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

public class UserLoginStepDefinition extends ServiceSetUp {


    public static final Logger LOGER = Logger.getLogger(UserLoginStepDefinition.class);
    private static final String USER_INFO_GIVEN_ERROR = "UNABLE TO START REQUEST OF USER INFORMATION";
    private static final String LOGIN_GIVEN_ERROR = "UNABLE TO START REQUEST OF LOGIN";

    private static final String USER_INFO_REQUEST_ERROR = "UNABLE TO REQUEST USER INFORMATION";
    private static final String LOGIN_REQUEST_ERROR = "UNABLE TO REQUEST LOGIN";

    private static final String USER_INFO_GET_ERROR = "UNABLE TO GET USER INFORMATION";
    private static final String LOGIN_FAIL_POST_ERROR = "UNABLE TO POST LOG IN FAIL";

    private static final String USER_INFO_GET_SUCCESS = "USER INFORMATION SUCCESSFULLY OBTAINED";
    private static final String LOGIN_FAIL_POST_SUCCESS = "LOGIN FAIL CONFIRMATED";

    private Response response;
    private RequestSpecification request;
    private final ServiceModel serviceModel = new ServiceModel();

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
                    .get(SINGLE_USER_UPDATE_RESOURCE)
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

    @Given("el usuario ingresa en la pagina para loguearse con su informacion de nombre de usuario")
    public void elUsuarioIngresaEnLaPaginaParaLoguearseConSuNombreDeUsuario() {

        try{

            generalSetUp();

            serviceModel.setEmail();

            KeyValues keyValues = new KeyValues(serviceModel.getEmail());

            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(keyValues.fieldsLogInFail());
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(LOGIN_GIVEN_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @When("cuando se realiza la peticion para loguearse por parte del usuario")
    public void cuandoSeRealizaLaPeticionParaLoguearsePorParteDelUsuario() {

        try{

            response = request.when()
                    .post(LOGIN_RESOURCE)
            ;

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(LOGIN_REQUEST_ERROR);
            Assertions.fail(e.getMessage());

        }

    }
    @Then("se debera obtener un codigo de respuesta de error y un mensaje indicando que hace falta la contrasena")
    public void seDeberaObtenerUnCodigoDeRespuestaErrorYUnMensajeIndicandoQueHaceFaltaLaContrasena() {

        try{

            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("error", equalTo("Missing password"));
            LOGER.info(LOGIN_FAIL_POST_SUCCESS);

        }catch (Exception e){
            LOGER.error(e.getMessage(), e);
            LOGER.warn(LOGIN_FAIL_POST_ERROR);
            Assertions.fail(e.getMessage());
        }

    }




}
