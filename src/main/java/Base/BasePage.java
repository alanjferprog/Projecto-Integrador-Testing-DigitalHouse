package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Clase base para todas las páginas del framework de automatización.
 * Proporciona métodos comunes de interacción con elementos utilizando Selenium y el patrón Page Object.
 */
public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    /**
     * Constructor protegido para inicializar el driver y el WebDriverWait con timeout de 3 segundos.
     * @param driver instancia del navegador a usar.
     * @param wait instancia de WebDriverWait (no se utiliza directamente, se reemplaza).
     */
    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(3000));
    }

    /**
     * Maximiza la ventana del navegador.
     */
    public void setup() {
        driver.manage().window().maximize();
    }

    /**
     * Navega a la URL especificada.
     * @param url dirección web a la que se desea acceder.
     */
    public void url(String url) {
        driver.get(url);
    }

    /**
     * Cierra el navegador y termina la sesión del WebDriver.
     */
    public void close() {
        driver.quit();
    }

    /**
     * Encuentra un elemento en la página utilizando un locator.
     * @param locator localizador del elemento (ej. By.id, By.xpath, etc).
     * @return el WebElement encontrado.
     */
    protected WebElement findElement(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Envía texto a un campo de entrada luego de esperar que esté presente.
     * @param inputText texto a ingresar.
     * @param locator localizador del elemento.
     */
    protected void sendText(String inputText, By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.clear();
        element.sendKeys(inputText);
    }

    /**
     * Envía una tecla (como ENTER o TAB) al campo localizado.
     * @param key tecla a enviar.
     * @param locator localizador del elemento.
     */
    protected void sendKey(CharSequence key, By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(key);
    }

    /**
     * Hace clic sobre un elemento luego de esperar que esté clickeable.
     * @param locator localizador del elemento.
     */
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.click();
    }

    /**
     * Obtiene el texto visible de un elemento luego de esperar que esté presente.
     * @param locator localizador del elemento.
     * @return texto del elemento.
     */
    protected String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.getText();
    }
}