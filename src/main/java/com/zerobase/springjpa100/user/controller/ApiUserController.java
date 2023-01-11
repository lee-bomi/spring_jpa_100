package com.zerobase.springjpa100.user.controller;

import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.notice.exception.UserNotFoundException;
import com.zerobase.springjpa100.notice.model.ResponseError;
import com.zerobase.springjpa100.notice.repository.NoticeRepository;
import com.zerobase.springjpa100.user.entity.User;
import com.zerobase.springjpa100.user.exception.ExistsEmailException;
import com.zerobase.springjpa100.user.model.*;
import com.zerobase.springjpa100.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    //31
//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput input, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        return ResponseEntity.ok().build();
//    }

    //32
//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput input, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = User.builder()
//                .email(input.getEmail())
//                .userName(input.getUserName())
//                .password(input.getPassword())
//                .phone(input.getPhone())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //33
//    @PutMapping("/api/use/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdate userUpdate,
//                                        @PathVariable Long id,
//                                        Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(e -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));
//
//        user.setPhone(user.getPhone());
//        user.setUpdateDate(LocalDateTime.now());
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    //34
    //DB에서 고객정보중 일부만 내릴경우엔, 특정 모델을만들어서 필요한것만 내려야한다
//    @GetMapping("/api/user/{id}")
//    public UserResponse findUser(@PathVariable Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));
//
//        return UserResponse.of(user);
//    }

    //35
//    @GetMapping("/api/user/{id}/notice")
//    public List<NoticeResponse> userNotice(@PathVariable Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));
//
//        List<Notice> noticeList = noticeRepository.findByUser(user);
//
//        List<NoticeResponse> noticeResponseList = new ArrayList<>();
//
//        noticeList.stream().forEach(
//                (e) -> noticeResponseList.add(NoticeResponse.of(e)));
//
//        return noticeResponseList;
//    }


    //36
    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput input, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(input.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일 입니다");
        }

        User user = User.builder()
                .email(input.getEmail())
                .userName(input.getUserName())
                .phone(input.getPhone())
                .password(input.getPassword())
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {PasswordNotMatchException.class, ExistsEmailException.class})
    public ResponseEntity<?> EmailPasswordException(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //37 비번만 변경하는 api를 만들어라
    @PatchMapping("/api/user/{id}/password")
    public ResponseEntity<?> passwordReset(@RequestBody @Valid UserInputPassword userInputPassword,
                                           @PathVariable Long id,
                                           Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
                .orElseThrow(() -> new PasswordNotMatchException("일치하는 비밀번호가 없습니다"));

        user.setPassword(userInputPassword.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
