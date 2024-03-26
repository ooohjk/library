package com.example.library.global;

import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;

@Setter
public class Events {
    private static ApplicationEventPublisher publisher;

    public static void setPublisher(ApplicationEventPublisher publisher){
        Events.publisher = publisher;
    }
    
    //이벤트를 발생시킬 코드는 해당 메소드를 사용
    public static void raise(Object event){
        if(publisher!=null){
            publisher.publishEvent(event);
        }
    }
}
