package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.model.Rule;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    private Tools tools;

    @Override
    public ResponseEntity<Object> createRule(Rule rule) {
        Integer appKey = rule.getAppKey();
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (res == null) {
            ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
            if(app == null){
                app = tools.createApplicaitonEntity(appKey);
            }

            List<BadgeEntity> badges = app.getBagdes();
            List<PointScaleEntity> pointScales = app.getPointScales();

            if(!badges.contains(tools.toBadgeEntity(rule.getBadge()))){
              badges.add(tools.toBadgeEntity(rule.getBadge()));
            }

            if(!badges.contains(tools.toPointScaleEntity(rule.getPointScale()))){
                pointScales.add(tools.toPointScaleEntity(rule.getPointScale()));
            }

            List<RuleEntity> rules = app.getRules();
            rules.add(tools.toRuleEntity(rule));
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
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
        if(app != null) {
            List<RuleEntity> res = app.getRules();
            List<Rule> ret = new ArrayList<>();
            for (RuleEntity r : res) {
                ret.add(tools.toRule(r));
            }

            return ResponseEntity.ok(ret);
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Object> updateRule(Integer appKey, String ruleName, Rule rule) {
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
        if(app != null){
            List<RuleEntity> rules = app.getRules();
            for (RuleEntity r : rules) {
                if(r.getName().equalsIgnoreCase(ruleName)){
                    List<BadgeEntity> badges = app.getBagdes();
                    List<PointScaleEntity> pointScales = app.getPointScales();

                    if(!badges.contains(tools.toBadgeEntity(rule.getBadge()))){
                        badges.add(tools.toBadgeEntity(rule.getBadge()));
                        r.setBadge(tools.toBadgeEntity(rule.getBadge()));
                    }

                    if(!badges.contains(tools.toPointScaleEntity(rule.getPointScale()))){
                        pointScales.add(tools.toPointScaleEntity(rule.getPointScale()));
                        r.setPointScale(tools.toPointScaleEntity(rule.getPointScale()));
                    }

                    r.setDescription(rule.getDescription());
                    r.setEventType(rule.getEventType());
                    r.setName(rule.getName());
                    r.setNumberOfTimesToGetTheAward(rule.getNumberOfTimesToGetTheAward());
                    r.setPoints(rule.getPoints());

                }

            }

            applicationRepository.save(app);
            return ResponseEntity.accepted().build();

        }

        return ResponseEntity.badRequest().build();
    }

}
