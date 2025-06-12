package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.restdto.AuthenticationRequestDto;
import org.generation.italy.collectionarchive.restdto.AuthenticationResponseDto;
import org.generation.italy.collectionarchive.restdto.RegisterRequestDto;

public interface AuthenticationService {
    void register(RegisterRequestDto input) throws Exception;
    AuthenticationResponseDto login(AuthenticationRequestDto request);
}
