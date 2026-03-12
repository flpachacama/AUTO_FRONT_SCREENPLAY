package automation.tasks;

import automation.tasks.DragSliderToEnd;
import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SubmitKudoWithSlider implements Task {

    public SubmitKudoWithSlider() {}

    public static SubmitKudoWithSlider now() {
        return Tasks.instrumented(SubmitKudoWithSlider.class);
    }

    @Step("{0} submits the kudo using the slider")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(KudoFormUI.SLIDER_TRACK, isVisible()).forNoMoreThan(5).seconds(),
                WaitUntil.the(KudoFormUI.SLIDER_HANDLE, isVisible()).forNoMoreThan(5).seconds(),
                DragSliderToEnd.now()
        );
    }
}