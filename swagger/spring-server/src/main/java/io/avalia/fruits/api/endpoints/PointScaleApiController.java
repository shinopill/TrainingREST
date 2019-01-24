package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PointScaleApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

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
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
        if(app != null){
            List<PointScaleEntity> pointScaleEntities = app.getPointScales();
            for (PointScaleEntity p :pointScaleEntities) {
                if(p.getName().equalsIgnoreCase(pointScaleName)){
                    p.setName(pointScale.getName());
                    p.setDescription(pointScale.getDescription());
                }

            }

            applicationRepository.save(app);
            return ResponseEntity.accepted().build();

        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> createPointScale(PointScale pointScale) {
        Integer appKey = pointScale.getAppKey();
        PointScale res = tools.toPointScale(pointScaleRepository.findByNameAndAppKey(pointScale.getName(), pointScale.getAppKey()));
        if (res == null) {
            ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
            if(app == null) {
                app = tools.createApplicaitonEntity(appKey);
            }

            List<PointScaleEntity> pointScaleEntities = app.getPointScales();
            PointScaleEntity newPointScale = tools.toPointScaleEntity(pointScale);
            pointScaleEntities.add(newPointScale);
            return ResponseEntity.accepted().build();

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}