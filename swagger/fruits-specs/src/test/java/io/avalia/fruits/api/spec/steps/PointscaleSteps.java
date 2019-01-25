package io.avalia.fruits.api.spec.steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.fruits.ApiException;
import io.avalia.fruits.ApiResponse;

import io.avalia.fruits.api.PointscalesApi;
import io.avalia.fruits.api.PointsApi;
import io.avalia.fruits.api.dto.PointScale;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import io.avalia.fruits.api.dto.PointScaleWithPoints;
import io.avalia.fruits.api.spec.helpers.Environment;

public class PointscaleSteps {

    private Environment env;
    private PointscalesApi pointscalesApi;
    private PointScale pointscale;
    private PointsApi pointsApi;
    private PointScaleWithPoints points;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private String username;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    final private int generateAppKey = 27;

    public PointscaleSteps(Environment environment) {
        env = environment;
        pointscalesApi = env.getpointscalesApi();
        username = "Toto";
    }


    @Given("^there is a pointscale server$")
    public void there_is_a_pointscale_server() throws Throwable {
        assertNotNull(pointscalesApi);
    }

    @Given("^I have a pointscale payload$")
    public void i_have_a_pointscale_payload() throws Throwable {
        pointscale = new PointScale();
        pointscale.setAppKey(generateAppKey);
        pointscale.setName("Testeur");
        pointscale.setDescription("Pointscale de test");

    }

    @When("^I POST it to /pointScales endpoint$")
    public void i_POST_it_to_pointScales_endpoint() throws Throwable {

        try {

            lastApiResponse = pointscalesApi.getPointScaleWithHttpInfo(pointscale.getName(), pointscale.getAppKey());
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

    @Then("^I receive a (\\d+) status code from /pointScales endpoint$")
    public void i_receive_status_code(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }

    @Given("^I have an API key and a pointscale name$")
    public void i_have_an_API_key_and_a_pointscale_name() throws Throwable {
        pointscale = new PointScale();
        pointscale.setAppKey(generateAppKey);
        pointscale.setName("TesteurPro");
        pointscale.setDescription("Pointscale de test");
    }

    @When("^I GET it to the /pointScales/\"([^\"]*)\" endpoint$")
    public void i_GET_it_to_the_pointScales_endpoint(String arg1) throws Throwable {
        try {

            lastApiResponse = pointscalesApi.getPointScaleWithHttpInfo(pointscale.getName(),pointscale.getAppKey());
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

    @When("^I PUT it to the /pointScales endpoint$")
    public void i_PUT_it_to_the_pointScales_endpoint() throws Throwable {
        try {

            PointScale updatedPointscale = new PointScale();
            updatedPointscale.setAppKey(pointscale.getAppKey());
            updatedPointscale.setName("TesteurProfessionel");

            lastApiResponse = pointscalesApi.updatePointScaleWithHttpInfo(pointscale.getAppKey(),pointscale.getName(),pointscale);
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

    @Given("^I have an API key, a pointscale name and an username$")
    public void i_have_an_API_key_a_pointscale_name_and_an_username() throws Throwable {
        pointscale = new PointScale();
        pointscale.setAppKey(generateAppKey);
        pointscale.setName("TesteurPro");
        pointscale.setDescription("Pointscale de test");
    }

    @When("^I GET it to the /users/\"([^\"]*)\"/\"([^\"]*)\" endpoint$")
    public void i_GET_it_to_the_users_endpoint(String arg1, String arg2) throws Throwable {
        try {

            lastApiResponse = pointsApi.getPointsWithHttpInfo(pointscale.getName(),pointscale.getAppKey(),username);
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

    @Then("^response body should contain pointscale data$")
    public void response_body_should_contain_pointscale_data() throws Throwable {
        assertNotNull(lastApiResponse.getData());
        assertEquals(lastApiResponse.getData(), pointscale);
    }

    @Then("^response body should contain an username and his points$")
    public void response_body_should_contain_an_username_and_his_points() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }



    @Then("^response body should contain an users list and the points they have$")
    public void response_body_should_contain_an_users_list_and_the_points_they_have() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }


}
