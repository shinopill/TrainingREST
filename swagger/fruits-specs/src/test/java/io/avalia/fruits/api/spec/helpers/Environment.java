package io.avalia.fruits.api.spec.helpers;


import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.PointScalesApi;

import java.io.IOException;
import java.util.Properties;

public class Environment {

    private BadgesApi badgeApi = new BadgesApi();
    private PointScalesApi pointScaleApi = new PointScalesApi();

    public Environment() throws IOException {

        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = props.getProperty("io.avalia.fruits.server.url");
        badgeApi.getApiClient().setBasePath(url);
        pointScaleApi.getApiClient().setBasePath(url);
    }

    public BadgesApi getBadgesApi() {
        return badgeApi;
    }

    public PointScalesApi getPointScalesApi() {
        return pointScaleApi;
    }
}
