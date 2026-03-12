package automation.task;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

/**
 * Task: Selects the kudo recipient (To) from the dropdown.
 *
 * Single Responsibility: populates only the 'to' (recipient) field.
 * All locators are kept in {@link KudoFormUI}.
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(SelectToUser.named("Santiago"));
 * </pre>
 */
public class SelectToUser implements Task {

    private final String userName;

    private SelectToUser(String userName) {
        this.userName = userName;
    }

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
    public static SelectToUser named(String userName) {
        return new SelectToUser(userName);
    }

    @Step("{0} selects '#userName' as the kudo recipient")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(userName).from(KudoFormUI.TO_USER)
        );
    }
}
