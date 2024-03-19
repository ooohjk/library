package com.example.library.domain.rent.manager.entity;

import com.example.library.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rentManager")
public class RentManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerNo;

    @OneToOne()
    @JoinColumn(name = "userNo", nullable = false)
    private UserEntity user;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean overdue;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer rentNumber;

    @Column()
    private String lastReturnDt;
}
