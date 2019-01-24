package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.EventsApi;
import io.avalia.fruits.api.model.Event;
import io.avalia.fruits.api.model.PointScale;
import io.avalia.fruits.api.model.PointScaleWithPoints;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.*;
import io.avalia.fruits.repositories.*;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
public class EventsApiController implements EventsApi {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    private Tools tools = new Tools();

    public ResponseEntity<Object> sendEvent(@ApiParam(value = "" ,required=true ) @RequestBody Event event){
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
            users.add(user);
        }



        for (RuleEntity r : rules) {
            if(r.getEventType().equalsIgnoreCase(event.getEventType()) && event.getProperties().contains(r.getProperty())){
                // Si on doit faire tout le temps l'event
                if(r.getNumberOfTimesToGetTheAward() == 1){

                    List<PointScaleWithPointsEntity> pointScaleWithPointsEntities = user.getPointScaleWithPoints();
                    PointScaleWithPointsEntity pointScaleToChange = null;

                    //On regarde si on a déjé un pointScaleWithPoints pour l'event
                    for(PointScaleWithPointsEntity pswp : pointScaleWithPointsEntities){
                        if(pswp.getPointScaleEntity().getName().equalsIgnoreCase(r.getPointScale().getName())){
                            pointScaleToChange = pswp;
                            pswp.setPoints(pswp.getPoints() + r.getPoints());
                            break;
                        }
                    }

                    //Sinon on le crée
                    if(pointScaleToChange == null){
                        PointScaleEntity ps = pointScaleRepository.findByNameAndAppKey(r.getPointScale().getName(),r.getAppKey());
                        pointScaleToChange = new PointScaleWithPointsEntity();
                        if(ps == null){
                            pointScaleToChange.setPointScaleEntity(r.getPointScale());
                        }else{
                            pointScaleToChange.setPointScaleEntity(ps);
                        }
                        pointScaleToChange.setPoints(r.getPoints());
                        pointScaleWithPointsEntities.add(pointScaleToChange);
                    }

                    //On regarde si il y a déjà un bage
                    List<BadgeEntity> badges = user.getBadges();
                    BadgeEntity badgeToGet = null;
                    for(BadgeEntity b : badges){
                        if(b.getName().equalsIgnoreCase(r.getBadge().getName()) && b.getDescription().equalsIgnoreCase(r.getBadge().getDescription()))
                            badgeToGet = b;
                            break;
                    }

                    //Sinon on lui ajoute le bagde
                    if(badgeToGet == null){
                        badges.add(r.getBadge());
                    }

                    events.add(tools.toEventEntity(event));
                    applicationRepository.save(app);
                    return ResponseEntity.accepted().build();
                }else{

                    int numberOfTimes = 1;
                    for(EventEntity e : events){
                        if(e.getUsername().equalsIgnoreCase(event.getUsername()) &&
                                e.getEventType().equalsIgnoreCase(event.getEventType()) &&
                                e.getProperties().contains(r.getProperty())){
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
                            PointScaleEntity ps = pointScaleRepository.findByNameAndAppKey(r.getPointScale().getName(),r.getAppKey());
                            pointScaleToChange = new PointScaleWithPointsEntity();
                            if(ps == null){
                                pointScaleToChange.setPointScaleEntity(r.getPointScale());
                            }else{
                                pointScaleToChange.setPointScaleEntity(ps);
                            }
                            pointScaleToChange.setPoints(r.getPoints());
                            pointScaleWithPointsEntities.add(pointScaleToChange);
                        }

                        List<BadgeEntity> badges = user.getBadges();
                        BadgeEntity badgeToGet = null;
                        for(BadgeEntity b : badges){
                            if(b.getName().equalsIgnoreCase(r.getBadge().getName()) && b.getDescription().equalsIgnoreCase(r.getBadge().getDescription()))
                                badgeToGet = b;
                            break;
                        }

                        if(badgeToGet == null){
                            badges.add(r.getBadge());
                        }

                        events.add(tools.toEventEntity(event));
                        applicationRepository.save(app);
                        return ResponseEntity.accepted().build();

                    }else{
                        events.add(tools.toEventEntity(event));
                        applicationRepository.save(app);
                        return ResponseEntity.accepted().build();
                    }
                }
            }
        }

        return ResponseEntity.badRequest().build();

    }


}

