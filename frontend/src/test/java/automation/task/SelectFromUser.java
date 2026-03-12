package automation.task;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

/**
 * Task: Selects the kudo sender (From) from the dropdown.
 *
 * Single Responsibility: populates only the 'from' (sender) field.
 * All locators are kept in {@link KudoFormUI}.
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(SelectFromUser.named("Christopher Pallo"));
 * </pre>
 */
public class SelectFromUser implements Task {

    private final String userName;

    private SelectFromUser(String userName) {
        this.userName = userName;
    }

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
    public static SelectFromUser named(String userName) {
        return new SelectFromUser(userName);
    }

    @Step("{0} selects '#userName' as the kudo sender")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(userName).from(KudoFormUI.FROM_USER)
        );
    }
}
