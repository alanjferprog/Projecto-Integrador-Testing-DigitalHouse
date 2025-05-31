package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage{

    private By btnNuevaCuenta= By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private By tipoCuenta= By.id("type");
    private By btnAbrirNuevaCuenta= By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");
    private By txtCuentaCreada= By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**
     * Hace clic en el botón de nueva cuenta.
     */
    public void clickNuevaCuenta() {
        this.click(btnNuevaCuenta);
    }

    /**
     * Selecciona la opción 'SAVINGS' en el tipo de cuenta.
     */
    public void seleccionarCuenta() {
        this.click(tipoCuenta);
        this.sendKey(Keys.ARROW_DOWN, tipoCuenta);
        this.sendKey(Keys.ENTER, tipoCuenta);
    }

    /**
     * Hace clic en el botón de abrir nueva cuenta.
     */
    public void clickAbrirCuenta() {
        this.click(btnAbrirNuevaCuenta);
    }

    /**
     * Devuelve el mensaje de exito cuando abrimos una cuenta.
     * @return el texto del mensaje de cuenta creada con exito.
     */
    public String msjCuentaCreada() {
        System.out.println("MENSAJE DE EXITO: " + this.getText(txtCuentaCreada));
        return this.getText(txtCuentaCreada);
    }


}
