package automation.questions;

import automation.ui.KudoFormUI;
import automation.util.WaitUtils;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class KudoSubmissionResult implements Question<Boolean> {

    private static final int WAIT_SECONDS = 8;

    public KudoSubmissionResult() {}

    public static KudoSubmissionResult isVisible() {
        return Instrumented.instanceOf(KudoSubmissionResult.class).newInstance();
    }

    @Step("{0} checks whether the kudo submission success message is visible")
    @Override
    public Boolean answeredBy(Actor actor) {
        return WaitUtils.isTargetVisible(actor, KudoFormUI.SUCCESS_TOAST, WAIT_SECONDS);
    }
}