package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long>{
    BadgeEntity findBadgeEntitiesByAppKeyAndAndName(Integer appKey, String name);
    BadgeEntity deleteBadgeEntitiesByAppKeyAndName(Integer appKey, String name);
}
