package com.jolles.taskmanager.controller;

import com.jolles.taskmanager.assembler.TasksModelAssembler;
import com.jolles.taskmanager.exception.ResourceNotFoundException;
import com.jolles.taskmanager.model.Tasks;
import com.jolles.taskmanager.repository.TasksRepository;
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
public class TasksController {

    private final TasksRepository repository;
    private final TasksModelAssembler assembler;

    public TasksController(TasksRepository repository, TasksModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> create(@RequestBody Tasks task) {
        EntityModel<Tasks> entityModel = assembler.toModel(repository.save(task));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/tasks")
    public CollectionModel<EntityModel<Tasks>> all() {

        List<EntityModel<Tasks>> tasks = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks, linkTo(methodOn(TasksController.class).all()).withSelfRel());
    }

    @GetMapping("/tasks/{id}")
    public EntityModel<Tasks> one(@PathVariable Long id) {
        Tasks task = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existing task " + id));

        return assembler.toModel(task);
    }

    @PutMapping("/tasks/{id}")
    public String update(@PathVariable Long id) {
        return "Updated";
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable Long id) {
        return "Deleted";
    }
}
