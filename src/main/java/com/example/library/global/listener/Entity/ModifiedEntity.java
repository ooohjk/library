package com.example.library.global.listener.Entity;

import com.example.library.global.listener.DateFormatListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(DateFormatListener.class)
public class ModifiedEntity extends BaseEntity{

    @Column
    private String modifiedDt;

    @Column
    private String modifiedTm;
}