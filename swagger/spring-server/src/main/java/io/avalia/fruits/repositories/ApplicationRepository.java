package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
}
