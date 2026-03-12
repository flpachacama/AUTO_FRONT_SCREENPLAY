package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

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