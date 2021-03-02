package com.lambdaschool.watermyplants;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.Role;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.models.UserRole;
import com.lambdaschool.watermyplants.services.PlantService;
import com.lambdaschool.watermyplants.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value="enabled",
        havingValue="true",
        matchIfMissing = true
)
@Component
public class SeedData
        implements CommandLineRunner{

    @Autowired
    PlantService plantservice;

    @Autowired
    UserService userservice;

    @Transactional
    @Override
    public void run(String[] args) throws Exception{
        userservice.deleteAll();
        plantservice.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        User testuser1 = new User();
        testuser1.setUsername("admin");
        //unencrypted password = adminpass
        testuser1.setPasswordNoEncrypt("$2y$12$UZekIqOPntRiqgW0Cuzf4.tMO6ZPvRcHfGxDPMkHJ.z9dFw8STjz.");
        testuser1.setPhoneNumber("2084212696");
        testuser1.getRoles().add(new UserRole(testuser1, r1));

        User testuser2 = new User();
        testuser2.setUsername("testuser");
        //unencrypted password = password
        testuser2.setPasswordNoEncrypt("$2y$12$UQEpomEzFJz6wEyzUKUhoOv9QElCC9/x21Y94MyLBkP/c1H1CEvW6");
        testuser2.setPhoneNumber("2485556351");
        testuser2.getRoles().add(new UserRole(testuser2, r2));

        testuser1 = userservice.save(testuser1);
        testuser2 = userservice.save(testuser2);

        Plant p1 = new Plant();
        p1.setPlantid(11);
        p1.setPlantname("flower");
        p1.setIntervalinhrs(24);
        p1.setUser(testuser1);

    }
}
