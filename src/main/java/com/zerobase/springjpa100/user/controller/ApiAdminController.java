package com.zerobase.springjpa100.user.controller;

import com.zerobase.springjpa100.user.entity.User;
import com.zerobase.springjpa100.user.model.ResponseMessage;
import com.zerobase.springjpa100.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiAdminController {

    private final UserRepository userRepository;

    //48
//    @GetMapping("/api/admin/user")
//    public ResponseMessage userList() {
//        List<User> userList = userRepository.findAll();
//        long totalUserCount = userRepository.count();
//
//        return ResponseMessage.builder()
//                .totalCount(totalUserCount)
//                .data(userList)
//                .build();
//    }

    //49
    @GetMapping("/api/admin/user/{id}")
    public ResponseEntity<?> userDetail(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(ResponseMessage.success(user));
    }

    //50 검색

}
