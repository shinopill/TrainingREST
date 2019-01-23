package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.UsersApi;
import io.avalia.fruits.api.model.User;
import io.avalia.fruits.api.util.Tools;
import io.avalia.fruits.entities.PointScaleWithPointsEntity;
import io.avalia.fruits.entities.UserEntity;
import io.avalia.fruits.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UserApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    private Tools tools;

    @Override
    public ResponseEntity<Integer> getPoints(String pointScaleName, Integer appKey, String username){
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
    public ResponseEntity<User> getUser(Integer appKey, String username) {
        UserEntity res = userRepository.findUserEntityByNameAndAppKey(username, appKey);
        if (res != null) {
            return ResponseEntity.ok(tools.toUser(res));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers(Integer appKey) {
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
