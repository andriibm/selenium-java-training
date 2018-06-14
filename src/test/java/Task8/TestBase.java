package Task8;

import Task8.app.Application;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    private WebDriver driver;
    protected Application app;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        app = new Application(driver);
    }



    @After
    public void stop() {
        driver.quit();
    }


}
