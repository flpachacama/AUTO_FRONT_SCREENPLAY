package automation.util;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private WaitUtils() {}

    public static boolean isElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }


    public static boolean isElementInvisible(WebDriver driver, By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public static boolean isElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public static boolean isTargetVisible(Actor actor, Target target, int timeoutSeconds) {
        try {
            WebDriver driver = BrowserUtils.getDriverFor(actor);
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ignored -> target.resolveFor(actor).isVisible());
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }
}
