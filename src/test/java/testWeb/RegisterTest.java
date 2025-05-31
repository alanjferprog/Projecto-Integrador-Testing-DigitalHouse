package testWeb;

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
import testPage.RegistroPage;

import java.time.Duration;

/**
 * Clase de prueba automatizada para validar el comportamiento del registro en la aplicación Parabank.
 * Utiliza Selenium WebDriver, JUnit 5 y ExtentReports para reporting.
 */
public class RegisterTest {

    public WebDriver driver;
    public WebDriverWait wait;

    // Configuración del reporte con ExtentReports
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reportes_registro.html");
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
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        RegistroPage registerPage = new RegistroPage(driver, wait);
        registerPage.setup();
        registerPage.url("https://parabank.parasoft.com/parabank/register.htm");
    }

    /**
     * Caso de prueba: Validar registro exitoso.
     * Verifica que un usuario se registre exitosamente en la pagina.
     * Se etiqueta con @Tag("REGISTRO") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("REGISTRO")
    @DisplayName("Registro Exitoso.")
    public void testRegistro_01() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        RegistroPage registerPage = new RegistroPage(driver, wait);

        try {
            registerPage.escribirNombre("Alan");
            registerPage.escribirApellido("Fernandez");
            registerPage.escribirDireccion("Mendoza 2250");
            registerPage.escribirCiudad("Buenos Aires");
            registerPage.escribirEstado("Belgrano");
            registerPage.escribirCodigoPostal("B1707");
            registerPage.escribirCelular("1122334455");
            registerPage.escribirSSN("SsN");
            registerPage.escribirUsuario("Alan21");
            registerPage.escribirContraseña("123456");
            registerPage.escribirRecontraseña("123456");
            test.log(Status.PASS, "Se ingresaron todos los datos del Registro");

            registerPage.clickRegistrarse();

            if (registerPage.registroExitoso().equals("Your account was created successfully. You are now logged in.")) {
                test.log(Status.PASS, "Validación de Registro Exitoso");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de Registro Exitoso");
            }



        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    /**
     * Caso de prueba: Validar registro exitoso.
     * Verifica que un usuario se registre exitosamente en la pagina.
     * Se etiqueta con @Tag("REGISTRO") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("REGISTRO")
    @DisplayName("Campos vacios.")
    public void testRegistro_02() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido - Campos vacios");
        test.log(Status.INFO, "Comienza el Test");
        RegistroPage registerPage = new RegistroPage(driver, wait);

        try {
            test.log(Status.PASS, "No se ingresaron todos los datos del Registro");

            registerPage.clickRegistrarse();

            if ( (registerPage.msjNombreRequerido().equals("First name is required."))
                    && (registerPage.msjApellidoRequerido().equals("Last name is required."))
                    && (registerPage.msjDireccionRequerido().equals("Address is required."))
                    && (registerPage.msjCiudadRequerido().equals("City is required."))
                    && (registerPage.msjEstadoRequerido().equals("State is required."))
                    && (registerPage.msjCodigoPostalRequerido().equals("Zip Code is required."))
                    && (registerPage.msjSsnRequerido().equals("Social Security Number is required."))
                    && (registerPage.msjUsuarioRequerido().equals("Username is required."))
                    && (registerPage.msjContraseñaRequerido().equals("Username is required."))
                    && (registerPage.msjReContraseñaRequerido().equals("Password confirmation is required."))
            ) {
                test.log(Status.PASS, "Validación de Registro Exitoso");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje cuando faltan ingresar campos");
            }

        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    /**
     * Se ejecuta después de cada test.
     * Cierra el navegador para limpiar el entorno de prueba.
     */
    @AfterEach
    public void cerrar() {
        RegistroPage registerPage = new RegistroPage(driver, wait);
        registerPage.close();
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
