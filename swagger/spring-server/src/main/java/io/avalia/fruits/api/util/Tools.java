package io.avalia.fruits.api.util;

import io.avalia.fruits.api.model.*;
import io.avalia.fruits.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setDescription(badge.getDescription());
        entity.setAppKey(badge.getAppKey());
        return entity;
    }

    public Badge toBadge(BadgeEntity badgeEntity) {
        Badge badge = new Badge();
        badge.setName(badgeEntity.getName());
        badge.setDescription(badgeEntity.getDescription());
        badge.setAppKey(badgeEntity.getAppKey());
        return badge;
    }

    public EventEntity toEventEntity(Event event) {
        EventEntity entity = new EventEntity();
        entity.setUsername(event.getUsername());
        entity.setTimestamp(event.getTimestamp());
        entity.setEventType(event.getEventType());
        entity.setProperties(event.getProperties());
        entity.setAppKey(event.getAppKey());
        return entity;
    }

    public Event toEvent(EventEntity eventEntity){
        Event event = new Event();
        event.setUsername(eventEntity.getUsername());
        event.setTimestamp(eventEntity.getTimestamp());
        event.setEventType(eventEntity.getEventType());
        event.setProperties(eventEntity.getProperties());
        event.setAppKey(eventEntity.getAppKey());
        return event;
    }

    public PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        entity.setDescription(pointScale.getDescription());
        entity.setAppKey(pointScale.getAppKey());
        return entity;
    }

    public PointScale toPointScale(PointScaleEntity pointScaleEntity) {
        PointScale pointScale = new PointScale();
        pointScale.setName(pointScaleEntity.getName());
        pointScale.setDescription(pointScaleEntity.getDescription());
        pointScale.setAppKey(pointScaleEntity.getAppKey());
        return pointScale;
    }

    public RuleEntity toRuleEntity(Rule rule){
        RuleEntity entity = new RuleEntity();
        entity.setAppKey(rule.getAppKey());
        entity.setName(rule.getName());
        entity.setEventType(rule.getEventType());
        entity.setNumberOfTimesToGetTheAward(rule.getNumberOfTimesToGetTheAward());
        entity.setDescription(rule.getDescription());
        entity.setPoints(rule.getPoints());
        entity.setPointScale(toPointScaleEntity(rule.getPointScale()));
        entity.setProperty(rule.getProperty());
        entity.setBadge(toBadgeEntity(rule.getBadge()));
        return entity;
    }

    public Rule toRule(RuleEntity ruleEntity){
        Rule rule = new Rule();
        rule.setAppKey(ruleEntity.getAppKey());
        rule.setName(ruleEntity.getName());
        rule.setEventType(ruleEntity.getEventType());
        rule.setNumberOfTimesToGetTheAward(ruleEntity.getNumberOfTimesToGetTheAward());
        rule.setDescription(ruleEntity.getDescription());
        rule.setPoints(ruleEntity.getPoints());
        rule.setPointScale(toPointScale(ruleEntity.getPointScale()));
        rule.setBadge(toBadge(ruleEntity.getBadge()));
        rule.setProperty(ruleEntity.getProperty());
        return rule;
    }

    public UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();

        List<PointScaleWithPointsEntity> pointScaleWithPointsEntities = new ArrayList<>();
        for(PointScaleWithPoints p : user.getPointScalesWithPoints()){
            pointScaleWithPointsEntities.add(toPointScaleWithPointsEntity(p));
        }

        List<BadgeEntity> badgeEntities = new ArrayList<>();
        for(Badge b : user.getBadgesArray()){
            badgeEntities.add(toBadgeEntity(b));
        }

        entity.setUsername(user.getUsername());
        entity.setAppKey(user.getAppkey());
        entity.setPointScaleWithPoints(pointScaleWithPointsEntities);
        entity.setBadges(badgeEntities);
        return entity;
    }

    public User toUser(UserEntity userEntity) {
        User user = new User();

        List<PointScaleWithPoints> pointScaleWithPoints = new ArrayList<>();
        for(PointScaleWithPointsEntity p : userEntity.getPointScaleWithPoints()){
            pointScaleWithPoints.add(toPointScaleWithPoints(p));
        }

        List<Badge> badges = new ArrayList<>();
        for(BadgeEntity b : userEntity.getBadges()){
            badges.add(toBadge(b));
        }
        user.setUsername(userEntity.getUsername());
        user.setAppkey(userEntity.getAppKey());
        user.setPointScalesWithPoints(pointScaleWithPoints);
        user.setBadgesArray(badges);

        return user;
    }

    public PointScaleWithPointsEntity toPointScaleWithPointsEntity(PointScaleWithPoints pointScaleWithPoints){
        PointScaleWithPointsEntity entity = new PointScaleWithPointsEntity();
        entity.setPointScaleEntity(toPointScaleEntity(pointScaleWithPoints.getPointScale()));
        entity.setPoints(pointScaleWithPoints.getPoints());
        return entity;
    }

    public PointScaleWithPoints toPointScaleWithPoints(PointScaleWithPointsEntity pointScaleWithPointsEntity){
        PointScaleWithPoints pointScaleWithPoints = new PointScaleWithPoints();
        pointScaleWithPoints.setPointScale(toPointScale(pointScaleWithPointsEntity.getPointScaleEntity()));
        pointScaleWithPoints.setPoints(pointScaleWithPointsEntity.getPoints());
        return pointScaleWithPoints;
    }

   public ApplicationEntity createApplicationEntity(Integer id){
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationID(id);
        applicationEntity.setBagdes(new ArrayList<BadgeEntity>());
        applicationEntity.setEvents(new ArrayList<EventEntity>());
        applicationEntity.setPointScales(new ArrayList<PointScaleEntity>());
        applicationEntity.setRules(new ArrayList<RuleEntity>());
        applicationEntity.setUsers(new ArrayList<UserEntity>());
        return applicationEntity;
   }

}
