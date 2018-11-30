package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.BadgeApi;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgeApi {

    @Autowired
    BadgeRepository badgeRepository;

    public ResponseEntity<Object> createBadges(@ApiParam(value = "", required = true) @Valid @RequestBody Badge badge) {
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        Long id = newBadgeEntity.getId();

        // TODO: check si jamais c'est la bonne méthode car pas sur
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        // Old version
        // URI location = ServletUriComponentsBuilder.getFromCurrentRequest().path("/{id}").buildeAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Badge>> getBadges() {
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
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setName(entity.getName());
        badge.setDescription(entity.getDescription());
        return badge;
    }

    @Override
    public ResponseEntity<List<Badge>> badgeGet() {
        return null;
    }

    @Override
    public ResponseEntity<Void> createBadge() {
        return null;
    }
}
