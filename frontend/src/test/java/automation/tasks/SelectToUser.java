package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

/**
 * @deprecated Replaced by {@link automation.task.SelectToUser}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 *
 * Task: Selects the kudo recipient (To) from the dropdown.
 *
 * Single Responsibility: populates only the 'to' field.
 */
@Deprecated
public class SelectToUser implements Task {

    private final String userName;

    private SelectToUser(String userName) {
        this.userName = userName;
    }

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
