package com.lambdaschool.watermyplants.controllers;

import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{userid}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable long userid)
    {
       User user = userService.findUserById(userid);

       return new ResponseEntity<>(user,
           HttpStatus.OK);
    }

    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/user",consumes = "application/json")
    public ResponseEntity<?> addUser(@Valid @RequestBody User newUser)throws
                                                                      URISyntaxException
    {
        newUser.setUserid(0);
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(newUser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

       return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }

    @PatchMapping(value = "/user/{userid}",consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long userid)
    {
        userService.update(updateUser,userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
