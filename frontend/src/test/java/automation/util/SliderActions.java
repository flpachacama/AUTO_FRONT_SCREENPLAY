package automation.util;

import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public final class SliderActions {

    private static final By SLIDER_TRACK_LOCATOR =
            By.cssSelector("div[class*='cursor-pointer'][class*='rounded-full']");

    private static final By SLIDER_HANDLE_LOCATOR =
            By.cssSelector("div[class*='w-16'][class*='bg-brand']");

    private static final By SUCCESS_LOCATOR =
            By.xpath("//*[contains(text(),'Kudo enviado')]");

    private SliderActions() {}

    public static void dragToEnd(Actor actor) {
        WebDriver driver = BrowserUtils.getDriverFor(actor);

        WebElement sliderTrack  = driver.findElement(SLIDER_TRACK_LOCATOR);
        WebElement sliderHandle = driver.findElement(SLIDER_HANDLE_LOCATOR);

        int dragDistance = Math.max(
                sliderTrack.getSize().getWidth() - sliderHandle.getSize().getWidth() - 2,
                200
        );

        new Actions(driver)
                .clickAndHold(sliderHandle)
                .moveByOffset(dragDistance, 0)
                .pause(Duration.ofMillis(150))
                .release()
                .perform();

        if (!WaitUtils.isElementVisible(driver, SUCCESS_LOCATOR, 3)) {
            sliderHandle.sendKeys(Keys.ARROW_RIGHT);
        }
    }
}
