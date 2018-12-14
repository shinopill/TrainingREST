package io.avalia.fruits.entities;

import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.api.model.PointScale;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class RuleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String      name;
    private String      description;
    private int         appKey;
    private String      badgeName;
    private int         pointReward;
    private String      pointScaleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAppKey() {
        return appKey;
    }

    public void setAppKey(int appKey) {
        this.appKey = appKey;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }

    public String getPointScaleName() { return pointScaleName; }

    public void setPointScaleName(String pointScaleName) { this.pointScaleName = pointScaleName; }

}
