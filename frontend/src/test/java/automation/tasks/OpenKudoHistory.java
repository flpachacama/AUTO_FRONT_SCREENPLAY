package automation.tasks;

import automation.ui.KudoHistoryUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenKudoHistory implements Task {

    public static OpenKudoHistory now() {
        return instrumented(OpenKudoHistory.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(KudoHistoryUI.HISTORY_BUTTON),
            WaitUntil.the(KudoHistoryUI.HISTORY_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
