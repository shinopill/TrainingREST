package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long>{
    BadgeEntity findBadgeEntitiesByNameAndAppKey(String badgeName, Integer appKey);
    BadgeEntity deleteBadgeEntitiesByAppKeyAndName(String badgeName, Integer appKey);
    List<BadgeEntity> findAllByAppKey(Integer appKey);
}
