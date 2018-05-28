import framework.admin.Admin;
import framework.utils.Utils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class LessonTwo {

    private WebDriver driver;



    @Before
    public void start() {

        ChromeOptions opt = new ChromeOptions();
//        opt.setCapability("unexpectedAlert");
        opt.setHeadless(false);
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
        Admin.login(driver, "admin", "admin");


    }


    @Test
    public void testMethod(){
        List<WebElement> items;
        items = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        System.out.println(items.size());
        for (int i = 1; i <= items.size(); i++){
            driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li[%d]", i))).click();
            List<WebElement> subItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));
            if(subItems.size() != 0){
                WebElement subItem;
                for (int j = 1; j <= subItems.size(); j++) {
                    subItem = driver.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li/ul/li[%d]", j)));
                    String text = subItem.getText();
                    subItem.click();
                    Assert.assertTrue(Utils.isElementPresent(driver, By.xpath("//h1")));
                }
            } else {
                Assert.assertTrue(Utils.isElementPresent(driver, By.xpath("//h1")));
            }

        }

    }

    @After
    public void stop() {
        driver.quit();
    }

}
