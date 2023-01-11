package com.zerobase.springjpa100.notice.entity;

import com.zerobase.springjpa100.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private LocalDateTime regDate;
    @Column
    private LocalDateTime updateDate;
    @Column
    private int hits;
    @Column
    private int likes;
    @Column
    private boolean deleted;
    @Column
    private LocalDateTime deletedDate;
}
