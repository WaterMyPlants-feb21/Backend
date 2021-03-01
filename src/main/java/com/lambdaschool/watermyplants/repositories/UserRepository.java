package com.lambdaschool.watermyplants.repositories;

import com.lambdaschool.watermyplants.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long>
{
}
