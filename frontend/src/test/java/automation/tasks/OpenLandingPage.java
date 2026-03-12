package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenLandingPage implements Task {

    public OpenLandingPage() {}

    public static OpenLandingPage open() {
        return Tasks.instrumented(OpenLandingPage.class);
    }

    @Step("{0} opens the SofkianOS landing page")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(new KudoFormUI.LandingHomePage())
        );
    }
}