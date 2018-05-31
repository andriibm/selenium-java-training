package task5;

import framework.utils.Utils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class task5_2 {

    private WebDriver driver;


    @Before
    public void start() {

        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void test(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 0; i < 3; i++) {
            driver.findElements(By.xpath("//div[@id='box-latest-products']//a[@class='link']")).get(i).click();

            int cartCountBefore = Integer.parseInt(driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());

            try {
                new Select(driver.findElement(By.name("options[Size]"))).selectByValue("Small");
            } catch (org.openqa.selenium.NoSuchElementException e) {

            }

            driver.findElement(By.name("add_cart_product")).click();

            wait.until((WebDriver d) -> cartCountBefore != Integer.parseInt(d.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText()));

            int cartCountAfter = Integer.parseInt(driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());

            Assert.assertEquals(cartCountAfter - cartCountBefore, 1);

            driver.get("http://localhost/litecart/");
        }

        driver.findElement(By.linkText("Checkout »")).click();

        while (!Utils.isElementPresent(driver, By.linkText("<< Back"))){
            WebElement table = driver.findElement(By.xpath("//table[@class]"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }

        driver.findElement(By.linkText("<< Back")).click();

        Assert.assertEquals("0", driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());


    }

    @After
    public void stop() {
        driver.quit();
    }

}
