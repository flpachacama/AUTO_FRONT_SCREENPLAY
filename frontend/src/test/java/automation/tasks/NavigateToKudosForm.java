package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToKudosForm implements Task {

    private NavigateToKudosForm() {}

    public static NavigateToKudosForm now() {
        return new NavigateToKudosForm();
    }

    @Step("{0} navigates to the Kudos form")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(KudoFormUI.LANDING_ACCESS_BUTTON),
                WaitUntil.the(KudoFormUI.KUDOS_FORM_TITLE, isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }
}