package io.avalia.fruits.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class RuleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @org.springframework.data.annotation.Version
    private long version;

    private int appKey;
    private String name;
    private String eventType;
    private int numberOfTimesToGetTheAward;
    private String description;
    private String property;
    private int points;
    private PointScaleEntity pointScale;
    private BadgeEntity badge;

    public long getId() {
        return id;
    }

    public int getAppKey() {
        return appKey;
    }

    public void setAppKey(int appKey) {
        this.appKey = appKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getNumberOfTimesToGetTheAward() {
        return numberOfTimesToGetTheAward;
    }

    public void setNumberOfTimesToGetTheAward(int numberOfTimesToGetTheAward) {
        this.numberOfTimesToGetTheAward = numberOfTimesToGetTheAward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}

