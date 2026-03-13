package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SendKudo implements Task {

    public static SendKudo now() {
        return instrumented(SendKudo.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade sliderTrack = KudoFormUI.SLIDER_TRACK.resolveFor(actor);
        WebElementFacade sliderHandle = KudoFormUI.SLIDER_HANDLE.resolveFor(actor);

        sliderTrack.waitUntilVisible();
        sliderHandle.waitUntilVisible();

        int dragDistance = Math.max(sliderTrack.getSize().getWidth() - sliderHandle.getSize().getWidth() - 2, 200);

        new Actions(BrowseTheWeb.as(actor).getDriver())
            .clickAndHold(sliderHandle)
            .moveByOffset(dragDistance, 0)
            .pause(Duration.ofMillis(150))
            .release()
            .perform();

        // Keep the keyboard fallback for environments where drag events are flaky.
        if (!waitForSuccessToast(actor)) {
            sliderHandle.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    private boolean waitForSuccessToast(Actor actor) {
        WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(8));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(KudoFormUI.SUCCESS_TOAST_XPATH)));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }
}
