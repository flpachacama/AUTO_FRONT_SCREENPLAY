package automation.questions;

import automation.util.BrowserUtils;
import automation.util.WaitUtils;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;

public class KudoSubmissionResult implements Question<Boolean> {

    private static final By SUCCESS_LOCATOR =
            By.xpath("//*[contains(text(),'Kudo enviado')]");
    private static final int WAIT_SECONDS = 8;

    private KudoSubmissionResult() {}

    public static KudoSubmissionResult isVisible() {
        return new KudoSubmissionResult();
    }

    @Step("{0} checks whether the kudo submission success message is visible")
    @Override
    public Boolean answeredBy(Actor actor) {
        return WaitUtils.isElementVisible(
                BrowserUtils.getDriverFor(actor),
                SUCCESS_LOCATOR,
                WAIT_SECONDS
        );
    }
}