package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class PointScaleApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    private Tools tools;

    @Override
    public ResponseEntity<PointScale> getPointScale(String pointScaleName, Integer appKey) {
        PointScaleEntity res = pointScaleRepository.findByNameAndAppKey(pointScaleName, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toPointScale(res));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> updatePointScale(Integer appKey, String pointScaleName, PointScale pointScale) {
        return null; // TODO: todo
    }

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointScale) {
        PointScale res = tools.toPointScale(pointScaleRepository.findByNameAndAppKey(pointScale.getName(), pointScale.getAppKey()));
        if (res == null) {
            PointScaleEntity newPointScale = tools.toPointScaleEntity(pointScale);
            pointScaleRepository.save(newPointScale);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}