package automation.tasks;

import automation.ui.KudoHistoryUI;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewKudoHistory implements Task {

    public static ViewKudoHistory now() {
        return instrumented(ViewKudoHistory.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(KudoHistoryUI.KUDO_LIST_CONTAINER, isVisible()).forNoMoreThan(10).seconds()
        );

        WebElementFacade refreshButton = KudoHistoryUI.REFRESH_BUTTON.resolveFor(actor);
        if (refreshButton.isCurrentlyVisible()) {
            actor.attemptsTo(Click.on(KudoHistoryUI.REFRESH_BUTTON));
        }
    }
}
