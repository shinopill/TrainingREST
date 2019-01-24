package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntityByNameAndAppKey(String name, Integer appKey);
    List<UserEntity> findAllUserEntityByAppKey(Integer appKey);
}
