package io.avalia.fruits.entities;

import io.avalia.fruits.api.model.PointScaleWithPoints;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idFromApplication;
    private String username;
    private int appKey;

    @OneToMany(cascade = CascadeType.ALL)
    List<PointScaleWithPointsEntity> pointScaleWithPoints;


    @ManyToMany(cascade = CascadeType.ALL)
    List<BadgeEntity> badges;

    public long getId() {return id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAppKey() {
        return appKey;
    }

    public void setAppKey(int appKey) {
        this.appKey = appKey;
    }

    public List<PointScaleWithPointsEntity> getPointScaleWithPoints() {
        return pointScaleWithPoints;
    }

    public void setPointScaleWithPoints(List<PointScaleWithPointsEntity> pointScaleWithPoints) {
        this.pointScaleWithPoints = pointScaleWithPoints;
    }

    public List<BadgeEntity> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeEntity> badges){
        this.badges = badges;
    }
}
