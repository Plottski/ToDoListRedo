package Thetododolist.tododolist.Entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    LocalDateTime creationDate;

    @Column(nullable = false)
    User creator;

    @Column(nullable = false)
    String title;

    @Column
    String description;

    @Column
    LocalDateTime dueDate;

    @Column
    LocalDateTime closedDate;

    @Column
    User closer;

    //I don't think I want an id contructor since the DB autogenerates. I need to ask J.

    //public Item(long id, LocalDateTime creationDate, User creator, String title, String description,
         //      LocalDateTime dueDate, LocalDateTime closedDate, User closer) {
        //this.id = id;
    public Item(LocalDateTime creationDate, User creator, String title, String description,
                    LocalDateTime dueDate, LocalDateTime closedDate, User closer) {
        this.creationDate = creationDate;
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.closedDate = closedDate;
        this.closer = closer;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public User getCloser() {
        return closer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public void setCloser(User closer) {
        this.closer = closer;
    }
}
