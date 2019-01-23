package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.model.Event;
import io.avalia.fruits.entities.EventEntity;
import io.avalia.fruits.repositories.EventRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    public ResponseEntity<Object> sendEvent(Event event) {

        // TODO: to do
        return null;
    }

    private EventEntity toEventEntity(Event event) {
        EventEntity entity = new EventEntity();
        entity.setAppKey(event.getAppKey());
        entity.setEventType(event.getEventType());
        entity.setTimestamp(event.getTimestamp());
        entity.setProperties(event.getProperties());
        entity.setUsername(event.getUsername());
        return entity;
    }

    private Event toEvent(EventEntity entity){
        Event event = new Event();
        event.setAppKey(entity.getAppKey());
        event.setEventType(entity.getEventType());
        event.setProperties(entity.getProperties());
        event.setTimestamp(entity.getTimestamp());
        event.setUsername(entity.getUsername());
        return event;
    }
}
