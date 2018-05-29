package framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils {

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElements(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
