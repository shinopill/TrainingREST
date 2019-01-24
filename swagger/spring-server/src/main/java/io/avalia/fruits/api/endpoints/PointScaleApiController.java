package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.PointScalesApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.PointScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Transactional
@Controller
public class PointScaleApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    private Tools tools = new Tools();

    @Override
    public ResponseEntity<PointScale> getPointScale(@ApiParam(value = "",required=true ) @PathVariable("pointScaleName") String pointScaleName,
                                                    @ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey) {
        PointScaleEntity res = pointScaleRepository.findByNameAndAppKey(pointScaleName, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toPointScale(res));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> updatePointScale(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey,
                                                   @ApiParam(value = "",required=true ) @PathVariable("pointScaleName") String pointScaleName,
                                                   @ApiParam(value = "" ,required=true ) @RequestBody PointScale pointScale) {
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
    public ResponseEntity<Object> createPointScale(@ApiParam(value = "" ,required=true ) @RequestBody PointScale pointScale) {
        Integer appKey = pointScale.getAppKey();

        PointScaleEntity res = pointScaleRepository.findByNameAndAppKey(pointScale.getName(), pointScale.getAppKey());
        if (res == null) {
            ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
            if(app == null){
                app = tools.createApplicationEntity(appKey);
            }

            List<PointScaleEntity> pointScaleEntities = app.getPointScales();
            PointScaleEntity newPointScale = tools.toPointScaleEntity(pointScale);
            pointScaleEntities.add(newPointScale);

            applicationRepository.save(app);
            return ResponseEntity.accepted().build();

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}