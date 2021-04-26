package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.cmd.api.dto.UpdateUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
public class UpdateUserController {

    public final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController (CommandGateway commandGateway ) {
        this.commandGateway = commandGateway;
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable String id,
                                                         @Valid @RequestBody UpdateUserCommand command) {
            try{
                command.setId(id);
                commandGateway.send(command);
                return new ResponseEntity<>(new UpdateUserResponse(command.getId(), "user updated successfully"), HttpStatus.CREATED);
            }catch(Exception e) {
                System.out.println ("Error : "+ e);
                return new ResponseEntity<>(new UpdateUserResponse (command.getId(), "Error while processing user update"), HttpStatus.BAD_REQUEST);
            }
    }

}
