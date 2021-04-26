package com.springbank.user.cmd.api.dto;

import com.springbank.user.core.dto.BaseResponse;
import lombok.Data;

public class UpdateUserResponse extends BaseResponse {

    private String id;

    public UpdateUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }

}
