package io.avalia.fruits.entities;

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



}
