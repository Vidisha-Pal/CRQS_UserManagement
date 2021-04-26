package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/removeUser")
public class RemoveUserController {

    public final CommandGateway commandGateway;

    @Autowired
    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable String id){
        try{
            var command = new RemoveUserCommand ();
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("user deleted successfully"), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new BaseResponse("user could not be deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
