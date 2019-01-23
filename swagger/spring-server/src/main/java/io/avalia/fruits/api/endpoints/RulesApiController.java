package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.model.Rule;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public ResponseEntity<Object> createRule(Rule rule) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteRule(Rule rule) {
        return null;
    }

    @Override
    public ResponseEntity<Rule> getRule(Integer appKey, String ruleName) {
        return null;
    }

    public ResponseEntity<List<Rule>> getRules(Integer appKey) {

        // TODO: to do
        return null;
    }

    @Override
    public ResponseEntity<Object> updateRule(Integer appKey, String ruleName, Rule rule) {
        return null;
    }

    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        entity.setDescription(pointScale.getDescription());
        entity.setAppKey(pointScale.getAppKey());
        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setName(entity.getName());
        pointScale.setDescription(entity.getDescription());
        pointScale.setAppKey(entity.getAppKey());
        return pointScale;
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

    private RuleEntity toRuleEntity(Rule rule){
        RuleEntity entity = new RuleEntity();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setAppKey(rule.getAppKey());
        entity.setBadge(toBadgeEntity(rule.getBadge()));
        entity.setPointReward(rule.getPoints());
        entity.setPointScale(toPointScaleEntity(rule.getPointScale()));
        return entity;
    }

    private Rule toRule(RuleEntity ruleEntity){
        Rule rule = new Rule();
        rule.setAppKey(ruleEntity.getAppKey());
        rule.setName(ruleEntity.getName());
        rule.setDescription(ruleEntity.getDescription());
        rule.setPoints(ruleEntity.getPointReward());
        rule.setPointScale(toPointScale(ruleEntity.getPointScale()));
        rule.setBadge(toBadge(ruleEntity.getBadge()));
        return rule;
    }
}
