package automation.tasks;

import automation.task.DragSliderToEnd;
import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @deprecated Replaced by {@link automation.task.SubmitKudoWithSlider}.
 *             This class belongs to the legacy {@code automation.tasks} package.
 */
@Deprecated
public class SubmitKudoWithSlider implements Task {

    public static SubmitKudoWithSlider now() {
        return new SubmitKudoWithSlider();
    }

    @Step("{0} submits the kudo using the slider")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(KudoFormUI.SLIDER_TRACK,  isVisible()).forNoMoreThan(5).seconds(),
                WaitUntil.the(KudoFormUI.SLIDER_HANDLE, isVisible()).forNoMoreThan(5).seconds(),
                DragSliderToEnd.now()
        );
    }
}
