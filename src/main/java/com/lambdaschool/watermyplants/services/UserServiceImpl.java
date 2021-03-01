package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private PlantService plantService;

    @Override
    public User findUserById(long id)
    {
        return userrepos.findById(id)
            .orElseThrow(()-> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if(user.getUserid() != 0){
            userrepos.findById(user.getUserid())
                .orElseThrow(()->new EntityNotFoundException("User "+user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPhoneNumber(user.getPhoneNumber());

        newUser.getPlantList().clear();
        for(Plant p:user.getPlantList()){
           Plant plant = plantService.findPlantById(p.getPlantid());

           newUser.getPlantList().add(plant);

        }
        return save(newUser);
    }

    @Transactional
    @Override
    public User update(User user,long id)
    {
        User updateUser = findUserById(id);

        if(user.getPassword() != null)
        {
            updateUser.setPassword(user.getPassword());
        }
        if(user.getPhoneNumber() != null)
        {
            updateUser.setPhoneNumber(user.getPhoneNumber());
        }
        return userrepos.save(updateUser);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
