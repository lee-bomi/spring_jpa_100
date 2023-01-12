package com.zerobase.springjpa100.user.repository;

import com.zerobase.springjpa100.user.entity.User;
import com.zerobase.springjpa100.user.model.UserInputPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    int countByEmail(String email);

    Optional<User> findByEmail(String email);


    Optional<User> findByIdAndPassword(Long id, String password);

    Optional<User> findByUserNameAndPhone(String userName, String phone);
}
