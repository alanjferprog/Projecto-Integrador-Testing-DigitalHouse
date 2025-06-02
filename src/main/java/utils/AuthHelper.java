package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthHelper {

    public static void registerInLocal(WebDriver driver) {
        driver.get("http://localhost:8080/parabank/register.htm");

        driver.findElement(By.id("customer.firstName")).sendKeys("Alan");
        driver.findElement(By.id("customer.lastName")).sendKeys("Fernandez");
        driver.findElement(By.id("customer.address.street")).sendKeys("Calle 123");
        driver.findElement(By.id("customer.address.city")).sendKeys("Ciudad 12");
        driver.findElement(By.id("customer.address.state")).sendKeys("Estado 13");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("4321");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("87654321");
        driver.findElement(By.id("customer.ssn")).sendKeys("123");
        driver.findElement(By.id("customer.username")).sendKeys("user18");
        driver.findElement(By.id("customer.password")).sendKeys("StrongPass123");
        driver.findElement(By.id("repeatedPassword")).sendKeys("StrongPass123");

        driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
    }

    public static void login(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input")).sendKeys("user18");
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input")).sendKeys("StrongPass123");
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input")).click();
    }


}
