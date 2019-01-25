package io.avalia.fruits.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;
import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.dto.Badge;
import io.avalia.fruits.api.dto.Rule;
import io.avalia.fruits.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RulesSteps {

    private Environment env;
    private RulesApi rulesApi;
    private Rule rule;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public RulesSteps(Environment environment) {
        env = environment;
        rulesApi = env.getRulesApi();
    }

    @Given("^there is a rules server$")
    public void there_is_a_pointscale_server() throws Throwable {
        assertNotNull(rulesApi);
    }

    @Given("^I have a rule payload$")
    public void i_have_a_rule_payload() throws Throwable {

        Badge badgeTemp = new Badge();
        badgeTemp.setName("Test");
        badgeTemp.setAppKey(654);
        badgeTemp.setDescription("test de rules");

        rule = new Rule();
        rule.setAppKey(654);
        rule.setDescription("Test for rules endpoint");
        rule.setName("Test Rule");
        rule.setPoints(20);
        rule.setBadge(badgeTemp);
        rule.setNumberOfTimesToGetTheAward(5);
        rule.setEventType("Reponse");
        rule.setProperty("unePropriété");
    }

    @When("^I POST it to the /rules endpoint$")
    public void i_POST_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.createRuleWithHttpInfo(rule);
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

    @Then("^I receive a (\\d+) status code from /rules endpoint$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1,lastStatusCode);
    }

    @When("^I GET it to the /rules/ruleName endpoint$")
    public void i_GET_it_to_the_rules_ruleName_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.getRuleWithHttpInfo(rule.getAppKey(),rule.getName());
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

    @Then("^response body should contain the asked rule data$")
    public void response_body_should_contain_the_asked_rule_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @Given("^I have an API key$")
    public void i_have_an_API_key() throws Throwable {
        Badge badgeTemp = new Badge();
        badgeTemp.setName("Test");
        badgeTemp.setAppKey(654);
        badgeTemp.setDescription("test de rules");

        rule = new Rule();
        rule.setAppKey(654);
        rule.setDescription("Test for rules endpoint");
        rule.setName("Test Rule");
        rule.setPoints(20);
        rule.setBadge(badgeTemp);
        rule.setNumberOfTimesToGetTheAward(5);
        rule.setEventType("Reponse");
        rule.setProperty("unePropriété");
    }

    @When("^I GET it to the /rules endpoint$")
    public void i_GET_it_to_the_rules_endpoint() throws Throwable {
        try {
            lastApiResponse = rulesApi.getRulesWithHttpInfo(rule.getAppKey());
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
            lastStatusCode = lastApiResponse.getStatusCode();
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
            lastStatusCode = lastApiResponse.getStatusCode();
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
            lastStatusCode = lastApiResponse.getStatusCode();
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
