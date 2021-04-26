package com.springbank.user.query.api.handlers;

import com.springbank.user.core.models.User;
import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse findUserById(FindUserByIdQuery findUserByIdQuery) {
        UserLookupResponse response = null  ;
        Optional<User> users = userRepository.findById(findUserByIdQuery.getId());
        if( users.isPresent()) response = new UserLookupResponse(Arrays.asList(users.get()));
        return response;
    }

    @QueryHandler
    @Override
    public UserLookupResponse findAllUsers(FindAllUsersQuery findAllUsersQuery) {
       var users =  userRepository.findAll();
       return (new UserLookupResponse(users));
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery searchUsersQuery) {
        var users = userRepository.findByFilterRegex(searchUsersQuery.getFilter());
        return (new UserLookupResponse(users));
    }
}
