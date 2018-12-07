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
    private Badge       badgeReward;
    private int         pointReward;
    private PointScale  pointScale;

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

    public Badge getBadgeReward() {
        return badgeReward;
    }

    public void setBadgeReward(Badge badgeReward) {
        this.badgeReward = badgeReward;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }

    public PointScale getPointScale() { return pointScale; }

    public void setPointScale(PointScale pointScale) { this.pointScale = pointScale; }

}
