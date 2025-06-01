package testBackend;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.lang.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PostTest {


       // Se busca dar de alta usuarios. A diferencia de los Get necesitmaos enviar un Request.
    @Test
    public void PostTest_01(){

        String urlREQRES = "https://reqres.in/api";
        String pathUser = "/users";

        JsonObject request= new JsonObject();
        request.addProperty("name", "space");
        request.addProperty("job", "leader");

    /* Metodo de Assure, en "Content Type" -> Se pasa el tipo de archivo que vamos a enviar.
       En el "body" -> Mandamos nuestro objeto, como es en formato Json y ya lo estamos aclarando antes no es necesario nada más.
       Enviamos un peticio tipo "post" a la url de la api y hacemos la validación de que nos de un codigo 201. */
        given().contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(request)
                .post(urlREQRES+ pathUser)

                //      A partir del 'then' se realizan validaciones del status y de lo obtenido en el body con testNg
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("SpaceR"))
                .body("job", equalTo("leader"))
                .body("createdAt", containsString("2025-05-29"));
    }

    //     Test que valida la falla del servicio cuando se ingresa solamente el mail
    @Test
    public void PostFallidoFaltaContraseña(){
        JsonObject request= new JsonObject();
        request.addProperty("email", "space@space");

        Response response= given().contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(request)
                .post("https://reqres.in/api/login");

        String error= response.jsonPath().getString("error");
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(error, "Missing password");

        System.out.println(response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);
        System.out.println("EL CODIGO DE ERROR ES: " +response.statusCode());
        System.out.println("EL SERVICIO TARDÓ: " +response.time()+ " MILISEGUNDOS");
    }

    //     Test que valida la falla del servicio cuando se ingresa solamente la contraseña
    @Test
    public void PostFallidoFaltaEmail(){
        JsonObject request= new JsonObject();
        request.addProperty("password", "1122345");

        Response response= given().contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(request)
                .post("https://reqres.in/api/login");

        String error= response.jsonPath().getString("error");
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(error, "Missing email or username");

        System.out.println(response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);
        System.out.println("EL CODIGO DE ERROR ES: " +response.statusCode());
        System.out.println("EL SERVICIO TARDÓ: " +response.time()+ " MILISEGUNDOS");
    }

    //             Test que valida que falla del servicio si no se agrega las llaves de acceso a la API.
    @Test
    public void PostFallidoLLaveAcceso(){
        JsonObject request= new JsonObject();
        request.addProperty("email", "space@space");

        Response response= given().contentType("application/json")
                .body(request)
                .post("https://reqres.in/api/login");

        String error= response.jsonPath().getString("error");
        String key= response.jsonPath().getString("how_to_get_one");
        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertEquals(error, "Missing API key.");

        System.out.println(response.getBody().asString());
        System.out.println("EL MENSAJE DE ERROR ES: " + error);
        System.out.println("PUEDES CONSEGUIR UNA EN: " + key);
        System.out.println("EL CODIGO DE ERROR ES: " +response.statusCode());
        System.out.println("EL SERVICIO TARDÓ: " +response.time()+ " MILISEGUNDOS");
    }


}
