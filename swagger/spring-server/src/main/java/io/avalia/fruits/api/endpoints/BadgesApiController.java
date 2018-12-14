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

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    // TODO: utiliser la appId pour récupérer les badges seulement de l'app voulue

    @Autowired
    BadgeRepository badgeRepository;

    public ResponseEntity<List<Badge>> getBadges(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey) {
        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }

        return ResponseEntity.ok(badges);
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
