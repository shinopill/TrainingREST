package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;


public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Long> {
    PointScaleEntity findByNameAndAppKey(String pointScaleName, Integer appKey);
    PointScaleEntity deletePointScaleEntitiesByNameAndAppKey(String pointScaleName, Integer appKey);
}
