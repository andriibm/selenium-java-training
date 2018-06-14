package Task8.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends PageBase {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart() {
        int cartCountBefore = getCartCounter();

        try {
            new Select(driver.findElement(By.name("options[Size]"))).selectByValue("Small");
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        driver.findElement(By.name("add_cart_product")).click();

        wait.until((WebDriver d) -> cartCountBefore != getCartCounter());

        int cartCountAfter = getCartCounter();

        Assert.assertEquals(cartCountAfter - cartCountBefore, 1);
        return this;
    }

    public int getCartCounter () {
        return Integer.parseInt(driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());
    }



}
