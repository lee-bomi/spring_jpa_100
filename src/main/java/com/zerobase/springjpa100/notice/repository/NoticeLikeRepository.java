package com.zerobase.springjpa100.notice.repository;

import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.notice.entity.NoticeLike;
import com.zerobase.springjpa100.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeLikeRepository extends JpaRepository<NoticeLike, Long> {

    List<NoticeLike> findByUser(User user);

}
