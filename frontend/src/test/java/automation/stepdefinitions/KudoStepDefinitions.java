package automation.stepdefinitions;

import automation.questions.TheKudoShouldAppearInHistory;
import automation.tasks.FillKudoForm;
import automation.tasks.NavigateToKudoForm;
import automation.tasks.OpenApplication;
import automation.tasks.OpenKudoHistory;
import automation.tasks.SendKudo;
import automation.tasks.ViewKudoHistory;
import automation.util.Actors;
import automation.util.KudoData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class KudoStepDefinitions {

    private KudoData sentKudo;

    @Given("the user opens the application")
    public void theUserOpensTheApplication() {
        OnStage.theActorCalled(Actors.DEFAULT_USER)
            .wasAbleTo(OpenApplication.onLandingPage());
    }

    @When("the user sends a kudo with:")
    public void theUserSendsAKudoWith(DataTable dataTable) {
        sentKudo = KudoData.from(dataTable);

        OnStage.theActorInTheSpotlight().attemptsTo(
            NavigateToKudoForm.now(),
            FillKudoForm.withData(sentKudo),
            SendKudo.now()
        );
    }

    @When("the actor opens the kudos history")
    public void theActorOpensTheKudosHistory() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenKudoHistory.now());
    }

    @When("the actor views the kudos history")
    public void theActorViewsTheKudosHistory() {
        OnStage.theActorInTheSpotlight().attemptsTo(ViewKudoHistory.now());
    }

    @Then("the actor should see the sent kudo in the kudos history")
    public void theActorShouldSeeTheSentKudoInTheKudosHistory() {
        OnStage.theActorInTheSpotlight().should(
            seeThat(TheKudoShouldAppearInHistory.withMessage(sentKudo.message()), is(true))
        );
    }
}
