package com.example.library.user.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @Column(name = "userNo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;

    private String userId;

    private String userPwd;

    private String userName;

    @Column(name = "createdAt")
    private Date createdDate;

    private String tel;

    @Column(name = "email")
    private String userEmail;

    private String gender;

    private int useFlg;
}