package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
}
