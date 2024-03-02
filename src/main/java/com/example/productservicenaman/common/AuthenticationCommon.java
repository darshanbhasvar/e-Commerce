package com.example.productservicenaman.common;

import com.example.productservicenaman.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommon {

    private RestTemplate restTemplate;

    public AuthenticationCommon(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken (String token){
        ResponseEntity<UserDto> userDtoResponseEntity =  restTemplate.postForEntity(
                "http://localhost:8181/users/validate/"+token,
                null,
                UserDto.class
        );

        if(userDtoResponseEntity.getBody() == null){
            return null;
        }
        return userDtoResponseEntity.getBody();
    }
}
