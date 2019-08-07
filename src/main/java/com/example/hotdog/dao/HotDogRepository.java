package com.example.hotdog.dao;

import com.example.hotdog.rest.v1.dto.HotDogDto;

import java.util.List;

public interface HotDogRepository {
    HotDogDto getHotDogById(Long hotDogId);
    List<HotDogDto> getAllHotDogs();
    HotDogDto createHotDog(HotDogDto hotDogDto);
    void deleteHotDogById(Long hotDogId);
    void updatedHotDog(HotDogDto hotDogDto);
}
