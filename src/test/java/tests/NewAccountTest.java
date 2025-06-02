package tests;

import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;
import Pages.NewAccountPage;
import utils.AuthHelper;

import java.time.Duration;

public class NewAccountTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public String url= "http://localhost:8080/parabank/index.htm";

    // Configuración del reporte con ExtentReports
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reportes_newaccount.html");
    static ExtentReports extent;

    /**
     * Se ejecuta una vez antes de todos los tests.
     * Inicializa y configura el reporte HTML para las pruebas.
     */
    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    /**
     * Se ejecuta antes de cada test.
     * Abre el navegador, inicializa los objetos necesarios y navega a la página de login.
     */
    @BeforeEach
    public void preconditions() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.setup();
        newAccountPage.url(url);
    }

    /**
     * Caso de prueba: Validar login exitoso.
     * Verifica que se muestre el mensaje de bienvenida al usuario.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("NEW ACCOUNT")
    @DisplayName("Nueva cuenta bancaria exitosa.")
    public void testNewAccount_01() {
        ExtentTest test = extent.createTest("Crear nueva cuenta bancaria Exitosamente.");
        test.log(Status.INFO, "Comienzo de test crear nueva cuenta bancaria.");
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);

        AuthHelper.login(driver);

        newAccountPage.clickNuevaCuenta();
        test.log(Status.PASS, "Click en el boton de nueva cuenta.");
        newAccountPage.seleccionarCuenta();
        test.log(Status.PASS, "Eleccion de tipo de cuenta.");
        newAccountPage.clickAbrirCuenta();
        test.log(Status.PASS, "Click en el boton para crear la cuenta efectivamente.");

        if (newAccountPage.msjCuentaCreada().equals("Congratulations, your account is now open.")) {
            test.log(Status.PASS, "Validación de Login Exitoso");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje de Login Exitoso");
        }
    }

    /**
     * Se ejecuta después de cada test.
     * Cierra el navegador para limpiar el entorno de prueba.
     */
    @AfterEach
    public void close() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    /**
     * Se ejecuta una vez al finalizar todos los tests.
     * Guarda el reporte generado por ExtentReports.
     */
    @AfterAll
    public static void saveReport() {
        extent.flush();
    }

}
