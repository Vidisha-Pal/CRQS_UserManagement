package com.springBank.bankacc.cmd.api.controllers;

import com.springBank.bankacc.cmd.api.commands.OpenAccountCommand;
import com.springBank.bankacc.cmd.api.dto.OpenAccountResponse;
import com.springBank.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/openBankAccount")
public class OpenAccountController {

    private final CommandGateway commandGateway;

    @Autowired
    public OpenAccountController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @RequestMapping(path="/openAccount")
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        try{
            var id = UUID.randomUUID().toString();
            command.setId(id);
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Account created."), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new ResponseEntity<>(new BaseResponse("Could not create account"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
