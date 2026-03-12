package automation.task;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Task: Clicks the 'Acceder' button on the landing page and waits until
 * the Kudos form title is fully visible.
 *
 * Single Responsibility: navigation from landing page to Kudos form.
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(NavigateToKudosForm.now());
 * </pre>
 */
public class NavigateToKudosForm implements Task {

    private NavigateToKudosForm() {}

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
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
