package Task8.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends PageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private boolean isOnThisPage() {
       return isElementPresent(By.id("checkout-cart-wrapper"));
    }


    public CartPage open() {
        if (!isOnThisPage()) {
            driver.get("http://localhost/litecart/en/checkout");
        }
        return this;
    }

    public CartPage removeAllProducts() {
        open();
        while (!isElementPresent(By.linkText("<< Back"))){
            WebElement table = driver.findElement(By.xpath("//table[@class]"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
        return this;
    }


}
