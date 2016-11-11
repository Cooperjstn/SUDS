package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by stevenburris on 11/10/16.
 */
@Entity
@Table(name = "beers")
public class Beer {

    public enum Category {
        PILSNER,
        LAGER,
        PALE_ALE,
        BROWN_ALE,
        INDIAN_PALE_ALE,
        PORTER,
        STOUT,
        BELGIAN,
        WHEAT,
        LAMBIC,
        SOUR
    }

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String brewery;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    int rating;

    @Column(nullable = false)
    Category category;

    @ManyToOne
    User user;

    public Beer() {
    }

    public Beer(String name, String filename, String brewery, String description, int rating, Category category, User user) {
        this.name = name;
        this.filename = filename;
        this.brewery = brewery;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
