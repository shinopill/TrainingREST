package io.avalia.fruits.api.spec.steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;
import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.dto.Badge;
import io.avalia.fruits.api.dto.Event;
import io.avalia.fruits.api.dto.PointScale;
import io.avalia.fruits.api.dto.Rule;
import io.avalia.fruits.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CreationSteps {

    private Environment env;

    private BadgesApi badgeApi;
    private EventsApi eventsApi;
    private PointScalesApi pointScaleApi;
    private RulesApi rulesApi;

    private Badge badge;
    private Event event;
    private PointScale pointScale;
    private Rule rule;
    private String username;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        env = environment;
        badgeApi = env.getBadgesApi();
        pointScaleApi = env.getPointScalesApi();
        eventsApi = env.getEventsApi();
        rulesApi = env.getRulesApi();
    }

    @Given("^there is a gamification server$")
    public void there_is_a_Gamification_server() throws Throwable {
        assertNotNull(badgeApi);
        assertNotNull(pointScaleApi);
        assertNotNull(eventsApi);
        assertNotNull(rulesApi);
    }

    /**
     * Badge feature steps
     */
    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        badge = new Badge();
        badge.setAppKey();
        badge.setName("abc");
        badge.setDescription("Badge de test pour la validation de la spec");
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badge_endpoint() throws Throwable {

        try {

            lastApiResponse = badgeApi.createBadgeWithHttpInfo(badge);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1,lastStatusCode);
    }


    @When("^I GET it to the /badges/\"([^\"]*)\" endpoint$")
    public void i_GET_it_to_the_badge_endpoint(String arg1) throws Throwable {
        badgeApi.getBadge(badge.getAppKey(), badge.getName());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain the asked badge data$")
    public void response_body_should_contain_the_asked_badge_data() throws Throwable {
        assertEquals(badge,lastApiResponse.getData());
    }

    @When("^I GET all badges to the /badges endpoint$")
    public void i_GET_all_badges_to_the_badges_endpoint() throws Throwable {
        badgeApi.getBadges(badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^Response Body should contain badges data$")
    public void response_Body_should_contain_badges_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I PUT it to the /badges/\"([^\"]*)\" endpoint$")
    public void i_PUT_it_to_the_badge_endpoint(String arg1) throws Throwable {
        badgeApi.modifiyBadge(badge, badge.getName(), badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }



    /*
     * Event tests
     */

    @Given("^I have an event payload$")
    public void i_have_an_event_payload() throws Throwable {
        event = new Event();
        event.setAppKey(1234);

        event.setUsername("toto");
    }

    @When("^I POST it to the /events payload$")
    public void i_POST_it_to_the_events_payload() throws Throwable {
        try {

            lastApiResponse = eventsApi.sendEventWithHttpInfo(event);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    /*
     * pointScale tests
     */

    @Given("^I have a pointScale payload$")
    public void i_have_a_pointScale_payload() throws Throwable {
        pointScale = new PointScale();
        pointScale.setAppKey(1234);
        pointScale.setDescription("Pointscale de test");
        pointScale.setName("Testeur Pro");
    }

    @When("^I POST it to /pointScales endpoint$")
    public void i_POST_it_to_pointScales_endpoint() throws Throwable {

        try {

            lastApiResponse = pointScaleApi.createPointScaleWithHttpInfo(pointScale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @When("^I PUT it to the /pointScales endpoint$")
    public void i_PUT_it_to_the_pointScales_endpoint() throws Throwable {
        try {
            lastApiResponse = pointScaleApi.updatePointScaleWithHttpInfo(pointScale.getAppKey(),pointScale.getName(),pointScale);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }


    @Then("^I receive (\\d+) status code$")
    public void i_receive_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have an API key and a pointscale name$")
    public void i_have_an_API_key_and_a_pointscale_name() throws Throwable {
        assertNotNull(pointScale.getAppKey());
        assertNotNull(pointScale.getName());
    }

    @When("^I GET it to the /pointScales/\"([^\"]*)\" endpoint$")
    public void i_GET_it_to_the_pointScales_endpoint(String arg1) throws Throwable {
        try {
            lastApiResponse = pointScaleApi.getPointScaleWithHttpInfo(pointScale.getName(),pointScale.getAppKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^response body should contain pointscale data$")
    public void response_body_should_contain_pointscale_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @Given("^I have an API key, a pointscale name and an username$")
    public void i_have_an_API_key_a_pointscale_name_and_an_username() throws Throwable {
        assertNotNull(pointScale.getAppKey());
        assertNotNull(pointScale.getName());
        username = "Toto";
    }



    @Then("^response body should contain an username and his points$")
    public void response_body_should_contain_an_username_and_his_points() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }



    @Then("^response body should contain an users list and the points they have$")
    public void response_body_should_contain_an_users_list_and_the_points_they_have() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    /*
     * Rule test
     */
    @Given("^I have a rule payload$")
    public void i_have_a_rule_payload() throws Throwable {
        rule = new Rule();
        rule.setAppKey(1234);
        rule.setDescription("Test for rules endpoint");
        rule.setName("Test Rule");
        rule.setPoints(10);

    }

    @When("^I POST it to the /rules endpoint$")
    public void i_POST_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.createRuleWithHttpInfo(rule);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @When("^I GET it to the /rules/ruleName endpoint$")
    public void i_GET_it_to_the_rules_ruleName_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.getRuleWithHttpInfo(rule.getAppKey(),rule.getName());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^response body should contain the asked rule data$")
    public void response_body_should_contain_the_asked_rule_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @Given("^I have an API key$")
    public void i_have_an_API_key() throws Throwable {
        assertNotNull(rule.getAppKey());
    }

    @When("^I GET it to the /rules endpoint$")
    public void i_GET_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.getRulesWithHttpInfo(rule.getAppKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^Response Body should contain rules data$")
    public void response_Body_should_contain_rules_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I PUT it to the /rules/ endpoint$")
    public void i_PUT_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.updateRuleWithHttpInfo(rule.getAppKey(),rule.getName(),rule);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @When("^I DELETE it to the /rules endpoint$")
    public void i_DELETE_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.deleteRuleWithHttpInfo(rule);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @When("^I POST it to the /rules payload endpoint$")
    public void i_POST_it_to_the_rules_payload_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.createRuleWithHttpInfo(rule);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiException.getCode();
        } catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^Response Body should contain a rules array$")
    public void response_Body_should_contain_a_rules_array() throws Throwable {
       assertNotNull(lastApiResponse.getData());
    }
}