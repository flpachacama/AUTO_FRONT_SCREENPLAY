package automation.tasks;

import automation.ui.KudoFormUI;
import automation.util.KudoData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillKudoForm implements Task {

    private final KudoData kudoData;

    public FillKudoForm(KudoData kudoData) {
        this.kudoData = kudoData;
    }

    public static FillKudoForm withData(KudoData kudoData) {
        return instrumented(FillKudoForm.class, kudoData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            SelectFromOptions.byVisibleText(kudoData.from()).from(KudoFormUI.FROM_SELECT),
            SelectFromOptions.byVisibleText(kudoData.to()).from(KudoFormUI.TO_SELECT),
            SelectFromOptions.byVisibleText(kudoData.category()).from(KudoFormUI.CATEGORY_SELECT),
            Enter.theValue(kudoData.message()).into(KudoFormUI.MESSAGE_TEXTAREA)
        );
    }
}
