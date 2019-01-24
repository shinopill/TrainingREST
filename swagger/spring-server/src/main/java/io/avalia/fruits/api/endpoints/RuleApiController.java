package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.RulesApi;
import io.avalia.fruits.api.model.Badge;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.model.Rule;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.BadgeEntity;
import io.avalia.fruits.entities.PointScaleEntity;
import io.avalia.fruits.entities.RuleEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Transactional
@Controller
public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    private Tools tools = new Tools();

    @Override
    public  ResponseEntity<Object> createRule(@ApiParam(value = "" ,required=true ) @RequestBody Rule rule) {
        Integer appKey = rule.getAppKey();
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(rule.getName(), rule.getAppKey());
        if (res == null) {
            ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
            if(app == null){
                app = tools.createApplicationEntity(appKey);
            }

            List<BadgeEntity> badges = app.getBagdes();
            List<PointScaleEntity> pointScales = app.getPointScales();
            boolean ishere = false;
            for(BadgeEntity b : badges){
                if(b.getName().equalsIgnoreCase(rule.getBadge().getName()) && b.getDescription().equalsIgnoreCase(rule.getBadge().getDescription())){
                    ishere = true;
                    break;
                }
            }

            if(!ishere){
                badges.add(tools.toBadgeEntity(rule.getBadge()));
            }

            ishere=false;

            for(PointScaleEntity ps : pointScales){
                if(ps.getName().equalsIgnoreCase(rule.getBadge().getName()) && ps.getDescription().equalsIgnoreCase(rule.getBadge().getDescription())){
                    ishere = true;
                    break;
                }
            }

            if(!ishere){
                pointScales.add(tools.toPointScaleEntity(rule.getPointScale()));
            }

            List<RuleEntity> rules = app.getRules();
            rules.add(tools.toRuleEntity(rule));
            applicationRepository.save(app);

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteRule(@ApiParam(value = "" ,required=true ) @RequestBody Rule rule) {
        ApplicationEntity app = applicationRepository.findByApplicationID(rule.getAppKey());
        if(app != null){
        List<RuleEntity> rules = app.getRules();
        List<RuleEntity> rulescopy = new ArrayList<>(rules);
        for(RuleEntity r : rulescopy){
            if(r.getName().equalsIgnoreCase(rule.getName()) && r.getDescription().equalsIgnoreCase(rule.getDescription())){
                rules.remove(r);
                ruleRepository.deleteRuleEntityByNameAndAppKey(rule.getName(),rule.getAppKey());
            }

        }
            applicationRepository.save(app);
            return  ResponseEntity.ok().build();

        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Rule> getRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey,
                                        @ApiParam(value = "",required=true ) @PathVariable("ruleName") String ruleName) {
        RuleEntity res = ruleRepository.findRuleEntityByNameAndAppKey(ruleName, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toRule(res));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Rule>> getRules(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey) {
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
    public  ResponseEntity<Object> updateRule(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey,
                                              @ApiParam(value = "",required=true ) @PathVariable("ruleName") String ruleName,
                                              @ApiParam(value = "" ,required=true ) @RequestBody Rule rule){
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
