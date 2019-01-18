package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScaleApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class PointScaleApiController implements PointScaleApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

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

    @Override
    public ResponseEntity<Object> deletePointScale(PointScale pointScale) {
        PointScaleEntity res = pointScaleRepository.deleteByNameAndAppKey(pointScale.getName(), pointScale.getAppKey());
        if (res != null) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
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
