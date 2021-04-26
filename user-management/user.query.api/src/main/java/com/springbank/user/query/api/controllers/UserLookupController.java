package com.springbank.user.query.api.controllers;

import com.springbank.user.core.dto.BaseResponse;
import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    @GetMapping(path="/")
    public ResponseEntity<BaseResponse> getAllUsers() {
        try{
            var query = new FindAllUsersQuery();
            var users = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(users == null || users.getUsers() == null) return new ResponseEntity<>(new BaseResponse("No users found"), HttpStatus.NO_CONTENT);
            else return new ResponseEntity<>(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new ResponseEntity<>(new BaseResponse("Could not retrieve users"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/byId/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable String id) {
        try{
            System.out.println("getUserById");
            var query = new FindUserByIdQuery(id);
            var users = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(users == null || users.getUsers() == null) return new ResponseEntity<>(new BaseResponse("No users found"), HttpStatus.NO_CONTENT);
            else return new ResponseEntity<>(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            System.out.println("Error "+ e);
            return new ResponseEntity<>(new BaseResponse("Could not retrieve user"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/byFilter/{search}")
    public ResponseEntity<BaseResponse> getUserBySearchFilter(@PathVariable String search) {
        try{
            var query = new SearchUsersQuery(search);
            var users = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if(users == null || users.getUsers() == null) return new ResponseEntity<>(new BaseResponse("No users found"), HttpStatus.NO_CONTENT);
            else return new ResponseEntity<>(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            System.out.println("Error "+ e);
            return new ResponseEntity<>(new BaseResponse("Could not retrieve users"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
