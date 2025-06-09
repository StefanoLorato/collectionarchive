package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;

import java.util.HashMap;
import java.util.Map;

public interface JwtService {

    String generateToken(Map<String, Object> claims, User user);
}
