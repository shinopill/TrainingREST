package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RuleApi;
import io.avalia.fruits.api.model.Rule;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

public class RuleApiController implements RuleApi {

    @Autowired
    RuleRepository ruleRepository;

    public ResponseEntity<Object> createRule(Rule rule) {
        RuleEntity newRuleEntity = ruleRepository.findByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (newRuleEntity == null) {
            ruleRepository.save(newRuleEntity);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Object> deleteRule(Rule rule) {
        RuleEntity res = ruleRepository.deleteRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (res != null) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Rule> getRule(Integer appKey, String ruleName) {
        RuleEntity res = ruleRepository.findByNameAndAppKey(ruleName, appKey);
        return ResponseEntity.ok(toRule(res));
    }

    @Override
    public ResponseEntity<Object> updateRule(Integer appKey, String ruleName, Rule rule) {
        RuleEntity res = ruleRepository.deleteRuleEntityByNameAndAppKey(ruleName, appKey);
        if(res != null){
            ruleRepository.save(toRuleEntity(rule));
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    private RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();
        entity.setName(rule.getName());
        entity.setDescription(rule.getDescription());
        entity.setAppKey(rule.getAppKey());
        entity.setBadgeName(rule.getBadgeName());
        entity.setPointReward(rule.getPoints());
        entity.setPointScaleName(rule.getPointScaleName());
        return entity;
    }

    private Rule toRule(RuleEntity ruleEntity) {
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
