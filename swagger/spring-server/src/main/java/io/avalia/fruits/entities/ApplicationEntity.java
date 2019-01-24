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

    @org.springframework.data.annotation.Version
    private long version;


    private Integer applicationID;

    @OneToMany(cascade = CascadeType.ALL)
    List<EventEntity> events;

    @ManyToMany(cascade = CascadeType.ALL)
    List<BadgeEntity> bagdes;

    @ManyToMany(cascade = CascadeType.ALL)
    List<PointScaleEntity> pointScales;

    @OneToMany(cascade = CascadeType.ALL)
    List<RuleEntity> rules;

    @OneToMany(cascade = CascadeType.ALL)
    List<UserEntity> users;

    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
