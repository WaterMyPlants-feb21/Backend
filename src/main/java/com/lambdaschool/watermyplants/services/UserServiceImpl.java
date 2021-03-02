package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        User user = userrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User with id: "+id+ " not found"));
        return user;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if(user.getUserid() != 0){
            newUser.setUserid(userrepos.findById(user.getUserid())
                    .orElseThrow(()->new EntityNotFoundException("User "+user.getUserid() + " not found!")).getUserid());
        }

        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPhoneNumber(user.getPhoneNumber());

        newUser.getPlantList().clear();
        for(Plant p:user.getPlantList()){
           Plant plant = plantService.findPlantById(p.getPlantid());

           newUser.getPlantList().add(plant);

        }
        userrepos.save(newUser);
        return newUser;
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

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(usersList::add);
        return usersList;
    }
}
