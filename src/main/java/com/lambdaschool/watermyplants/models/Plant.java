package com.lambdaschool.watermyplants.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="plants")
public class Plant extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    @Column(nullable=false)
    private String nickname;

    private String species;

    @Column(nullable = false)
    private double h2oFrequency;

    @ManyToOne
    @JsonIgnoreProperties(value="plantList")
    private User user;

    public Plant() {
    }

    public Plant(long plantid, String plantname, double intervalinhrs, User user) {
        this.plantid = plantid;
        this.nickname = plantname;
        this.h2oFrequency = intervalinhrs;
        this.user = user;
    }

    public long getPlantid() {
        return plantid;
    }

    public void setPlantid(long plantid) {
        this.plantid = plantid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String plantname) {
        this.nickname = plantname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getH2oFrequency() {
        return h2oFrequency;
    }

    public void setH2oFrequency(double intervalinhrs) {
        this.h2oFrequency = intervalinhrs;
    }
}
