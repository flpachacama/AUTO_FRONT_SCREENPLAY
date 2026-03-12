package automation.util;

import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

/**
 * Utility class that encapsulates all slider-specific interaction logic.
 *
 * Keeping this code here (rather than inside a Task) means:
 * <ul>
 *   <li>Tasks stay declarative and easy to read.</li>
 *   <li>The drag/fallback mechanic can be reused by any Task or Interaction
 *       without copy-pasting Selenium {@link Actions} code.</li>
 * </ul>
 *
 * The algorithm mirrors the original POM implementation exactly:
 * <ol>
 *   <li>Compute drag distance = track width − handle width (minimum 200 px).</li>
 *   <li>Click-and-hold the handle, drag right, release.</li>
 *   <li>If the success toast does not appear within 3 s, send {@code ARROW_RIGHT}
 *       as a keyboard fallback (useful in headless environments).</li>
 * </ol>
 */
public final class SliderActions {

    /** Locator for the slider track container. */
    private static final By SLIDER_TRACK_LOCATOR =
            By.cssSelector("div[class*='cursor-pointer'][class*='rounded-full']");

    /** Locator for the draggable slider handle. */
    private static final By SLIDER_HANDLE_LOCATOR =
            By.cssSelector("div[class*='w-16'][class*='bg-brand']");

    /** Locator used for the keyboard-fallback check. */
    private static final By SUCCESS_LOCATOR =
            By.xpath("//*[contains(text(),'Kudo enviado')]");

    private SliderActions() {
        // Utility class — no instantiation
    }

    /**
     * Drags the submit slider from its rest position all the way to the right,
     * triggering kudo submission.  Falls back to a keyboard nudge when the
     * mouse-drag does not produce the expected success toast.
     *
     * @param actor the Screenplay actor whose {@code BrowseTheWeb} ability
     *              provides the underlying WebDriver session
     */
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

        // Keyboard fallback — fires only when the drag did not trigger submission
        if (!WaitUtils.isElementVisible(driver, SUCCESS_LOCATOR, 3)) {
            sliderHandle.sendKeys(Keys.ARROW_RIGHT);
        }
    }
}
