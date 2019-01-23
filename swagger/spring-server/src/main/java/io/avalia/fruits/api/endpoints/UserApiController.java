package io.avalia.fruits.api.endpoints;


import io.avalia.fruits.api.UsersApi;
import io.avalia.fruits.api.model.User;
import io.avalia.fruits.api.util.Tools;
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
    public ResponseEntity<User> getUser(Integer appKey, String username){
        UserEntity res = userRepository.findUserEntityByNameAndAppKey(username, appKey);
        return ResponseEntity.ok(tools.toUser(res));
    }

    @Override
    public ResponseEntity<List<User>> getUsers(Integer appKey){
        List<UserEntity> entities = userRepository.findAllUserEntityByAppKey(appKey);
        List<User> users = new ArrayList<>();
        for(UserEntity u : entities){
            users.add(tools.toUser(u));
        }
        return ResponseEntity.ok(users);
    }
}
