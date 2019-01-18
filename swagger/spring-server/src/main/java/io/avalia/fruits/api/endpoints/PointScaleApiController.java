package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScaleApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


//TODO all
public class PointScaleApiController implements PointScaleApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointScale) {
        PointScaleEntity pointScaleEntity = toPointScaleEntity(pointScale);
        pointScaleRepository.save(pointScaleEntity);

        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Object> deletePointScale(PointScale pointScale) {
        PointScaleEntity pointScaleEntity = toPointScaleEntity(pointScale);
        pointScaleRepository.delete(pointScaleEntity);

        return ResponseEntity.accepted().build();
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
