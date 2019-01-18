package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;


public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Long> {
    PointScaleEntity findByNameAAndAppKey(String name, Integer appKey);
    PointScaleEntity deleteByNameAndAppKey(String name, Integer appKey);
}
