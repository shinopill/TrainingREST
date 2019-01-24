package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<RuleEntity, Long> {
    UserEntity findUserEntityByNameAndAppKey(String name, Integer appKey);
    List<UserEntity> findAllUserEntityByAppKey(Integer appKey);
}
