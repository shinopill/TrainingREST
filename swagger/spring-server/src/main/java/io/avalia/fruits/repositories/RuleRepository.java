package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<RuleEntity, Long> {
    RuleEntity findByNameAndAppKey(String ruleName, Integer appKey);
    RuleEntity deleteRuleEntityByNameAndAppKey(String ruleName, Integer appKey);
}
