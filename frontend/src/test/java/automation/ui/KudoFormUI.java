package automation.ui;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class KudoFormUI {

    @DefaultUrl("/")
    public static class LandingHomePage extends PageObject {}

    public static final Target LANDING_ACCESS_BUTTON =
            Target.the("'Acceder' button on the landing page")
                  .located(By.xpath("//button[normalize-space()='Acceder']"));

    public static final Target KUDOS_FORM_TITLE =
            Target.the("kudos form title")
                  .located(By.xpath("//h2[contains(.,'Reconoce a un')]"));

    public static final Target FROM_USER =
            Target.the("'from' (sender) dropdown")
                  .located(By.name("from"));

    public static final Target TO_USER =
            Target.the("'to' (recipient) dropdown")
                  .located(By.name("to"));

    public static final Target CATEGORY =
            Target.the("category dropdown")
                  .located(By.name("category"));

    public static final Target MESSAGE =
            Target.the("message textarea")
                  .located(By.name("message"));

    public static final Target SLIDER_TRACK =
            Target.the("submission slider track")
                  .located(By.cssSelector("div[class*='cursor-pointer'][class*='rounded-full']"));

    public static final Target SLIDER_HANDLE =
            Target.the("submission slider handle")
                  .located(By.cssSelector("div[class*='w-16'][class*='bg-brand']"));

    public static final Target SUCCESS_TOAST =
            Target.the("success toast '¡Kudo enviado!'")
                  .located(By.xpath("//*[contains(text(),'Kudo enviado')]"));
}
