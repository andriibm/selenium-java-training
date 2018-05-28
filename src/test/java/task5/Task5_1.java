package task5;

import framework.admin.Admin;
import framework.utils.Utils;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task5_1 {

    private WebDriver driver;


    @Before
    public void start() {

        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
        Admin.login(driver, "admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
    }

    @Test
    public void test(){


        String uniqueCode = Long.toString(System.currentTimeMillis());
        String uniqueName = "RetroDuck_" + uniqueCode;

        //Click on [Add New Product] button
        driver.findElement(By.xpath("//a[@class='button' and text() = ' Add New Product']")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("RetroDuck_" + uniqueCode );
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys(uniqueCode);
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='categories[]' and @data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='product_groups[]' and @value='1-3']")).click();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String fromDate = dateFormat.format(new Date(System.currentTimeMillis() - 86500000));
        String toDate = dateFormat.format(new Date(System.currentTimeMillis() + 86500000));

        ClassLoader classLoader = getClass().getClassLoader();
        File image = new File(classLoader.getResource("retroduck.png").getFile());

        driver.findElement(By.xpath("//input[@type='file' and @name='new_images[]']")).sendKeys(image.getAbsolutePath());


        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_from']")).sendKeys(fromDate);
        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_to']")).sendKeys(toDate);


        driver.findElement(By.xpath("//a[text() = 'Information']")).click();

        new Select(driver.findElement(By.name("manufacturer_id"))).selectByIndex(1);

        driver.findElement(By.name("keywords")).sendKeys("duck");

        driver.findElement(By.name("short_description[en]")).sendKeys("rubber duck");

        driver.findElement(By.xpath("//div[@class = 'trumbowyg-editor']")).sendKeys("Description of rubber duck");

        driver.findElement(By.name("head_title[en]")).sendKeys("Head title of rubber duck");

        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta Description of rubber duck");

        driver.findElement(By.xpath("//a[text() = 'Prices']")).click();

        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("5.00");



        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByIndex(1);

        driver.findElement(By.name("save")).click();

        Assert.assertTrue(Utils.isElementPresent(driver, By.xpath(String.format("//tr[td/a/text() = '%s']/td/input", uniqueName))));

        driver.findElement(By.xpath(String.format("//tr[td/a/text() = '%s']/td/input", uniqueName))).click();

        driver.findElement(By.xpath("//button[@name='enable']")).click();


    }

    @After
    public void stop() {
        driver.quit();
    }

}
