package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Plant;

import java.util.List;

public interface PlantService {
    Plant save(Plant plant);
    void deletePlantById(long plantid);
    void updatePlant(Plant plant, long plantid);
    List<Plant> findAllPlants();
    Plant findPlantById(long plantid);
    void deleteAll();
}
