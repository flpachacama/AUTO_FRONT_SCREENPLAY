package automation.ui;

import net.serenitybdd.screenplay.targets.Target;

public class KudoHistoryUI {

    private KudoHistoryUI() {
    }

    public static final Target HISTORY_BUTTON = Target.the("history button")
        .locatedBy("//button[normalize-space()='Historial']");

    public static final Target HISTORY_TITLE = Target.the("kudo history title")
        .locatedBy("//h2[contains(.,'Historial de')]");

    public static final Target REFRESH_BUTTON = Target.the("refresh history button")
        .locatedBy("//button[normalize-space()='Actualizar']");

    public static final Target KUDO_LIST_CONTAINER = Target.the("kudo list container")
        .locatedBy("//section[contains(@class,'grid')]");

    public static final Target KUDO_ITEMS = Target.the("kudo history items")
        .locatedBy("//article");
}
