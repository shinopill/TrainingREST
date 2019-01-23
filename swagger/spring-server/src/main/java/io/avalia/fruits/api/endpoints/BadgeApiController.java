package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.api.model.Badge;
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

    private Tools tools = new Tools();

    @Override
    public ResponseEntity<List<Badge>> getBadges(Integer appKey) {
        List<BadgeEntity> badgesEntity = badgeRepository.findAllByAppKey(appKey);
        List<Badge> badges = new ArrayList();
        for(BadgeEntity entity : badgesEntity){
            badges.add(tools.toBadge(entity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<Object> createBadge(Badge badge) {
        ResponseEntity<Badge> res = getBadge(badge.getAppKey(), badge.getName());
        if (!res.hasBody()) {
            BadgeEntity newBadgeEntity = tools.toBadgeEntity(badge);
            badgeRepository.save(newBadgeEntity);
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
        BadgeEntity res = badgeRepository.deleteBadgeEntitiesByAppKeyAndName(badgeName, appKey);
        if (res != null) {
            badgeRepository.save(tools.toBadgeEntity(badge));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
