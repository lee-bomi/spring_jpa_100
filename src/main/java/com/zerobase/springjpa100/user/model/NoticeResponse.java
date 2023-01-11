package com.zerobase.springjpa100.user.model;

import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponse {
    private long id;

    private long regUserId;
    private String regUserName;

    private String title;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private int hits;
    private int likes;

    public static NoticeResponse of(Notice e) {
        return NoticeResponse.builder()
                .id(e.getId())
                .regUserId(e.getUser().getId())
                .regUserName(e.getUser().getUserName())
                .title(e.getTitle())
                .contents(e.getContents())
                .regDate(e.getRegDate())
                .updateDate(e.getUpdateDate())
                .hits(e.getHits())
                .likes(e.getLikes())
                .build();
    }
}
