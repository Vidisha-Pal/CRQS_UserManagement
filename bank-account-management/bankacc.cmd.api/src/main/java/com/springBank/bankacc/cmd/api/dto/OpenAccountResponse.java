package com.springBank.bankacc.cmd.api.dto;

import com.springBank.bankacc.core.dto.BaseResponse;


public class OpenAccountResponse extends BaseResponse {

    private String id;

    public OpenAccountResponse(String id, String message){
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
