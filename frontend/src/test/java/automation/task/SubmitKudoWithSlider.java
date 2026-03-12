package automation.task;

import automation.ui.KudoFormUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Task: Waits for the submit slider to be ready, then drags it to trigger
 * kudo submission.
 *
 * Single Responsibility: handles only the slider-based form submission.
 * <ul>
 *   <li>Waits for the slider track and handle to be visible.</li>
 *   <li>Delegates the physical drag gesture to {@link DragSliderToEnd}.</li>
 * </ul>
 * All locators live in {@link KudoFormUI}. All drag logic lives in
 * {@link automation.util.SliderActions}.
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(SubmitKudoWithSlider.now());
 * </pre>
 */
public class SubmitKudoWithSlider implements Task {

    private SubmitKudoWithSlider() {}

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
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
