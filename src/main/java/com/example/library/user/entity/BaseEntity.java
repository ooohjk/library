package com.example.library.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public class BaseEntity {
}
