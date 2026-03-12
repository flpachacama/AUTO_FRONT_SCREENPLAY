package automation.stepdefinitions;

import automation.questions.KudoSubmissionResult;
import automation.task.EnterKudoMessage;
import automation.task.NavigateToKudosForm;
import automation.task.OpenLandingPage;
import automation.task.SelectCategory;
import automation.task.SelectFromUser;
import automation.task.SelectToUser;
import automation.task.SubmitKudoWithSlider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class KudoStepDefinitions {

    // -------------------------------------------------------------------------
    // Given
    // -------------------------------------------------------------------------

    @Given("the user opens SofkianOS landing page")
    public void theUserOpensSofkianOSLandingPage() {
        OnStage.theActorCalled("Sofkiano").attemptsTo(
                OpenLandingPage.open()
        );
    }

    // -------------------------------------------------------------------------
    // When
    // -------------------------------------------------------------------------

    @When("the user navigates to the Kudos form")
    public void theUserNavigatesToTheKudosForm() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateToKudosForm.now()
        );
    }

    @When("the user fills the kudo form with:")
    public void theUserFillsTheKudoFormWith(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);

        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectFromUser.named(data.get("from")),
                SelectToUser.named(data.get("to")),
                SelectCategory.named(data.get("category")),
                EnterKudoMessage.saying(data.get("message"))
        );
    }

    @When("the user submits the kudo using the slider")
    public void theUserSubmitsTheKudoUsingTheSlider() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SubmitKudoWithSlider.now()
        );
    }

    // -------------------------------------------------------------------------
    // Then
    // -------------------------------------------------------------------------

    @Then("the user should see a successful kudo submission message")
    public void theUserShouldSeeASuccessfulKudoSubmissionMessage() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(
                seeThat(
                        "the success toast '¡Kudo enviado!'",
                        KudoSubmissionResult.isVisible(),
                        is(true)
                )
        );
    }
}