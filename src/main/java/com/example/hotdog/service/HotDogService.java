package com.example.hotdog.service;

import com.example.hotdog.rest.v1.dto.HotDogDto;

import java.util.List;

public interface HotDogService {
    List<HotDogDto> getAllHotDogs();
    HotDogDto getHotDog(Long hotDogId);
    HotDogDto createHotDog(HotDogDto hotDogDto);
    void updateHotDog(HotDogDto hotDogDto);
    void deleteHotDog(Long hotDogId);
}
