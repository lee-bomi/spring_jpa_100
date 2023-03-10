package com.zerobase.springjpa100.notice.repository;

import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<List<Notice>> findByIdIn(List<Long> idList);

    Optional<List<Notice>> findByTitleAndContentsAndRegDateIsGreaterThanEqual(
            String title, String contents, LocalDateTime regDate);

    List<Notice> findByUser(User user);


}
