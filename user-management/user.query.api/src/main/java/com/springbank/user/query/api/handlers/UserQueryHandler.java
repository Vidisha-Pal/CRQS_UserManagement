package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;

import java.util.List;

public interface UserQueryHandler {

    public UserLookupResponse findUserById(FindUserByIdQuery findUserByIdQuery);

    public UserLookupResponse findAllUsers(FindAllUsersQuery findAllUsersQuery);

    public UserLookupResponse searchUsers(SearchUsersQuery searchUsersQuer);
}
