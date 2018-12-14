package io.avalia.fruits.entities;

import io.avalia.fruits.api.model.Rule;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private Rule rule;
    private int appKey;

    public long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Rule getRules() { return rule; }

    public void setRules(Rule name){ this.rule = rule; }

    public int getAppKey() { return appKey; }

    public void setAppKey(int appKey) { this.appKey = appKey; }
}
