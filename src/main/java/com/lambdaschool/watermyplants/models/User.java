package com.lambdaschool.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(unique = true)
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",allowSetters = true)
    private List<Plant> plantList = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
            allowSetters = true)
    private Set<UserRole> roles = new HashSet<>();

    public User()
    {
    }

    public User(
        String username,
        String password)
    {
        setUsername(username);
        setPassword(password);
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPasswordNoEncrypt(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public List<Plant> getPlantList()
    {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList)
    {
        this.plantList = plantList;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(UserRole r:this.roles){
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(myRole));
        }
        return authorities;

    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", plantList=" + plantList +
                '}';
    }
}
