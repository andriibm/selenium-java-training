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


public class Task4 {

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

        //Get Product Name on Main Page (of 1st Product in Campaigns section)
        mainProductName = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]/a/div[@class='name']")).getText();

        //Get Regular and Discount Price Elements on Main Page (of 1st Product in Campaigns section)
        mainRegularPriceElement = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]/a/div/s[@class='regular-price']"));
        mainDiscountPriceElement = driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li/a/div/strong[@class='campaign-price']"));

        //Get Regular and Discount Price Text on Main Page (of 1st Product in Campaigns section)
        mainRegularPrice = mainRegularPriceElement.getText();
        mainDiscountPrice = mainDiscountPriceElement.getText();

        //Regular Price
        //Is Regular Price GREY on Main Page (of 1st Product in Campaigns section)
        Assert.assertEquals("rgba(119, 119, 119, 1)", mainRegularPriceElement.getCssValue("color"));

        //Is Regular Price STRIKE on Main Page (of 1st Product in Campaigns section)
        Assert.assertTrue(mainRegularPriceElement.getCssValue("text-decoration").contains("line-through"));

        //Discount Price
        //Is Discount Price RED on Main Page (of 1st Product in Campaigns section)
        Assert.assertEquals("rgba(204, 0, 0, 1)", mainDiscountPriceElement.getCssValue("color"));

        //Is Discount Price BOLD on Main Page (of 1st Product in Campaigns section)
        Assert.assertEquals("700", mainDiscountPriceElement.getCssValue("font-weight"));

        //Click 1st Product in Campaigns section on Main Page
        driver.findElement(By.xpath("//div[@id='box-campaigns']/div/ul/li[1]")).click();

        //Get Product Name on Item Page
        itemProductName = driver.findElement(By.xpath("//h1[@class='title']")).getText();

        //Get Regular and Discount Price Element on Item Page
        itemRegularPriceElement = driver.findElement(By.xpath("//s[@class='regular-price']"));
        itemDiscountPriceElement = driver.findElement(By.xpath("//strong[@class='campaign-price']"));

        //Get Regular and Discount Price Text on Item Page
        itemRegularPrice = itemRegularPriceElement.getText();
        itemDiscountPrice = itemDiscountPriceElement.getText();

        //Is Product Name equal on both pages?
        Assert.assertEquals(mainProductName, itemProductName);

        //Is Regular Price GRAY on Item Page?
        Assert.assertEquals("rgba(102, 102, 102, 1)", itemRegularPriceElement.getCssValue("color"));

        //Is Regular Price STRIKE on Item Page?
        Assert.assertTrue(itemRegularPriceElement.getCssValue("text-decoration").contains("line-through"));

        //Is Discount Price RED on Item Page?
        Assert.assertEquals("rgba(204, 0, 0, 1)", itemDiscountPriceElement.getCssValue("color"));

        //Is Discount Price BOLD on Item Page?
        Assert.assertEquals("700", itemDiscountPriceElement.getCssValue("font-weight"));

        //Are Regular and Discount prices equal on both pages?
        Assert.assertTrue(mainRegularPrice.equals(itemRegularPrice));
        Assert.assertTrue(mainDiscountPrice.equals(itemDiscountPrice));



    }

    @After
    public void stop() {
        driver.quit();
    }

}
