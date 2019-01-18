package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgeApi;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgeApiController implements BadgeApi {

    @Autowired
    BadgeRepository badgeRepository;

    public ResponseEntity<Object> createBadge(Badge badge) {
        Badge res = getBadge(badge.getAppKey(), badge.getName()).getBody();
        if(res == null) {
            BadgeEntity newBadgeEntity = toBadgeEntity(badge);
            badgeRepository.save(newBadgeEntity);
            Long id = newBadgeEntity.getId();

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBadgeEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Object> deleteBadge(Badge badge){
        badgeRepository.deleteBadgeEntitiesByAppKeyAndName(badge.getAppKey(), badge.getName());
        return ResponseEntity.accepted().build();
    }


    public ResponseEntity<Badge> getBadge(Integer appKey, String badgeName){
        BadgeEntity res = badgeRepository.findBadgeEntitiesByAppKeyAndAndName(appKey, badgeName);
        return ResponseEntity.ok(toBadge(res));
    }

    @Override
    public ResponseEntity<Object> modifiyBadge(Badge badge, String badgeName, Integer appKey) {
        Badge res = getBadge(appKey, badgeName).getBody();
        if(res != null){
            res.setName(badge.getName());
            res.setDescription(badge.getDescription());
            badgeRepository.save(toBadgeEntity(res));
            return ResponseEntity.accepted().build();
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
