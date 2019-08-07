package com.example.hotdog.service;

import com.example.hotdog.dao.HotDogRepository;
import com.example.hotdog.rest.v1.dto.HotDogDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HotDogServiceImpl implements HotDogService {
    private final HotDogRepository hotDogRepository;

    @Override
    public HotDogDto getHotDog(Long hotDogId) {
        return hotDogRepository.getHotDogById(hotDogId);
    }

    @Override
    public List<HotDogDto> getAllHotDogs() {
        return hotDogRepository.getAllHotDogs();
    }

    @Override
    public HotDogDto createHotDog(HotDogDto hotDogDto) {
        return hotDogRepository.createHotDog(hotDogDto);
    }

    @Override
    public void updateHotDog(HotDogDto hotDogDto) {
        hotDogRepository.updatedHotDog(hotDogDto);
    }

    @Override
    public void deleteHotDog(Long hotDogId) {
        hotDogRepository.deleteHotDogById(hotDogId);
    }
}
