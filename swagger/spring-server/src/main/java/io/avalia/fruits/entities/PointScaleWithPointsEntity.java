package io.avalia.fruits.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PointScaleWithPointsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @org.springframework.data.annotation.Version
    private long version;

    @OneToOne(cascade = CascadeType.ALL)
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
