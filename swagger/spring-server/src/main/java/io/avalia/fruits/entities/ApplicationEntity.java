package io.avalia.fruits.entities;

import io.avalia.fruits.api.model.Rule;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
public class ApplicationEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long applicationID;

    @OneToMany
    List<EventEntity> events;

    @ManyToMany
    List<BadgeEntity> bagdes;

    @ManyToMany
    List<PointScaleEntity> pointScales;

    @OneToMany
    List<RuleEntity> rules;


    public long getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    public List<BadgeEntity> getBagdes() {
        return bagdes;
    }

    public void setBagdes(List<BadgeEntity> bagdes) {
        this.bagdes = bagdes;
    }

    public List<PointScaleEntity> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<PointScaleEntity> pointScales) {
        this.pointScales = pointScales;
    }

    public List<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(List<RuleEntity> rules) {
        this.rules = rules;
    }
}
