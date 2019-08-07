package com.example.hotdog.config;

import com.example.hotdog.dao.HotDogRepository;
import com.example.hotdog.dao.HotDogRepositoryImpl;
import com.example.hotdog.service.HotDogService;
import com.example.hotdog.service.HotDogServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HotDogRestConfig.class,
        HotDogWebConfig.class
})
public class HotDogConfig {
    @Bean
    HotDogRepository hotDogRepository() {
        return new HotDogRepositoryImpl();
    }

    @Bean
    HotDogService hotDogService(HotDogRepository hotDogRepository) {
        return new HotDogServiceImpl(hotDogRepository);
    }
}
