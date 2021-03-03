package com.lambdaschool.watermyplants.controllers;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.repositories.UserRepository;
import com.lambdaschool.watermyplants.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {
    @Autowired
    private PlantService plantserv;

    @Autowired
    private UserRepository userrepos;

    @GetMapping(value="", produces = {"application/json"})
    public ResponseEntity<?> getAllPlants(){
        List<Plant> plantlist = plantserv.findAllPlants();

        return new ResponseEntity<>(plantlist, HttpStatus.OK);
    }

    @GetMapping(value="/plant/{plantid}", produces = {"application/json"})
    public ResponseEntity<?> getPlantById(@PathVariable long plantid){
        Plant plant = plantserv.findPlantById(plantid);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @PostMapping(value="/plant", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewPlant(@RequestBody Plant newplant){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User workingUser = userrepos.findByUsername(authentication.getName());

        newplant.setPlantid(0);
        newplant.setUser(workingUser);
        Plant saved = plantserv.save(newplant);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value="/plant/{plantid}")
    public ResponseEntity<?> putUpdatePlant(@RequestBody Plant updateplant, @PathVariable long plantid){
        Plant updatedplant = plantserv.save(updateplant);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @PatchMapping(value="/plant/{plantid}", consumes = {"application/json"})
    public ResponseEntity<?> patchPlant(@PathVariable long plantid, @RequestBody Plant plant){
        plantserv.updatePlant(plant, plantid);
        return new ResponseEntity<>(null, null , HttpStatus.OK);
    }

    @DeleteMapping(value = "/plant/{plantid}")
    public ResponseEntity<?> deletePlantById(@PathVariable long plantid){
        plantserv.deletePlantById(plantid);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}
