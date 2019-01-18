package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long>{
    BadgeEntity findBadgeEntitiesByAppKeyAndName(Integer appKey, String name);
    BadgeEntity deleteBadgeEntitiesByAppKeyAndName(Integer appKey, String name);
    List<BadgeEntity> findAllByAppKey(Integer appKey);
}
