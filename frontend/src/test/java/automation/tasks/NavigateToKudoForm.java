package automation.tasks;

import automation.ui.KudoFormUI;
import automation.ui.LandingPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToKudoForm implements Task {

    public static NavigateToKudoForm now() {
        return instrumented(NavigateToKudoForm.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(LandingPageUI.ACCESS_BUTTON),
            WaitUntil.the(KudoFormUI.KUDO_FORM_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
