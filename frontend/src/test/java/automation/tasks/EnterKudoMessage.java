package automation.tasks;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;

/**
 * @deprecated Replaced by {@link automation.task.EnterKudoMessage}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 *
 * Task: Clears the message textarea and types the kudo message.
 *
 * Single Responsibility: populates only the 'message' field.
 * The field is cleared first to prevent appending to any pre-existing content.
 */
@Deprecated
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
