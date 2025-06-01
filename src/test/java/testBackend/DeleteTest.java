package testBackend;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteTest {

    @Test
    public void DeleteTest_01() {
        Response responseBody= given().header("x-api-key", "reqres-free-v1").delete("https://reqres.in/api/users/12");
        Assert.assertEquals(responseBody.getStatusCode(), 204);

        System.out.println("EL CODIGO DE RESPUESTA ES: "+ responseBody.getStatusCode());
        System.out.println("EL SERVICIO SE TARDO: "+ responseBody.getTime() + " MILISEGUNDOS");
    }

    @Test
    public void DeleteTest_02() {

        //------------ Configuración extra que podemos agregar a nuestros tests
        String urlREQRES= "https://reqres.in/api";
        String pathUser= "/users";
        String deleteUser= "/55";


        Response responseBody= given().header("x-api-key", "reqres-free-v1").delete(urlREQRES+ pathUser+ deleteUser);
        Assert.assertEquals(responseBody.getStatusCode(), 204);

        System.out.println("EL CODIGO DE RESPUESTA "+ responseBody.getStatusCode()+ "NOS ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO "+ deleteUser) ;
        System.out.println("EL SERVICIO SE TARDO: "+ responseBody.getTime() + " MILISEGUNDOS");
    }
}

