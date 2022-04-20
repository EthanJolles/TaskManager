package com.jolles.taskmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Task")
public class Tasks {

    public Tasks() {
    }

    public Tasks(String description, String category, String dateCreated) {
        this.description = description;
        this.category = category;
        this.dateCreated = dateCreated;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String category;
    private String dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return Objects.equals(id, tasks.id) && Objects.equals(description, tasks.description) && Objects.equals(category, tasks.category) && Objects.equals(dateCreated, tasks.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, category, dateCreated);
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}