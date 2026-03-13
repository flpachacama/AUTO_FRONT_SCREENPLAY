package automation.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LandingPageUI {

    private LandingPageUI() {
    }

    public static final Target ACCESS_BUTTON = Target.the("Acceder button")
        .locatedBy("//button[normalize-space()='Acceder']");
}
