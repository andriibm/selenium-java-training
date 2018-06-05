package task7;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FindElementListener extends AbstractWebDriverEventListener {



    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Before find by: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("After find by: " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        File tempScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempScreenshot.toPath(), new File(System.currentTimeMillis() + "screen_on_exception.png").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}