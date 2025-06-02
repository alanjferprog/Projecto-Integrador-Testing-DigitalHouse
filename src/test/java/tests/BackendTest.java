package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reportes.ExtentFactory;

import static io.restassured.RestAssured.given;


/**
 * Esta clase contiene pruebas automatizadas para verificar distintas funcionalidades del backend
 * del sitio Parabank. Utiliza la biblioteca REST Assured para realizar llamadas HTTP y ExtentReports
 * para generar reportes HTML.
 *
 * Las funcionalidades testeadas incluyen:
 * - Disponibilidad del formulario de registro
 * - Login de usuario
 * - Creación de cuentas
 * - Transferencias de fondos
 * - Visualización de actividad y resumen de cuentas
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BackendTest {

    //String urlBase= "https://parabank.parasoft.com/parabank/services_proxy/bank/";
    String urlBase= "http://localhost:8080/parabank/services_proxy/bank/";
    //String loginUrl= "https://parabank.parasoft.com/parabank/services/bank/login/";
    String loginUrl= "http://localhost:8080/parabank/services/bank/login/";
    //String webBase= "https://parabank.parasoft.com/parabank/";
    String webBase= "http://localhost:8080/parabank/";

    String customerId= "12434";
    String accountId= "13566";
    String username = "user17";
    String password = "112233";

    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES-BACKEND.html");
    static ExtentReports extent;

    /**
     * Inicializa el reporte antes de ejecutar cualquier prueba.
     */
    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    /**
     * Pausa entre pruebas para evitar sobrecarga de peticiones al servidor.
     */
    @BeforeEach
    public void time() throws InterruptedException {
        Thread.sleep(500);
    }

    /**
     * Verifica que la página HTML de registro esté disponible (HTTP 200).
     * No realiza un registro real, solo comprueba que el recurso exista.
     */
    @Test
    @Order(1)
    public void registro() {
        given()
                .get(webBase + "register.htm")
                .then()
                .statusCode(200)
                .log().body();
    }

    /**
     * Realiza un login usando usuario y contraseña como parámetros de la URL.
     * Extrae el ID del cliente del XML de respuesta y lo guarda en la variable `customerId`.
     */
    @Test
    @Order(2)
    public void login() {

        //AuthHelper.registerInLocal(new ChromeDriver());

        String response = given()
                .get(loginUrl + username + "/" + password)
                .then()
                .statusCode(200)
                .log().status()
                .extract().asString();

        XmlPath xmlPath = new XmlPath(response);
        customerId = xmlPath.getString("customer.id");

        System.out.println("Customer ID: " + customerId);
    }

    /**
     * Obtiene las cuentas del cliente autenticado y extrae el ID de la primera cuenta.
     * Este ID se guarda en la variable `accountId` para ser usado en pruebas posteriores.
     */
    @Test
    @Order(3)
    public void crearCuenta() {
        String response =given()
                .auth().basic(username, password)
                .get(urlBase + "customers/" + customerId + "/accounts")
                .then()
                .statusCode(200)
                .extract().asString();

        JsonPath jsonPath = new JsonPath(response);
        accountId = jsonPath.getString("[0].id");

        System.out.println("Account ID: " + accountId);

    }

    /**
     * Crea una nueva cuenta bancaria utilizando el ID de cliente y la cuenta origen.
     * Se requiere autenticación básica (usuario y contraseña).
     */
    @Test
    @Order(4)
    public void nuevaCuenta() {
        given()
                .auth().basic(username, password)
                .post(urlBase + "createAccount?customerId=" + customerId + "&newAccountType=1&fromAccountId=" + accountId)
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    /**
     * Intenta acceder al resumen de cuenta vía HTML.
     * Se espera una respuesta 404 (página no disponible por esa vía).
     */
    @Test
    @Order(5)
    public void resumenCuenta() {
        given()
                .get(webBase + "overview.html")
                .then()
                .statusCode(404)
                .log().status()
                .log().body();
    }

    /**
     * Realiza una transferencia de fondos entre cuentas utilizando los IDs y un monto fijo.
     * Requiere autenticación.
     */
    @Test
    @Order(6)
    public void descargaDeFondos() {
        String amount = "1000";
        given()
                .auth().basic(username, password)
                .post(urlBase + "transfer?fromAccountId="+accountId+ "&toAccountId=15453&amount=" + amount)
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    /**
     * Consulta la actividad (transacciones) de la cuenta bancaria seleccionada.
     * Se utilizan filtros por mes y tipo de transacción.
     */
    @Test
    @Order(7)
    public void actividadCuenta() {
        given()
                .auth().basic(username, password)
                .get(urlBase + "accounts/"+accountId+"/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }
}
