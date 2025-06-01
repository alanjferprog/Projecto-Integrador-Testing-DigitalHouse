package testBackend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GetTest {

    @Test
    public void Get_Test01() {

        String urlREQRES= "https://reqres.in/api";
        String pathUser= "/users?page=";
        String page= "2";

        Response responseBody= given().header("x-api-key", "reqres-free-v1").get(urlREQRES+ pathUser+ page);

        Assert.assertEquals(responseBody.getStatusCode(), 200);
        System.out.println("EL CODIGO DE RESPUESTA ES "+ responseBody.getStatusCode()) ;
        System.out.println("EL SERVICIO SE TARDO: "+ responseBody.getTime() + " MILISEGUNDOS");
    }
}
