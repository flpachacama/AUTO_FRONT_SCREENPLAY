package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;

public class EnterKudoMessage implements Task {

    private final String message;

    private EnterKudoMessage(String message) {
        this.message = message;
    }

    public static EnterKudoMessage saying(String message) {
        return new EnterKudoMessage(message);
    }

    @Step("{0} enters the kudo message")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Clear.field(KudoFormUI.MESSAGE),
                Enter.theValue(message).into(KudoFormUI.MESSAGE)
        );
    }
}