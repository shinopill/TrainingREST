package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
    ApplicationEntity findByApplicationID(Integer applicationId);
}
