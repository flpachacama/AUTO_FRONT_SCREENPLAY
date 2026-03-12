package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

/**
 * @deprecated Replaced by {@link automation.task.SelectFromUser}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 *
 * Task: Selects the kudo sender (From) from the dropdown.
 *
 * Single Responsibility: populates only the 'from' field.
 */
@Deprecated
public class SelectFromUser implements Task {

    private final String userName;

    private SelectFromUser(String userName) {
        this.userName = userName;
    }

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
