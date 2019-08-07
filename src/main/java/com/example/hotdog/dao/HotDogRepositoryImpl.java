package com.example.hotdog.dao;

import com.example.hotdog.exception.CreateHotDogException;
import com.example.hotdog.exception.DeleteHotDogException;
import com.example.hotdog.exception.ReadHotDogException;
import com.example.hotdog.exception.UpdateHotDogException;
import com.example.hotdog.rest.v1.HotDogOperation;
import com.example.hotdog.rest.v1.dto.HotDogDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class HotDogRepositoryImpl implements HotDogRepository {
    private static final AtomicLong ID;
    private static final ConcurrentHashMap<Long, HotDogDto> DATABASE;

    static {
        ID = new AtomicLong(10);

        DATABASE = new ConcurrentHashMap<>();
        DATABASE.put(1L, new HotDogDto(1L, "hotdog_1"));
        DATABASE.put(2L, new HotDogDto(2L, "hotdog_2"));
        DATABASE.put(3L, new HotDogDto(3L, "hotdog_3"));
        DATABASE.put(4L, new HotDogDto(4L, "hotdog_4"));
        DATABASE.put(5L, new HotDogDto(5L, "hotdog_5"));
        DATABASE.put(6L, new HotDogDto(6L, "hotdog_6"));
        DATABASE.put(7L, new HotDogDto(7L, "hotdog_7"));
        DATABASE.put(8L, new HotDogDto(8L, "hotdog_8"));
        DATABASE.put(9L, new HotDogDto(9L, "hotdog_9"));
        DATABASE.put(10L, new HotDogDto(10L, "hotdog_10"));
    }

    @Override
    public HotDogDto getHotDogById(Long hotDogId) {
        checkHotDogId(hotDogId, HotDogOperation.READ);
        return DATABASE.get(hotDogId);
    }

    @Override
    public List<HotDogDto> getAllHotDogs() {
        return DATABASE.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public HotDogDto createHotDog(HotDogDto hotDogDto) {
        Long id = ID.incrementAndGet();
        HotDogDto hotDogWithId = new HotDogDto(id, hotDogDto.getName());
        DATABASE.put(id, hotDogWithId);
        checkHotDogId(id, HotDogOperation.CREATE);
        return hotDogWithId;
    }

    @Override
    public void deleteHotDogById(Long hotDogId) {
        checkHotDogId(hotDogId, HotDogOperation.DELETE);
        DATABASE.remove(hotDogId);
    }

    @Override
    public void updatedHotDog(HotDogDto hotDogDto) {
        Long hotDogId = hotDogDto.getId();
        checkHotDogId(hotDogId, HotDogOperation.UPDATE);
        DATABASE.put(hotDogId, hotDogDto);
    }

    private void checkHotDogId(Long hotDogId, HotDogOperation operation) {
        if (DATABASE.containsKey(hotDogId)) {
            return;
        }

        switch (operation) {
            case CREATE: throw new CreateHotDogException("Cannot create hot dog with id = " + hotDogId);
            case READ: throw new ReadHotDogException("Cannot read hot dog with id = " + hotDogId);
            case UPDATE: throw new UpdateHotDogException("Cannot update hot dog with id = " + hotDogId);
            case DELETE: throw new DeleteHotDogException("Cannot delete hot dog with id = " + hotDogId);
            default:
        }
    }
}
