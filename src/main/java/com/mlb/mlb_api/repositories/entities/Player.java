package com.mlb.mlb_api.repositories.entities;


import com.mlb.mlb_api.controllers.dto.PlayerDTO;

import javax.persistence.*;

@Entity // also after creating this build and you will see the table in mysql without the repo .
@Table(name="player")
public class Player {

    @Id // multi id entities are available make sure to import persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY) // before .AUTO was used but now updates happen
    @Column(name="id", nullable = false, updatable = false)
    private Integer id;

    @Column(name="name") // name can be removed if both (property and column) are the same
    private String name;

    @Column(name="age")
    private Integer age;

    @Column(name = "years_of_experience") //make sure
    private Double yearsOfExperience;

    @Column
    private Double rating;

    public Player() { // no arguments constructor needed for hibernate to instantiate Player
    }

    public Player(PlayerDTO playerDTO) {
        this.id = id;
        this.name = playerDTO.getName();
        this.age = playerDTO.getAge();
        this.yearsOfExperience = playerDTO.getYearsOfExperience();
        this.rating = playerDTO.getRating();
    }

    // ALL getter and setter are needed for hibernate
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}




