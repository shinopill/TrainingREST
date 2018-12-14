package io.avalia.fruits.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String ruleName;
    private int appKey;

    public long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getRuleName() { return ruleName; }

    public void setRuleName(String ruleName){ this.ruleName = ruleName; }

    public int getAppKey() { return appKey; }

    public void setAppKey(int appKey) { this.appKey = appKey; }
}
