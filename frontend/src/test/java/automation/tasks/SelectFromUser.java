package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class SelectFromUser implements Task {

    private final String userName;

    public SelectFromUser(String userName) {
        this.userName = userName;
    }

    public static SelectFromUser named(String userName) {
        return Tasks.instrumented(SelectFromUser.class, userName);
    }

    @Step("{0} selects '#userName' as the kudo sender")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(userName).from(KudoFormUI.FROM_USER)
        );
    }
}