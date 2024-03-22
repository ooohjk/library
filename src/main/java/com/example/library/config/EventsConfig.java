package com.example.library.config;

import com.example.library.domain.rent.domain.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventsConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventsInitialize(){
        return ()-> Events.setPublisher(applicationContext);
    }

}
