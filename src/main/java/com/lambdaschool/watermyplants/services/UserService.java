package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(User user);
    User findUserById(long id);
}
