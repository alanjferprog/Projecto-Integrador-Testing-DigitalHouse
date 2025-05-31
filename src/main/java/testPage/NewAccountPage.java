package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage{

    private By btnNuevaCuenta= By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");

    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }



}
