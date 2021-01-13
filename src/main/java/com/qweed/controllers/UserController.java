package com.qweed.controllers;

import com.qweed.logic.UserCollection;
import com.qweed.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.NoResultException;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user", produces = "application/hal+json")
public class UserController {

    @Autowired
    private UserCollection userCollection;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final long id) {
        return userCollection.getUser(id).map(p-> {
            userCollection.deleteUser(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(NoResultException::new);
    }

    @GetMapping("/list")
    public List getAllUsers() {
        return userCollection.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody final User userFromRequest) {
        User loggedInUser = null;

        try {
            loggedInUser = userCollection.login(userFromRequest.getUsername(), userFromRequest.getPassword());
        }
        catch (Exception e) {
            throw new NoResultException();
        }

        final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(userFromRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(loggedInUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable final long id) {
        return userCollection.getUser(id).map(ResponseEntity::ok).orElseThrow(NoResultException::new);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody final User userFromRequest) {
        User savedUser = userCollection.save(userFromRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userFromRequest) {
        User userUpdated = userCollection.save(userFromRequest);
        return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    }
}
