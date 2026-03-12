package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

/**
 * @deprecated Replaced by {@link automation.task.OpenLandingPage}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 *             Use {@code automation.task.OpenLandingPage} instead.
 *
 * Task: Opens the SofkianOS landing page.
 *
 * Single Responsibility: navigates the browser to the application root URL.
 * The URL is resolved against {@code webdriver.base.url} from serenity.conf
 * via the {@code @DefaultUrl("/")} annotation on {@link KudoFormUI.LandingHomePage}.
 */
@Deprecated
public class OpenLandingPage implements Task {

    public static OpenLandingPage now() {
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
