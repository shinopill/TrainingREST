package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PointScalesApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;


    @Override
    public ResponseEntity<List<Object>> getAllUsersFromPointScale(Integer appKey, String pointScaleName) {
        // TODO: to do
        return null;
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(String pointScaleName, Integer appKey) {
        // TODO: to do
        return null;
    }

    @Override
    public ResponseEntity<Integer> getPoints(String pointScaleName, Integer appKey, String username) {
        //TODO: to do
        return null;
    }

    @Override
    public ResponseEntity<Object> updatePointScale(Integer appKey, String pointScaleName, PointScale pointScale) {
        //TODO to do
        return null;
    }
}
