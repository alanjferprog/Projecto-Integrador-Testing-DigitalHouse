package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewAccountPage extends BasePage {

    private By btnResumenCuenta= By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    private By txtInformativo= By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    public OverviewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**
     * Hace clic en el botón de resumen de cuenta.
     */
    public void clickResumenCuenta() {
        this.click(btnResumenCuenta);
    }

    public String msjInformativo() {
        String msj= this.getText(txtInformativo);
        System.out.println("MENSAJE INFORMATIVO: " + msj);
        return msj;
    }
}
