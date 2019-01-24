package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.model.Event;
import io.avalia.fruits.api.model.PointScaleWithPoints;
import io.avalia.fruits.entities.*;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.EventRepository;
import io.avalia.fruits.repositories.UserRepository;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ApplicationRepository applicationRepository;


    public ResponseEntity<Object> sendEvent(Event event) {
        Integer appKey = event.getAppKey();
        ApplicationEntity app = applicationRepository.findByApplicationID(appKey);
        List<RuleEntity> rules = app.getRules();
        List<UserEntity> users = app.getUsers();
        List<EventEntity> events = app.getEvents();

        UserEntity user = null;
        for(UserEntity u : users ){
            if(u.getUsername().equalsIgnoreCase(event.getUsername())){
                user = u;
                break;
            }
        }

        if (user == null) {
            user = new UserEntity();
            user.setAppKey(event.getAppKey());
            user.setBadges(new ArrayList<BadgeEntity>());
            user.setPointScaleWithPoints(new ArrayList<PointScaleWithPointsEntity>());
            user.setUsername(event.getUsername());
        }
        for (RuleEntity r : rules) {
            if(r.getEventType().equalsIgnoreCase(event.getEventType())){
                if(r.getNumberOfTimesToGetTheAward() == 1){

                    List<PointScaleWithPointsEntity> pointScaleWithPointsEntities = user.getPointScaleWithPoints();
                    PointScaleWithPointsEntity pointScaleToChange = null;
                    for(PointScaleWithPointsEntity pswp : pointScaleWithPointsEntities){
                        if(pswp.getPointScaleEntity().getName().equalsIgnoreCase(r.getPointScale().getName())){
                            pointScaleToChange = pswp;
                            pointScaleToChange.setPoints(pswp.getPoints() + r.getPoints());
                            break;
                        }
                    }

                    if(pointScaleToChange == null){
                        pointScaleToChange = new PointScaleWithPointsEntity();
                        pointScaleToChange.setPoints(r.getPoints());
                        pointScaleToChange.setPointScaleEntity(r.getPointScale());
                    }

                    List<BadgeEntity> badges = user.getBadges();
                    BadgeEntity badgeToGet = null;
                    if(!badges.contains(r.getBadge())){
                        badges.add(r.getBadge());
                    }

                    users.add(user);
                    applicationRepository.save(app);
                }else{
                    int numberOfTimes = 0;
                    for(EventEntity e : events){
                        if(e.getUsername().equalsIgnoreCase(event.getUsername()) &&
                                e.getEventType().equalsIgnoreCase(event.getEventType())){
                            numberOfTimes++;
                        }
                    }

                    if(numberOfTimes == r.getNumberOfTimesToGetTheAward()){
                        List<PointScaleWithPointsEntity> pointScaleWithPointsEntities = user.getPointScaleWithPoints();
                        PointScaleWithPointsEntity pointScaleToChange = null;
                        for(PointScaleWithPointsEntity pswp : pointScaleWithPointsEntities){
                            if(pswp.getPointScaleEntity().getName().equalsIgnoreCase(r.getPointScale().getName())){
                                pointScaleToChange = pswp;
                                pointScaleToChange.setPoints(pswp.getPoints() + r.getPoints());
                                break;
                            }
                        }

                        if(pointScaleToChange == null){
                            pointScaleToChange = new PointScaleWithPointsEntity();
                            pointScaleToChange.setPoints(r.getPoints());
                            pointScaleToChange.setPointScaleEntity(r.getPointScale());
                        }

                        List<BadgeEntity> badges = user.getBadges();
                        BadgeEntity badgeToGet = null;
                        if(!badges.contains(r.getBadge())){
                            badges.add(r.getBadge());
                        }

                        users.add(user);
                        applicationRepository.save(app);

                    }
                }
            }
        }

        return ResponseEntity.accepted().build();

    }
}
