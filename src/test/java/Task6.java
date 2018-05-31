import framework.admin.Admin;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;

public class Task6 {

    private WebDriver driver;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
        Admin.login(driver, "admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }

    @Test
    public void test(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.linkText("Add New Country")).click();
        List<WebElement> extLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        String litecartWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        for(WebElement e : extLinks) {
            e.click();

            //works fine without this, but just in case
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));

            Set<String> newWindows = driver.getWindowHandles();
            newWindows.removeAll(oldWindows);

            for (String newWindow : newWindows){
                driver.switchTo().window(newWindow);

                //just to see new window before closing
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("*"))));

                driver.close();
                driver.switchTo().window(litecartWindow);
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}
