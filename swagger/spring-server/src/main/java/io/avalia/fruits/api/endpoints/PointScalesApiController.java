package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.model.Badge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PointScalesApiController implements PointScalesApi {
    @Override
    public ResponseEntity<List<Badge>> getAllUsersFromPointScale(Integer appKey, String pointScaleName) {
        return null;
    }

    @Override
    public ResponseEntity<Integer> getPoints(String pointScaleName, Integer appKey, String username) {
        return null;
    }
}
