package com.lambdaschool.watermyplants;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.User;
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

        User testuser1 = new User();
        testuser1.setUsername("admin");
        testuser1.setPassword("testpass");
        testuser1.setPhoneNumber("+1(208)555-2121");

        User testuser2 = new User();
        testuser2.setUsername("tester");
        testuser2.setPassword("password");
        testuser2.setPhoneNumber("+1(206)555-2929");

        Plant p1 = new Plant();
        p1.setPlantname("flower");
        p1.setIntervalinhrs(24);
        p1.setUser(testuser1);

        Plant p2 = new Plant();
        p2.setPlantname("succulent");
        p2.setIntervalinhrs(36);
        p2.setUser(testuser2);

        testuser1.getPlantList().add(p1);
        testuser2.getPlantList().add(p2);

        userservice.save(testuser1);
        userservice.save(testuser2);

        plantservice.save(p1);
        plantservice.save(p2);

    }
}
