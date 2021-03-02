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
    private String plantname;

    @Column(nullable = false)
    private double intervalinhrs;

    @ManyToOne
    @JsonIgnoreProperties(value="plantList")
    private User user;

    public Plant() {
    }

    public Plant(long plantid, String plantname, double intervalinhrs, User user) {
        this.plantname = plantname;
        this.intervalinhrs = intervalinhrs;
        this.user = user;
    }

    public long getPlantid() {
        return plantid;
    }

    public void setPlantid(long plantid) {
        this.plantid = plantid;
    }

    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getIntervalinhrs() {
        return intervalinhrs;
    }

    public void setIntervalinhrs(double intervalinhrs) {
        this.intervalinhrs = intervalinhrs;
    }
}
