package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    public ResponseEntity<Object> createRule(@ApiParam(value = "" ,required=true ) @RequestBody Rules rule) {
        // TODO: to do
        return null;
    }

    private RuleEntity toRuleEntity(Rules rule){
        RuleEntity entity = new RuleEntity();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setAppKey(rule.getAppKey());
        entity.setBadgeReward(rule.getBadge());
        entity.setPointReward(rule.getPoints());
        entity.setPointScale(rule.getPointScale());
        return entity;
    }

    private Rules toRule(RuleEntity ruleEntity){
        Rules rule = new Rules();
        rule.setAppKey(ruleEntity.getAppKey());
        rule.setName(ruleEntity.getName());
        rule.setDescription(ruleEntity.getDescription());
        rule.setPoints(ruleEntity.getPointReward());
        rule.setPointScale(ruleEntity.getPointScale());
        rule.setBadge(ruleEntity.getBadgeReward());
        return rule;
    }
}
