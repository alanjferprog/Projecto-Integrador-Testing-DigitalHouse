package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewAccountPage extends BasePage {

    private By btnResumenCuenta= By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    private By txtInformativoRetenciones = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private By cuenta= By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private By txtDetallesCuenta= By.xpath("//*[@id=\"accountDetails\"]/h1");
    private By btnMesActividad= By.id("month");
    private By btnActividadALL= By.xpath("//*[@id=\"month\"]/option[1]");
    private By btnTipoActividad= By.id("transactionType");
    private By btnTipoALL= By.xpath("//*[@id=\"transactionType\"]/option[1]");
    private By btnIr= By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input");

    public OverviewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**
     * Hace clic en el botón de resumen de cuenta.
     */
    public void clickResumenCuenta() {
        this.click(btnResumenCuenta);
    }

    public String msjInformativoRetenciones() {
        String msj= this.getText(txtInformativoRetenciones);
        System.out.println("MENSAJE INFORMATIVO: " + msj);
        return msj;
    }

    public void clickCuenta() {
        this.click(cuenta);
    }

    public String msjDetalleCuenta() {
        String msj= this.getText(txtDetallesCuenta);
        System.out.println("MENSAJE DETALLE DE CUENTA: " + msj);
        return msj;
    }

    public void clickMesActvidad() {
        this.click(btnMesActividad);
    }

    public void clickTodaActividad() {
        this.click(btnActividadALL);
    }

    public void clickTipoActvidad() {
        this.click(btnTipoActividad);
    }

    public void clickTodoTipoActividad() {
        this.click(btnTipoALL);
    }

    public void clickIr() {
        this.click(btnIr);
    }
}
