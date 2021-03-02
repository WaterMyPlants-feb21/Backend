package com.lambdaschool.watermyplants;

import com.lambdaschool.watermyplants.models.Plant;
import com.lambdaschool.watermyplants.models.Role;
import com.lambdaschool.watermyplants.models.User;
import com.lambdaschool.watermyplants.models.UserRole;
import com.lambdaschool.watermyplants.services.PlantService;
import com.lambdaschool.watermyplants.services.RoleService;
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

    @Autowired
    RoleService roleService;
    @Transactional
    @Override
    public void run(String[] args) throws Exception{
        userservice.deleteAll();
        plantservice.deleteAll();
        roleService.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        roleService.save(r1);
        roleService.save(r2);

        User testuser1 = new User();
        testuser1.setUsername("admin");
        testuser1.setPassword("hello");
        testuser1.setPhoneNumber("2084212696");
        testuser1.getRoles().add(new UserRole(testuser1, r1));

        User testuser2 = new User();
        testuser2.setUsername("testuser");
        testuser2.setPassword("world");
        testuser2.setPhoneNumber("2485556351");
        testuser2.getRoles().add(new UserRole(testuser2, r2));

        testuser1 = userservice.save(testuser1);
        testuser2 = userservice.save(testuser2);

        Plant p1 = new Plant();
        p1.setPlantname("flower");
        p1.setIntervalinhrs(24);
        p1.setUser(testuser1);
        plantservice.save(p1);
    }
}
