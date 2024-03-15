package com.example.library.global.listener;

import com.example.library.global.listener.Entity.BaseEntity;
import com.example.library.global.listener.Entity.ModifiedEntity;
import com.example.library.global.utils.DateUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DateFormatListener {

    @PrePersist
    public void prePersist(Object obj){
        BaseEntity baseEntity = (BaseEntity) obj;
        baseEntity.setCreatedDt(DateUtil.getDate());
        baseEntity.setCreatedTm(DateUtil.getTime());
    }

    @PreUpdate
    public void preUpdate(Object obj){
        ModifiedEntity modifiedEntity = (ModifiedEntity) obj;
        modifiedEntity.setModifiedDt(DateUtil.getDate());
        modifiedEntity.setModifiedTm(DateUtil.getTime());
    }
}
