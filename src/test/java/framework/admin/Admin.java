package framework.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Admin {

    public static void login(WebDriver driver, String login, String password){
        WebElement element = driver.findElement(By.name("username"));
        element.clear();
        element.sendKeys(login);
        element = driver.findElement(By.name("password"));
        element.clear();
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.click();
    }

}
