package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgeApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    private Tools tools = new Tools();

    @Override
    public ResponseEntity<List<Badge>> getBadges(Integer appKey) {
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);

        if(app != null){
            List<BadgeEntity> badgesEntity = app.getBagdes();
            if (badgesEntity != null) {
                List<Badge> badges = new ArrayList();
                for (BadgeEntity entity : badgesEntity) {
                    badges.add(tools.toBadge(entity));
                }

                return ResponseEntity.ok(badges);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> createBadge(Badge badge) {
        Integer appKey = badge.getAppKey();

        ResponseEntity<Badge> res = getBadge(badge.getAppKey(), badge.getName());

        if (!res.hasBody()) {
            ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
            if(app == null){
                app = tools.createApplicaitonEntity(appKey);

            }
            List<BadgeEntity> badges = app.getBagdes();
            badges.add(tools.toBadgeEntity(badge));
            applicationRepository.save(app);

            return ResponseEntity.accepted().build();
       } else {
             return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<Badge> getBadge(Integer appKey, String badgeName) {

        BadgeEntity res = badgeRepository.findBadgeEntitiesByNameAndAppKey(badgeName, appKey);
        if(res == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tools.toBadge(res));
    }

    @Override
    public ResponseEntity<Object> modifiyBadge(Badge badge, String badgeName, Integer appKey) {
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
        ResponseEntity<Badge> res = getBadge(badge.getAppKey(), badge.getName());

        if (!res.hasBody()) {
            List<BadgeEntity> badges = app.getBagdes();
            for (BadgeEntity b : badges){
                if(b.getName().equalsIgnoreCase(badgeName)){
                    b.setDescription(badge.getDescription());
                    b.setName(badge.getName());
                }
            }

            applicationRepository.save(app);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
