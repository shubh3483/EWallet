package com.example.WalletService1.Service;

import com.example.WalletService1.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    @Autowired
    RestTemplate restTemplate;
    public User getAUser(String userId){
        final String uri = "http://127.0.0.1:9090/userbyid/{userId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);

        ResponseEntity<User> forEntity =
                restTemplate.getForEntity(uri,  User.class,params);
        if(forEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return null;
        }
        return forEntity.getBody();
    }

}
