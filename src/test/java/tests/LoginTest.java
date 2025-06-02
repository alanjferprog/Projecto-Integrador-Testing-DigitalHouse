package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;
import Pages.LoginPage;

import java.time.Duration;


/**
 * Clase de prueba automatizada para validar el comportamiento del login en la aplicación Parabank.
 * Utiliza Selenium WebDriver, JUnit 5 y ExtentReports para reporting.
 */
public class LoginTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    String url= "http://localhost:8080/parabank/index.htm";

    // Configuración del reporte con ExtentReports
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reportes_login.html");
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
        loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.url(url);
    }

    /**
     * Caso de prueba: Validar login exitoso.
     * Verifica que se muestre el mensaje de bienvenida al usuario.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("LOGIN")
    @DisplayName("Login Exitoso.")
    public void testLogin_01() {
        ExtentTest test = extent.createTest("Login Exitoso");
        test.log(Status.INFO, "Comienzo de test de login");

        loginPage.escribirUsuario("user18");
        loginPage.escribirContraseña("StrongPass123");
        test.log(Status.PASS, "Datos del login ingresados");

        loginPage.clickLogin();

        if (loginPage.loginExitoso().equals("Welcome John Smith")) {
            test.log(Status.PASS, "Validación de Login Exitoso");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje de Login Exitoso");
        }
    }

    /**
     * Caso de prueba: Validar login fallido con todos los campos incompletos.
     * Verifica que se muestre el mensaje de error esperado.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("LOGIN")
    @DisplayName("Campos incompletos.")
    public void testLogin_02() {
        ExtentTest test = extent.createTest("Login Fallido - Campos incompletos");
        test.log(Status.INFO, "Comienzo de test de login");

        test.log(Status.PASS, "Datos del login no fueron completados");

        loginPage.clickLogin();

        Assertions.assertEquals(loginPage.msjErrorGenerico(), "Please enter a username and password.");
        test.log(Status.PASS, "Se valida el Login Fallido");
    }


    /**
     * Caso de prueba: Validar login fallido con campo de usuario vacio.
     * Verifica que se muestre el mensaje de error esperado.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("LOGIN")
    @DisplayName("Username Vacio.")
    public void testLogin_03() {
        ExtentTest test = extent.createTest("Login Fallido - Username vacio");
        test.log(Status.INFO, "Comienzo de test de login");

        loginPage.escribirContraseña("123456");
        test.log(Status.PASS, "Se completa solamente la contraseña");

        loginPage.clickLogin();

        Assertions.assertEquals(loginPage.msjErrorGenerico(), "Please enter a username and password.");
        test.log(Status.PASS, "Se valida el Login Fallido");
    }

    /**
     * Caso de prueba: Validar login fallido con campo de contraseña vacio.
     * Verifica que se muestre el mensaje de error esperado.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("LOGIN")
    @DisplayName("Password vacia.")
    public void testLogin_04() {
        ExtentTest test = extent.createTest("Login Fallido - Password vacia");
        test.log(Status.INFO, "Comienzo de test de login");

        loginPage.escribirUsuario("Alan");
        test.log(Status.PASS, "Se completa solamente el usuario");

        loginPage.clickLogin();

        Assertions.assertEquals(loginPage.msjErrorGenerico(), "Please enter a username and password.");
        test.log(Status.PASS, "Se valida el Login Fallido");
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
