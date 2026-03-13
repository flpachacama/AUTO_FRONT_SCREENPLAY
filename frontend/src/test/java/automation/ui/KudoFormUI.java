package automation.ui;

import net.serenitybdd.screenplay.targets.Target;

public class KudoFormUI {

    private KudoFormUI() {
    }

    public static final Target KUDO_FORM_TITLE = Target.the("kudo form title")
        .locatedBy("//h2[contains(.,'Reconoce a un')]");

    public static final Target FROM_SELECT = Target.the("from user select")
        .locatedBy("[name='from']");

    public static final Target TO_SELECT = Target.the("to user select")
        .locatedBy("[name='to']");

    public static final Target CATEGORY_SELECT = Target.the("category select")
        .locatedBy("[name='category']");

    public static final Target MESSAGE_TEXTAREA = Target.the("message textarea")
        .locatedBy("[name='message']");

    public static final Target SLIDER_TRACK = Target.the("kudo slider track")
        .locatedBy("div[class*='cursor-pointer'][class*='rounded-full']");

    public static final Target SLIDER_HANDLE = Target.the("kudo slider handle")
        .locatedBy("div[class*='w-16'][class*='bg-brand']");

    public static final String SUCCESS_TOAST_XPATH = "//*[contains(text(),'Kudo enviado')]";

    public static final Target SUCCESS_TOAST = Target.the("successful kudo toast")
        .locatedBy(SUCCESS_TOAST_XPATH);
}
