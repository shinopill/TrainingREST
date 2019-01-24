package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RuleRepository extends CrudRepository<RuleEntity, Long> {
    RuleEntity findRuleEntityByNameAndAppKey(String name, Integer appKey);
    void deleteRuleEntityByNameAndAppKey(String ruleName, Integer appKey);
    List<RuleEntity> findAllByAppKey(Integer appKey);
}
