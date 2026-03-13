package automation.tasks;

import automation.ui.KudoHistoryUI;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewKudoHistory implements Task {

    public static ViewKudoHistory now() {
        return instrumented(ViewKudoHistory.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(KudoHistoryUI.HISTORY_FILTER_SECTION, isVisible()).forNoMoreThan(10).seconds(),
            WaitUntil.the(KudoHistoryUI.REFRESH_BUTTON, isClickable()).forNoMoreThan(10).seconds()
        );

        WebElementFacade refreshButton = KudoHistoryUI.REFRESH_BUTTON.resolveFor(actor);
        if (refreshButton.isCurrentlyVisible()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
            jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", refreshButton);
            try {
                actor.attemptsTo(Click.on(KudoHistoryUI.REFRESH_BUTTON));
            } catch (RuntimeException clickFailure) {
                jsExecutor.executeScript("arguments[0].click();", refreshButton);
            }
        }
    }
}
