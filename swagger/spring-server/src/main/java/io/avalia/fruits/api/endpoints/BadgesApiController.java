package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgesApi;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<List<Badge>> getBadges(Integer appKey) {
        List<BadgeEntity> badgesEntity = badgeRepository.findAllByAppKey(appKey);
        List<Badge> badges = new ArrayList();
        for(BadgeEntity entity : badgesEntity){
            badges.add(toBadge(entity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<Object> createBadge(Badge badge) {
        Badge res = getBadge(badge.getAppKey(), badge.getName()).getBody();
        if (res == null) {
            BadgeEntity newBadgeEntity = toBadgeEntity(badge);
            badgeRepository.save(newBadgeEntity);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
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
