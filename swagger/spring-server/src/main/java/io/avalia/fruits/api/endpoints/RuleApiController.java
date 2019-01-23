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

public class RuleApiController implements RulesApi {

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
}
