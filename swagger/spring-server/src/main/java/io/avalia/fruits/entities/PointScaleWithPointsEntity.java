package io.avalia.fruits.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class PointScaleWithPointsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private PointScaleEntity pointScaleEntity;
    private int points;

    public long getId() {
        return id;
    }

    public PointScaleEntity getPointScaleEntity() {
        return pointScaleEntity;
    }

    public void setPointScaleEntity(PointScaleEntity pointScaleEntity) {
        this.pointScaleEntity = pointScaleEntity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
