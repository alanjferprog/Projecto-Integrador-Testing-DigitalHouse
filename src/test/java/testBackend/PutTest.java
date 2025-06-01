package testBackend;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PutTest {

    @Test
    public void PutTest_01() {

        String urlREQRES = "https://reqres.in/api";
        String pathUser = "/users/";
        String page = "55";

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "SPACE");
        requestBody.addProperty("job", "CAMBIADO POR RESTASSURED");

        Response responseBody = given().contentType("application/json").body(requestBody)
                .header("x-api-key", "reqres-free-v1")
                .put(urlREQRES + pathUser + page);

        String nombreModificado= responseBody.jsonPath().getString("name");
        String trabajoModificado= responseBody.jsonPath().getString("job");

        Assert.assertEquals(nombreModificado, "SPACE");
        Assert.assertEquals(trabajoModificado, "CAMBIADO POR RESTASSURED");
        Assert.assertEquals(responseBody.getStatusCode(), 200);
        Assert.assertTrue(responseBody.getBody().jsonPath().getString("updatedAt").contains("2025-06-01"));

        System.out.println("EL NOMBRE MODIFICADO ES: "+ nombreModificado);
        System.out.println("EL TRABAJO MODIFICADO ES: "+ trabajoModificado);
        System.out.println("EL CODIGO DE RESPUESTA ES: "+ responseBody.getStatusCode());
        System.out.println("EL SERVICIO SE TARDO: "+ responseBody.getTime() + " MILISEGUNDOS");
    }

}
