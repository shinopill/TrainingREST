package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgeApi;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgeApiController implements BadgeApi {

    @Autowired
    BadgeRepository badgeRepository;

    public ResponseEntity<Object> createBadge(Badge badge) {
        BadgeEntity newBadgeEntity = badgeRepository.findBadgeEntitiesByNameAndAppKey(badge.getName(), badge.getAppKey());
        if (newBadgeEntity == null) {
            badgeRepository.save(newBadgeEntity);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Object> deleteBadge(Badge badge) {
        BadgeEntity res = badgeRepository.deleteBadgeEntitiesByAppKeyAndName(badge.getName(), badge.getAppKey());
        if (res != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    public ResponseEntity<Badge> getBadge(Integer appKey, String badgeName) {
        BadgeEntity res = badgeRepository.findBadgeEntitiesByNameAndAppKey(badgeName, appKey);
        return ResponseEntity.ok(toBadge(res));
    }

    @Override
    public ResponseEntity<Object> modifiyBadge(Badge badge, String badgeName, Integer appKey) {
        BadgeEntity res = badgeRepository.deleteBadgeEntitiesByAppKeyAndName(badgeName, appKey);
        if (res != null) {
            badgeRepository.save(toBadgeEntity(badge));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setDescription(badge.getDescription());
        entity.setAppKey(badge.getAppKey());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setName(entity.getName());
        badge.setDescription(entity.getDescription());
        badge.setAppKey(entity.getAppKey());
        return badge;
    }
}
