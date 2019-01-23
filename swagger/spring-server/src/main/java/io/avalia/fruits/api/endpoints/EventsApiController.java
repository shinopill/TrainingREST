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
}
