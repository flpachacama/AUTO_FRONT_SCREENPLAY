package automation.task;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

/**
 * Task: Opens the SofkianOS landing page.
 *
 * Single Responsibility: navigates the browser to the application root URL.
 * The URL is resolved against {@code webdriver.base.url} in serenity.conf
 * via the {@code @DefaultUrl("/")} annotation on {@link KudoFormUI.LandingHomePage}.
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(OpenLandingPage.open());
 * </pre>
 */
public class OpenLandingPage implements Task {

    private OpenLandingPage() {}

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
    public static OpenLandingPage open() {
        return new OpenLandingPage();
    }

    @Step("{0} opens the SofkianOS landing page")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(new KudoFormUI.LandingHomePage())
        );
    }
}
