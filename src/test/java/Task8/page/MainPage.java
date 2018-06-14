package Task8.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends PageBase {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private boolean isOnThisPage() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Checkout »")));
        return isElementPresent(By.linkText("Checkout »"));
    }

    public MainPage open() {
        if (!isOnThisPage()) {
            driver.get("http://localhost/litecart/");
        }

        return this;
    }

    public MainPage openProductByNum(int prodNum){
        open();
        driver.findElements(By.xpath("//div[@id='box-latest-products']//a[@class='link']")).get(prodNum - 1).click();
        return this;
    }

    public void checkThatZeroItemsInCart(){
        open();
        Assert.assertEquals("0", driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());
    }
}
