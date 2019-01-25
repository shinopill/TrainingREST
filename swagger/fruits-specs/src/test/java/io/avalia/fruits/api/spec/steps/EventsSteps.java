package io.avalia.fruits.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;
import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.dto.Badge;
import io.avalia.fruits.api.dto.Event;
import io.avalia.fruits.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventsSteps {

    private Environment env;
    private EventsApi eventsApi;
    private Event event;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private final int appGeneratedKey = 27;


    public EventsSteps(Environment environment) {
        env = environment;
        eventsApi = env.getEventsApi();
    }

    @Given("^there is an event server")
    public void there_is_an_event_server() throws Throwable {
        assertNotNull(eventsApi);
    }

    @Given("^I have an event payload$")
    public void i_have_an_event_payload() throws Throwable {

        List<String> properties = new ArrayList<>();
        properties.add("proprieteTest");

        event = new Event();
        event.setAppKey(appGeneratedKey);
        event.setEventType("TestEvent");
        event.setTimestamp(new Date().toString());
        event.setUsername("Toto");
        event.setProperties(properties);


    }

    @When("^I POST it to the /events endpoint$")
    public void i_POST_it_to_the_events_payload() throws Throwable {
        try {
            lastApiResponse = eventsApi.sendEventWithHttpInfo(event);
            lastStatusCode = lastApiResponse.getStatusCode();
        }
        catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code from /events endpoint$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1,lastStatusCode);
    }

    @Given("^I have a badge payload, a pointscale payload, an user and a rule that uses them$")
    public void i_have_a_badge_payload_a_pointscale_payload_an_user_and_a_rule_that_uses_them() throws Throwable {
        Badge badge = new Badge();

    }

    @When("^I post an event triggering the rule$")
    public void i_post_an_event_triggering_the_rule() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the user should win the points on the pointscale$")
    public void the_user_should_win_the_points_on_the_pointscale() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^win the badge$")
    public void win_the_badge() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
