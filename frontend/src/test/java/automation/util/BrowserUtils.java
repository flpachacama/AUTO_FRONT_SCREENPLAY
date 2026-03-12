package automation.util;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class providing reusable browser-level helpers.
 *
 * Methods here operate directly on the {@link WebDriver} and are intentionally
 * kept at a lower level than Tasks or Questions so they can be shared across
 * multiple Tasks without duplication.
 */
public final class BrowserUtils {

    private BrowserUtils() {
        // Utility class — no instantiation
    }

    /**
     * Returns the {@link WebDriver} instance associated with the given actor's
     * {@code BrowseTheWeb} ability.
     *
     * @param actor the Screenplay actor
     * @return the actor's WebDriver session
     */
    public static WebDriver getDriverFor(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver();
    }

    /**
     * Scrolls the viewport to the very top of the page.
     *
     * @param actor the Screenplay actor whose browser session to use
     */
    public static void scrollToTop(Actor actor) {
        ((JavascriptExecutor) getDriverFor(actor))
                .executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls a specific element into view using JavaScript.
     *
     * @param actor    the Screenplay actor whose browser session to use
     * @param cssQuery CSS selector for the target element
     */
    public static void scrollIntoView(Actor actor, String cssQuery) {
        ((JavascriptExecutor) getDriverFor(actor))
                .executeScript(
                        "document.querySelector(arguments[0]).scrollIntoView(true);",
                        cssQuery
                );
    }

    /**
     * Blocks until the browser's {@code document.readyState} equals
     * {@code "complete"}, waiting at most 10 seconds.
     *
     * @param actor the Screenplay actor whose browser session to use
     */
    public static void waitForPageLoad(Actor actor) {
        new WebDriverWait(getDriverFor(actor), Duration.ofSeconds(10))
                .until(driver -> "complete".equals(
                        ((JavascriptExecutor) driver).executeScript("return document.readyState")
                ));
    }
}
