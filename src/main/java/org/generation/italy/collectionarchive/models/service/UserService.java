package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.restdto.UserDto;

public interface UserService {
    User getUserInfo();
    void deleteUser();
}
