package automation.questions;

import automation.ui.KudoHistoryUI;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Collection;

public class TheKudoShouldAppearInHistory implements Question<Boolean> {

    private final String expectedMessage;

    public TheKudoShouldAppearInHistory(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public static TheKudoShouldAppearInHistory withMessage(String expectedMessage) {
        return new TheKudoShouldAppearInHistory(expectedMessage);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int maxAttempts = 8;

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            Collection<String> historyItems = Text.ofEach(KudoHistoryUI.KUDO_ITEMS).answeredBy(actor);
            boolean found = historyItems.stream().anyMatch(item -> item.contains(expectedMessage));
            if (found) {
                return true;
            }

            WebElementFacade refreshButton = KudoHistoryUI.REFRESH_BUTTON.resolveFor(actor);
            if (refreshButton.isCurrentlyVisible()) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
                jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", refreshButton);
                try {
                    refreshButton.click();
                } catch (RuntimeException clickFailure) {
                    jsExecutor.executeScript("arguments[0].click();", refreshButton);
                }
            }

            try {
                Thread.sleep(1200);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return false;
    }
}
