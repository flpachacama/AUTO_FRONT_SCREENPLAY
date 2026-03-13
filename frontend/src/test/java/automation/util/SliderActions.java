package automation.util;

import automation.ui.KudoFormUI;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public final class SliderActions {

    private SliderActions() {}

    public static void dragToEnd(Actor actor) {
        WebDriver driver = BrowserUtils.getDriverFor(actor);

        WebElement sliderTrack  = KudoFormUI.SLIDER_TRACK.resolveFor(actor);
        WebElement sliderHandle = KudoFormUI.SLIDER_HANDLE.resolveFor(actor);

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

        if (!WaitUtils.isTargetVisible(actor, KudoFormUI.SUCCESS_TOAST, 3)) {
            sliderHandle.sendKeys(Keys.ARROW_RIGHT);
        }
    }
}
