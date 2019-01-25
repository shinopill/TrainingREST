package io.avalia.fruits.api.spec.steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;
import io.avalia.fruits.api.BadgesApi;

import io.avalia.fruits.api.dto.Badge;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import io.avalia.fruits.api.spec.helpers.Environment;

import java.util.concurrent.ThreadLocalRandom;


public class BadgeSteps {

    private Environment env;
    private BadgesApi badgeApi;
    private Badge badge;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    final private int generateAppKey = 27;

    public BadgeSteps(Environment environment) {
        env = environment;
        badgeApi = env.getBadgesApi();

    }


    @Given("^there is a badge server$")
    public void there_is_a_badge_server() throws Throwable {
        assertNotNull(badgeApi);
    }


    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        badge = new Badge();
        badge.setAppKey(generateAppKey);
        badge.setName("tata");
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

    @Then("^I receive a (\\d+) status code from the /badges endpoint$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1,lastStatusCode);
    }


    @When("^I GET it to the /badges/(.*) endpoint$")
    public void i_GET_it_to_the_badge_endpoint(String arg1) throws Throwable {

        try {
            lastApiResponse = badgeApi.getBadgeWithHttpInfo(badge.getAppKey(), badge.getName());
            lastStatusCode = lastApiResponse.getStatusCode();
            lastApiCallThrewException = false;
            lastApiException = null;
        }
        catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }

    }

    @Then("^response body should contain the asked badge data$")
    public void response_body_should_contain_the_asked_badge_data() throws Throwable {
        assertEquals(badge,lastApiResponse.getData());
    }

    @When("^I GET all badges to the /badges endpoint$")
    public void i_GET_all_badges_to_the_badges_endpoint() throws Throwable {
        try {
            lastApiResponse = badgeApi.getBadgesWithHttpInfo(badge.getAppKey());
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        }
        catch (ApiException ex) {

            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = ex;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^Response Body should contain badges data$")
    public void response_Body_should_contain_badges_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I PUT it to the /badges/(.*) endpoint$")
    public void i_PUT_it_to_the_badge_endpoint(String arg1) throws Throwable {

        // Un badge modifié pour faire l'update
        Badge tmp = new Badge();
        tmp.setAppKey(badge.getAppKey());
        tmp.setName("unNouveauNom");
        tmp.setDescription("Le badge modifié");

        lastApiResponse = badgeApi.modifiyBadgeWithHttpInfo(tmp,tmp.getName(),tmp.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
    }
}
