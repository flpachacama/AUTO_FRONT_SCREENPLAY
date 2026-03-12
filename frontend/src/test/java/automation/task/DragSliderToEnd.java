package automation.task;

import automation.util.SliderActions;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

/**
 * Interaction: Drags the submit slider from its rest position all the way to
 * the right, triggering kudo submission.
 *
 * This is an Interaction (low-level browser gesture) that delegates the
 * physical drag-and-fallback algorithm to {@link SliderActions#dragToEnd(Actor)}.
 *
 * <p>Responsibility pyramid:</p>
 * <ul>
 *   <li>{@link SubmitKudoWithSlider} — high-level Task (waits + delegates)</li>
 *   <li>{@link DragSliderToEnd}      — low-level Interaction (one gesture)</li>
 *   <li>{@link SliderActions}         — utility holding the raw Selenium code</li>
 * </ul>
 *
 * <p>Usage:</p>
 * <pre>
 *   actor.attemptsTo(DragSliderToEnd.now());
 * </pre>
 */
public class DragSliderToEnd implements Interaction {

    private DragSliderToEnd() {}

    /** Factory method — reads naturally inside {@code attemptsTo(...)}. */
    public static DragSliderToEnd now() {
        return new DragSliderToEnd();
    }

    @Step("{0} drags the slider to the end to submit the kudo")
    @Override
    public <T extends Actor> void performAs(T actor) {
        SliderActions.dragToEnd(actor);
    }
}
