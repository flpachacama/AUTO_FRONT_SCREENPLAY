package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class SelectToUser implements Task {

    private final String userName;

    public SelectToUser(String userName) {
        this.userName = userName;
    }

    public static SelectToUser named(String userName) {
        return Tasks.instrumented(SelectToUser.class, userName);
    }

    @Step("{0} selects '#userName' as the kudo recipient")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(userName).from(KudoFormUI.TO_USER)
        );
    }
}