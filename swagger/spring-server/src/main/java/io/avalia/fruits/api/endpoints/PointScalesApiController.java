package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
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
    public ResponseEntity<PointScale> getPointScale(String pointScaleName, Integer appKey) {
        PointScaleEntity res = pointScaleRepository.findByNameAndAppKey(pointScaleName, appKey);
        return ResponseEntity.ok(toPointScale(res));
    }

    @Override
    public ResponseEntity<Integer> getPoints(String pointScaleName, Integer appKey, String username) {
        //TODO: to do
        return null;
    }

    @Override
    public ResponseEntity<Object> updatePointScale(Integer appKey, String pointScaleName, PointScale pointScale) {
        PointScaleEntity res = pointScaleRepository.deletePointScaleEntitiesByNameAndAppKey(pointScaleName, appKey);
        if (res != null) {
            pointScaleRepository.save(toPointScaleEntity(pointScale));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointScale) {
        PointScale res = toPointScale(pointScaleRepository.findByNameAAndAppKey(pointScale.getName(), pointScale.getAppKey()));
        if (res == null) {
            PointScaleEntity newPointScale = toPointScaleEntity(pointScale);
            pointScaleRepository.save(newPointScale);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }




    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        entity.setDescription(pointScale.getDescription());
        entity.setAppKey(pointScale.getAppKey());
        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setName(entity.getName());
        pointScale.setDescription(entity.getDescription());
        pointScale.setAppKey(entity.getAppKey());
        return pointScale;
    }
}
