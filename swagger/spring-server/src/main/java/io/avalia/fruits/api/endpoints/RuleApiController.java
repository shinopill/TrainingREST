package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.model.Rule;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    private Tools tools;

    @Override
    public ResponseEntity<Object> createRule(Rule rule) {
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (res == null) {
            ruleRepository.save(res);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteRule(Rule rule) {
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (res != null) {
            ruleRepository.deleteRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Rule> getRule(Integer appKey, String ruleName) {
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(ruleName, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toRule(res));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Rule>> getRules(Integer appKey) {
        List<RuleEntity> res = ruleRepository.findAllByAppKey(appKey);
        List<Rule> ret = new ArrayList<>();
        for (RuleEntity r : res) {
            ret.add(tools.toRule(r));
        }

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> updateRule(Integer appKey, String ruleName, Rule rule) {
        return null; // TODO: todo
    }
}
