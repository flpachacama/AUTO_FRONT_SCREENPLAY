package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

/**
 * @deprecated Replaced by {@link automation.task.SelectCategory}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 *
 * Task: Selects a recognition category from the dropdown.
 *
 * Single Responsibility: populates only the 'category' field.
 */
@Deprecated
public class SelectCategory implements Task {

    private final String category;

    private SelectCategory(String category) {
        this.category = category;
    }

    public static SelectCategory named(String category) {
        return new SelectCategory(category);
    }

    @Step("{0} selects the category '#category'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(category).from(KudoFormUI.CATEGORY)
        );
    }
}
