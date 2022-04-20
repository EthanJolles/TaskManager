package com.jolles.taskmanager.assembler;

import com.jolles.taskmanager.controller.TasksController;
import com.jolles.taskmanager.model.Tasks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TasksModelAssembler implements RepresentationModelAssembler<Tasks, EntityModel<Tasks>> {
    @Override
    public EntityModel<Tasks> toModel(Tasks task) {
        return EntityModel.of(task,
            linkTo(methodOn(TasksController.class).one(task.getId())).withSelfRel(),
            linkTo(methodOn(TasksController.class).all()).withRel("tasks"));
    }
}
