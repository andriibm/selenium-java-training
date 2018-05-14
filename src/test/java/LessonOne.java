import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LessonOne {

    @Test
    public void runChromeBrowser() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }

    @Test
    public void runIEBrowser(){
        InternetExplorerDriverManager.getInstance().setup();
        WebDriver driver = new InternetExplorerDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }
    @Test
    public void runFirefoxBrowser(){
        FirefoxDriverManager.getInstance().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }


}
