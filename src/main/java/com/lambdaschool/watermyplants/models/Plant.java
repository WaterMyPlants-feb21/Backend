package com.lambdaschool.watermyplants.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    private String plantname;

    @ManyToOne
    @JsonIgnoreProperties(value="plantlist")
    private User user;

    public Plant() {
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
}
