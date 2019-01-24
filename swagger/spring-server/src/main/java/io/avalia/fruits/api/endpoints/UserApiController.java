package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.UsersApi;
import io.avalia.fruits.api.model.User;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.ApplicationEntity;
import io.avalia.fruits.entities.PointScaleWithPointsEntity;
import io.avalia.fruits.entities.UserEntity;
import io.avalia.fruits.repositories.ApplicationRepository;
import io.avalia.fruits.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UserApiController implements UsersApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserRepository userRepository;

    private Tools tools = new Tools();

    @Override
    public ResponseEntity<Integer> getPoints(@ApiParam(value = "",required=true ) @PathVariable("pointScaleName") String pointScaleName,
                                      @ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey,
                                      @ApiParam(value = "",required=true ) @PathVariable("username") String username){
        UserEntity res = userRepository.findUserEntityByNameAndAppKey(username, appKey);
        if(res!= null) {
            List<PointScaleWithPointsEntity> pointScaleWithPointsEntities = res.getPointScaleWithPoints();
            int points;

            for (int i = 0; i < pointScaleWithPointsEntities.size(); ++i) {
                if (pointScaleWithPointsEntities.get(i).getPointScaleEntity().getName() == pointScaleName) {
                    points = pointScaleWithPointsEntities.get(i).getPoints();
                    return ResponseEntity.ok(points);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public  ResponseEntity<User> getUser(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey,
                                         @ApiParam(value = "",required=true ) @PathVariable("username") String username){
        UserEntity res = userRepository.findUserEntityByNameAndAppKey(username, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toUser(res));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public  ResponseEntity<List<User>> getUsers(@ApiParam(value = "" ,required=true ) @RequestHeader(value="appKey", required=true) Integer appKey) {
        List<UserEntity> entities = userRepository.findAllUserEntityByAppKey(appKey);
        if (entities != null) {
            List<User> users = new ArrayList<>();
            for (UserEntity u : entities) {
                users.add(tools.toUser(u));
            }
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().build();
    }
}
