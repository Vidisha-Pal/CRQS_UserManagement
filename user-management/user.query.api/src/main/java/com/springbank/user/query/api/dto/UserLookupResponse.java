package com.springbank.user.query.api.dto;

import com.springbank.user.core.dto.BaseResponse;
import com.springbank.user.core.models.User;


import java.util.List;


public class UserLookupResponse extends BaseResponse {

    private List<User> users;
    public UserLookupResponse( List<User> users, String message) {
        super(message);
        this.users = users;
    }

    public UserLookupResponse( List<User> users) {
        super(null);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
