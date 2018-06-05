package task7;
import framework.admin.Admin;
import framework.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Task7 {

    private EventFiringWebDriver edr;
    private RemoteWebDriver rwd;



    @Before
    public void start() throws IOException {

        rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        edr = new EventFiringWebDriver(rwd);
        edr.register(new FindElementListener());

        edr.get("http://localhost/litecart/admin/");
        Admin.login(edr, "admin", "admin");


    }


    @Test
    public void testMethod(){
        List<WebElement> items;
        items = edr.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        System.out.println(items.size());
        for (int i = 1; i <= items.size(); i++){
            edr.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li[%d]", i))).click();
            List<WebElement> subItems = edr.findElements(By.xpath("//ul[@id='box-apps-menu']/li/ul/li"));
            if(subItems.size() != 0){
                WebElement subItem;
                for (int j = 1; j <= subItems.size(); j++) {
                    subItem = edr.findElement(By.xpath(String.format("//ul[@id='box-apps-menu']/li/ul/li[%d]", j)));
                    String text = subItem.getText();
                    subItem.click();
                    Assert.assertTrue(Utils.isElementPresent(edr, By.xpath("//h1")));
                }
            } else {
                Assert.assertTrue(Utils.isElementPresent(edr, By.xpath("//h1")));
            }

        }

    }

    @After
    public void stop() {
        edr.quit();
    }

}
