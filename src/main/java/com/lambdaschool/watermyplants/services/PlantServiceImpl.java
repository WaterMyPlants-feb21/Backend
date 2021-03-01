package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.repositories.PlantRepository;
import com.lambdaschool.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("plantservice")
public class PlantServiceImpl implements PlantService{
    @Autowired
    private UserRepository userrepo;
    @Autowired
    private PlantRepository plantrepo;

    @Override
    public List<Plant> findAllPlants() {
        List<Plant> plantsList = new ArrayList<>();
        plantrepo.findAll().iterator().forEachRemaining(plantsList::add);
        return plantsList;
    }

    @Override
    public Plant save(Plant plant) {
        Plant tempplant = new Plant();
        tempplant.setPlantname(plant.getPlantname());
        if(userrepo.findById(plant.getUser().getUserid()).isPresent()){
            tempplant.setUser(userrepo.findById(plant.getUser().getUserid()).orElseThrow(()-> new EntityNotFoundException("user with id " + plant.getUser().getUserid() + " does not exist")));
        }else{
            throw new EntityNotFoundException("Could not find user with id: "+plant.getUser().getUserid());
        }

        plantrepo.save(tempplant);
        return tempplant;
    }

    @Override
    public void deletePlantById(long plantid) {
        if(plantrepo.findById(plantid).isPresent()){
            throw new EntityNotFoundException("Could not find plant with id: "+plantid);
        } else{
            plantrepo.deleteById(plantid);
        }
    }

    @Override
    public void updatePlant(Plant plant, long plantid) {
        if(plantrepo.findById(plantid).isPresent()){

            Plant updateplant = new Plant();

            if(plant.getPlantid()!=0){
                updateplant.setPlantid(plant.getPlantid());
            }
            if(plant.getPlantname()!=null){
                updateplant.setPlantname(plant.getPlantname());
            }
            if(plant.getUser()!=null){
                if(userrepo.findById(plant.getUser().getUserid()).isPresent()){
                    updateplant.setUser(userrepo.findById(plant.getUser().getUserid()).orElseThrow(
                            ()-> new EntityNotFoundException("User with id: "+plant.getUser().getUserid()+" not found")
                    ));
                } else{
                    throw new EntityNotFoundException("User with id: "+plant.getUser().getUserid()+" not found");
                }
            }
        }

    }

    @Override
    public Plant findPlantById(long plantid) {
        Plant plant = plantrepo.findById(plantid).orElseThrow(()-> new EntityNotFoundException("plant with id: "+plantid+" not found"));
        return plant;
    }

    @Override
    public void deleteAll(){
        plantrepo.deleteAll();
    }
}
