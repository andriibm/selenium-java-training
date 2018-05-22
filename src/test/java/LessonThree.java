import framework.admin.Admin;
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


public class LessonThree {

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
    public void testMethod(){
        String mainProductName;
        String itemProductName;
        String mainDiscountPrice;
        String itemDiscountPrice;
        String mainRegularPrice;
        String itemRegularPrice;
        WebElement mainRegularPriceElement;
        WebElement mainDiscountPriceElement;
        WebElement itemRegularPriceElement;
        WebElement itemDiscountPriceElement;


        Assert.assertTrue(Utils.isElementPresent(driver, By.xpath("//div[@id='box-campaigns']/div/ul/li")));

        mainProductName = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]/a/div[@class='name']")).getText();

        Assert.assertTrue(Utils.isElementPresent(driver, By.xpath("//div[@id='box-campaigns']/div/ul/li[1]/a/div/s[@class='regular-price']")));

        mainRegularPriceElement = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]/a/div/s[@class='regular-price']"));
        mainDiscountPriceElement = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li/a/div/strong[@class='campaign-price']"));
        mainRegularPrice = mainRegularPriceElement.getText();
        mainDiscountPrice = mainDiscountPriceElement.getText();



        Assert.assertEquals("rgba(119, 119, 119, 1)", mainRegularPriceElement.getCssValue("color"));
        Assert.assertTrue(mainRegularPriceElement.getCssValue("text-decoration").contains("line-through"));

        Assert.assertEquals("rgba(204, 0, 0, 1)", mainDiscountPriceElement.getCssValue("color"));
        Assert.assertEquals("700", mainDiscountPriceElement.getCssValue("font-weight"));



        driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]")).click();

        itemProductName = driver.findElement(By.xpath("//h1[@class='title']")).getText();
        itemRegularPriceElement = driver.findElement(By.xpath("//s[@class='regular-price']"));
        itemDiscountPriceElement = driver.findElement(By.xpath("//strong[@class='campaign-price']"));
        itemRegularPrice = itemRegularPriceElement.getText();
        itemDiscountPrice = itemDiscountPriceElement.getText();

        Assert.assertEquals(mainProductName, itemProductName);

        Assert.assertEquals("rgba(102, 102, 102, 1)", itemRegularPriceElement.getCssValue("color"));
        Assert.assertTrue(itemRegularPriceElement.getCssValue("text-decoration").contains("line-through"));

        Assert.assertEquals("rgba(204, 0, 0, 1)", itemDiscountPriceElement.getCssValue("color"));
        Assert.assertEquals("700", itemDiscountPriceElement.getCssValue("font-weight"));

        Assert.assertTrue(mainRegularPrice.equals(itemRegularPrice));
        Assert.assertTrue(mainDiscountPrice.equals(itemDiscountPrice));



    }

    @After
    public void stop() {
        driver.quit();
    }

}
