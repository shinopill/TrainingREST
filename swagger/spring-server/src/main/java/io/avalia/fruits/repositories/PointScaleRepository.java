package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Long> {
    PointScaleEntity  findByName(String name);
}
