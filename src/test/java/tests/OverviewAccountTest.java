package tests;

import Pages.LoginPage;
import Pages.OverviewAccountPage;
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
import utils.AuthHelper;

import java.time.Duration;

public class OverviewAccountTest {

    public WebDriver driver;
    public WebDriverWait wait;
    OverviewAccountPage overviewAccountPage;
    public String url= "http://localhost:8080/parabank/index.htm";

    // Configuración del reporte con ExtentReports
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reportes_overview-account.html");
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
        overviewAccountPage = new OverviewAccountPage(driver, wait);
        overviewAccountPage.setup();
        overviewAccountPage.url(url);
    }

    /**
     * Caso de prueba: Validar login exitoso.
     * Verifica que se muestre el mensaje de bienvenida al usuario.
     * Se etiqueta con @Tag("LOGIN") para facilitar su ejecución selectiva.
     */
    @Test
    @Tag("OVERVIEW-ACCOUNT")
    @DisplayName("Ver detalles de cuenta (Toda actividad, de todo tipo).")
    public void testNewAccount_01() {
        ExtentTest test = extent.createTest("Ver el resumen de cuenta exitosamente.");
        test.log(Status.INFO, "Comienzo de test ver el resumen de cuenta.");

        AuthHelper.login(driver);

        overviewAccountPage.clickResumenCuenta();
        test.log(Status.PASS, "Click en el boton de resumen de cuenta.");

        if (overviewAccountPage.msjInformativoRetenciones().equals("*Balance includes deposits that may be subject to holds")) {
            test.log(Status.PASS, "Validación de msj informativo de retenciones");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje informativo de retenciones");
        }

        overviewAccountPage.clickCuenta();
        test.log(Status.PASS, "Click en una cuenta para ver sus detalles");

        if (overviewAccountPage.msjDetalleCuenta().equals("Account Details")) {
            test.log(Status.PASS, "Validación de msj Detalle de cuenta");
        } else {
            test.log(Status.FAIL, "Fallo el mensaje Detalle de cuenta");
        }
        overviewAccountPage.clickMesActvidad();
        overviewAccountPage.clickTodaActividad();
        overviewAccountPage.clickTipoActvidad();
        overviewAccountPage.clickTodoTipoActividad();
        test.log(Status.PASS, "Se selecciono el mes y el tipo de actividad");

        overviewAccountPage.clickIr();
        test.log(Status.PASS, "Se hizo click en IR.");
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
