package automation.util;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class BrowserUtils {

    private BrowserUtils() {}

    public static WebDriver getDriverFor(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver();
    }

    public static void scrollToTop(Actor actor) {
        ((JavascriptExecutor) getDriverFor(actor))
                .executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollIntoView(Actor actor, String cssQuery) {
        ((JavascriptExecutor) getDriverFor(actor))
                .executeScript(
                        "document.querySelector(arguments[0]).scrollIntoView(true);",
                        cssQuery
                );
    }

    public static void waitForPageLoad(Actor actor) {
        new WebDriverWait(getDriverFor(actor), Duration.ofSeconds(10))
                .until(driver -> "complete".equals(
                        ((JavascriptExecutor) driver).executeScript("return document.readyState")
                ));
    }
}
