package automation.tasks;

import automation.util.SliderActions;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class DragSliderToEnd implements Interaction {

    public DragSliderToEnd() {}

    public static DragSliderToEnd now() {
        return Instrumented.instanceOf(DragSliderToEnd.class).newInstance();
    }

    @Step("{0} drags the slider to the end to submit the kudo")
    @Override
    public <T extends Actor> void performAs(T actor) {
        SliderActions.dragToEnd(actor);
    }
}