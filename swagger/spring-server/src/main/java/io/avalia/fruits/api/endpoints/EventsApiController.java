package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.model.Event;
import io.avalia.fruits.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    public ResponseEntity<Object> sendEvent(Event event) {

        // TODO: to do
        return null;
    }
}
