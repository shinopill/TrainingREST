package io.avalia.fruits.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;
import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.dto.Badge;
import io.avalia.fruits.api.dto.PointScale;
import io.avalia.fruits.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CreationSteps {

    private Environment env;
    private BadgesApi badgeApi;
    private PointScalesApi pointScaleApi;

    private Badge badge;
    private PointScale pointScale;
    private String username;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        env   = environment;
        badgeApi  = env.getBadgesApi();
        pointScaleApi = env.getPointScalesApi();
    }

    @Given("^there is a Gamification server$")
    public void there_is_a_Gamification_server() throws Throwable {
        assertNotNull(badgeApi);
        assertNotNull(pointScaleApi);
    }

    @Given("^I have a badge payload$")
    public void i_have_a_badge_payload() throws Throwable {
        badge = new Badge();
    }

    @When("^I POST it to the /badge/ endpoint$")
    public void i_POST_it_to_the_badge_endpoint() throws Throwable {

        try {

            lastApiResponse = badgeApi.createBadgeWithHttpInfo(badge);
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(201,arg1);
    }

    @When("^I GET it to the /badge/appId/badgeId endpoint$")
    public void i_GET_it_to_the_badge_appId_badgeId_endpoint() throws Throwable {
        badgeApi.getBadges(badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain the asked badge data$")
    public void response_body_should_contain_the_asked_badge_data() throws Throwable {
        assertEquals(badge,lastApiResponse.getData());
    }

    @Given("^I have an API key$")
    public void i_have_an_API_key() throws Throwable {
        assertNotNull(badge.getAppKey());
    }

    @When("^I GET all badges to the /badge endpoint$")
    public void i_GET_all_badges_to_the_badge_endpoint() throws Throwable {
        badgeApi.getBadges(badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^Response Body should contain a badges array$")
    public void response_Body_should_contain_a_badges_array() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I POST it to the /badges/appId/badgeId endpoint$")
    public void i_POST_it_to_the_badges_appId_badgeId_endpoint() throws Throwable {
        try {

            lastApiResponse = badgeApi.createBadgeWithHttpInfo(badge);
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

    @When("^I GET the badge with name to the /badges/name endpoint$")
    public void i_GET_the_badge_with_name_to_the_badges_name_endpoint() throws Throwable {
        badgeApi.getBadges(badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @When("^I POST it to the /badge/appId endpoint$")
    public void i_POST_it_to_the_badge_appId_endpoint() throws Throwable {
        try {

            lastApiResponse = badgeApi.createBadgeWithHttpInfo(badge);
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

    @When("^I GET all badges to the /badge/appId endpoint$")
    public void i_GET_all_badges_to_the_badge_appId_endpoint() throws Throwable {
        badgeApi.getBadges(badge.getAppKey());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^Response Body should contain badges data$")
    public void response_Body_should_contain_badges_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }



    /*************************
     Steps for PointScales API
     **************************/

    @Given("^there is a gamification server$")
    public void there_is_a_gamification_server() throws Throwable {
        assertNotNull(badgeApi);
        assertNotNull(pointScaleApi);
    }

    @Given("^I have a pointScale payload$")
    public void i_have_a_pointScale_payload() throws Throwable {
        pointScale = new PointScale();
    }

    @When("^I POST it to /pointScale endpoint$")
    public void i_POST_it_to_pointScale_endpoint() throws Throwable {
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

    @When("^I POST it to the /pointScale endpoint$")
    public void i_POST_it_to_the_pointScale_endpoint() throws Throwable {
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

    @Given("^I have an API key, a PointScale name and an username$")
    public void i_have_an_API_key_a_PointScale_name_and_an_username() throws Throwable {
        assertNotNull(pointScale);
        username = "toto";
    }

    @When("^I GET it to the /pointScales/PointScaleName/username endpoint$")
    public void i_GET_it_to_the_pointScales_PointScaleName_username_endpoint() throws Throwable {
        pointScaleApi.getPoints(pointScale.getName(),pointScale.getAppKey(),username);
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain every PointScale data$")
    public void response_body_should_contain_every_PointScale_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }


    @When("^I GET it to the /pointScales/pointScaleName/users$")
    public void i_GET_it_to_the_pointScales_pointScaleName_users() throws Throwable {
        pointScaleApi.getPoints(pointScale.getName(),pointScale.getAppKey(),username);
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain every users whose have this pointscale$")
    public void response_body_should_contain_every_users_whose_have_this_pointscale() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I GET it to the /pointScale/appId endpoint$")
    public void i_GET_it_to_the_pointScale_appId_endpoint() throws Throwable {
        pointScaleApi.getPoints(pointScale.getName(), pointScale.getAppKey(), username);
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain user's points$")
    public void response_body_should_contain_user_s_points() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }



    @Given("^I have a payload$")
    public void i_have_a_payload() throws Throwable {
        assertNotNull(pointScale);
    }

    @Given("^I have a username, a pointScale payload and points$")
    public void i_have_a_username_a_pointScale_payload_and_points() throws Throwable {
        assertNotNull(pointScale);
        assertNotNull(username);
    }

    @When("^I POST it to the /pointscale/update/userpoints$")
    public void i_POST_it_to_the_pointscale_update_userpoints() throws Throwable {
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

    @Then("^I receive (\\d+) status code$")
    public void i_receive_status_code(int arg1) throws Throwable {
        assertEquals(201,arg1);
    }

    @Given("^I have a PointScale payload$")
    public void i_have_a_PointScale_payload() throws Throwable {
        assertNotNull(pointScale);
    }

    @When("^I POST it to the /pointScale/update/scalePoints endpoint$")
    public void i_POST_it_to_the_pointScale_update_scalePoints_endpoint() throws Throwable {
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

    @Given("^I have pointScale payload and a username$")
    public void i_have_pointScale_payload_and_a_username() throws Throwable {
        assertNotNull(pointScale);
        assertNotNull(username);
    }

    @When("^I GET it to the /pointScale/user endpoint$")
    public void i_GET_it_to_the_pointScale_user_endpoint() throws Throwable {

        pointScaleApi.getPoints(pointScale.getName(), pointScale.getAppKey(), username);
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();
    }

    @Then("^response body should contain user datas$")
    public void response_body_should_contain_user_datas() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @When("^I GET it to the /pointscale/user/all endpoint$")
    public void i_GET_it_to_the_pointscale_user_all_endpoint() throws Throwable {

        pointScaleApi.getAllUsersFromPointScale(pointScale.getAppKey(),pointScale.getName());
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiException.getCode();

    }

    @Then("^response body should contain every user and their points$")
    public void response_body_should_contain_every_user_and_their_points() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

}