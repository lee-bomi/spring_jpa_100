package com.zerobase.springjpa100.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
