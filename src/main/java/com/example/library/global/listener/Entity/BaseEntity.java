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
public class BaseEntity {
    @Column(updatable = false)
    private String createdDt;

    @Column(updatable = false)
    private String createdTm;
}
