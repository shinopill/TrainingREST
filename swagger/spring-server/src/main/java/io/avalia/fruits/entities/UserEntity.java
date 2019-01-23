package io.avalia.fruits.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idFromApplication;

    @ManyToMany
    List<BadgeEntity> bages;

    @ManyToMany
    Map<PointScaleEntity, Integer> pointScale;

}
