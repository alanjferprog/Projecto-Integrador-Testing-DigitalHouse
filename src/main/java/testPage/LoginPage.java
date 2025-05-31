package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Representa la página de login del sistema bajo prueba.
 * Hereda de BasePage para reutilizar métodos comunes de interacción con elementos.
 */
public class LoginPage extends BasePage{

    // Localizadores de elementos de la página
    private By username= By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By password= By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private By login= By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");
    private By credencialesNoVerificadas= By.xpath("//*[@id=\"rightPanel\"]/p");
    private By msjGenerico= By.xpath("//*[@id=\"rightPanel\"]/p");

    /**
     * Constructor de la página de login.
     * @param driver instancia del WebDriver utilizada para interactuar con el navegador.
     * @param wait instancia de WebDriverWait (no se usa directamente, ya que la clase base lo crea).
     */
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**
     * Escribe el nombre de usuario en el campo correspondiente.
     * @param usuario el nombre de usuario a ingresar.
     */
    public void escribirUsuario(String usuario) {
        this.sendText(usuario, username);
    }

    /**
     * Escribe la contraseña en el campo correspondiente.
     * @param contraseña la contraseña a ingresar.
     */
    public void escribirContraseña(String contraseña) {
        this.sendText(contraseña, password);
    }

    /**
     * Hace clic en el botón de login.
     */
    public void clickLogin() {
        this.click(login);
    }

    /**
     * Devuelve el mensaje de error mostrado cuando el login no es exitoso.
     * @return el texto del mensaje de credenciales no verificadas.
     */
    public String loginNoVerificado() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(credencialesNoVerificadas));
        return this.getText(credencialesNoVerificadas);
    }

    /**
     * Devuelve el mensaje de error mostrado cuando el login no es exitoso.
     * @return el texto del mensaje de credenciales no verificadas.
     */
    public String msjErrorGenerico() {
        System.out.println("MENSAJE DE ERROR: " + this.getText(msjGenerico));
        return this.getText(msjGenerico);
    }


}
