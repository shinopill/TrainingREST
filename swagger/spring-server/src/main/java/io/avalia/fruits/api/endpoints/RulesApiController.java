package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.model.Rule;
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

    public ResponseEntity<List<Rule>> getRules(Integer appKey) {

        // TODO: to do
        return null;
    }

    private RuleEntity toRuleEntity(Rule rule){
        RuleEntity entity = new RuleEntity();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setAppKey(rule.getAppKey());
        entity.setBadgeName(rule.getBadgeName());
        entity.setPointReward(rule.getPoints());
        entity.setPointScaleName(rule.getPointScaleName());
        return entity;
    }

    private Rule toRule(RuleEntity ruleEntity){
        Rule rule = new Rule();
        rule.setAppKey(ruleEntity.getAppKey());
        rule.setName(ruleEntity.getName());
        rule.setDescription(ruleEntity.getDescription());
        rule.setPoints(ruleEntity.getPointReward());
        rule.setPointScaleName(ruleEntity.getPointScaleName());
        rule.setBadgeName(ruleEntity.getBadgeName());
        return rule;
    }
}
