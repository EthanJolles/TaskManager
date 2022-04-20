package com.jolles.taskmanager.controller;

import com.jolles.taskmanager.assembler.UserModelAssembler;
import com.jolles.taskmanager.exception.ResourceNotFoundException;
import com.jolles.taskmanager.model.User;
import com.jolles.taskmanager.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;

    public UserController(UserRepository repository, UserModelAssembler assembler) {
        this.assembler = assembler;
        this.repository = repository;
    }


    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(repository.save(user));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found " + id));

        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    public String update(@PathVariable Long id) {
        return "Updated";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        return "Deleted";
    }
}
