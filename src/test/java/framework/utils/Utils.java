package framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Utils {

    public static boolean isElementPresent(WebDriver driver, By by) {
        return   driver.findElements(by).size() > 0;
    }

}
