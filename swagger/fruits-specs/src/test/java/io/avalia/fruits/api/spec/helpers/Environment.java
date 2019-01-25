package io.avalia.fruits.api.spec.helpers;

import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.PointscalesApi;
import io.avalia.fruits.api.RulesApi;

import java.io.IOException;
import java.util.Properties;

public class Environment {

    private BadgesApi badgeApi = new BadgesApi();
    private EventsApi eventsApi = new EventsApi();
    private PointscalesApi pointscaleApi = new PointscalesApi();
    private RulesApi rulesApi = new RulesApi();

    public Environment() throws IOException {

        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = props.getProperty("io.avalia.fruits.server.url");
        badgeApi.getApiClient().setBasePath(url);
        pointscaleApi.getApiClient().setBasePath(url);
    }

    public BadgesApi getBadgesApi() {
        return badgeApi;
    }
    public EventsApi getEventsApi() {return eventsApi; }
    public PointscalesApi getpointscalesApi() {
        return pointscaleApi;
    }
    public RulesApi getRulesApi() {return rulesApi;}
}
