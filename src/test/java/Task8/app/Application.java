package Task8.app;

import Task8.Task8;
import Task8.page.CartPage;
import Task8.page.MainPage;
import Task8.page.ProductPage;
import org.openqa.selenium.WebDriver;

public class Application {

    private WebDriver driver;

    public MainPage mainPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public Application(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }
}
