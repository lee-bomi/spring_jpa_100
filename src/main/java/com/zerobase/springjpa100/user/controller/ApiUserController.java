package com.zerobase.springjpa100.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.notice.exception.UserNotFoundException;
import com.zerobase.springjpa100.notice.model.ResponseError;
import com.zerobase.springjpa100.notice.repository.NoticeLikeRepository;
import com.zerobase.springjpa100.notice.repository.NoticeRepository;
import com.zerobase.springjpa100.user.entity.User;
import com.zerobase.springjpa100.user.exception.ExistsEmailException;
import com.zerobase.springjpa100.user.model.*;
import com.zerobase.springjpa100.user.repository.UserRepository;
import com.zerobase.springjpa100.util.JwtUtil;
import com.zerobase.springjpa100.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeLikeRepository noticeLikeRepository;

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
//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput input, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        if (userRepository.countByEmail(input.getEmail()) > 0) {
//            throw new ExistsEmailException("이미 존재하는 이메일 입니다");
//        }
//
//        User user = User.builder()
//                .email(input.getEmail())
//                .userName(input.getUserName())
//                .phone(input.getPhone())
//                .password(input.getPassword())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @ExceptionHandler(value = {PasswordNotMatchException.class, ExistsEmailException.class})
    public ResponseEntity<?> EmailPasswordException(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //37 비번만 변경하는 api를 만들어라
//    @PatchMapping("/api/user/{id}/password")
//    public ResponseEntity<?> passwordReset(@RequestBody @Valid UserInputPassword userInputPassword,
//                                           @PathVariable Long id,
//                                           Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
//                .orElseThrow(() -> new PasswordNotMatchException("일치하는 비밀번호가 없습니다"));
//
//        user.setPassword(userInputPassword.getNewPassword());
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    //객체를 만들어서 비번 암호화하기
    private String getEncryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    //38
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
                .password(getEncryptPassword(input.getPassword()))
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    //39
//    @DeleteMapping("/api/user/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));
//
//        try {
//            userRepository.delete(user);
//        } catch (DataIntegrityViolationException e) {
//            String message = "제약조건에 문제가 발생하였습니다";
//            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            String message = "회원탈퇴 중 문제가 발생하였습니다";
//            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok().build();
//    }

    //40 아이디찾는 API => id는 비번과달리 보안상 이슈없이 그냥 알랴줘도 된다
    @GetMapping("/api/User")
    public ResponseEntity<?> findUser(@RequestBody FindUserInput input) {

        User user = userRepository.findByUserNameAndPhone(input.getUserName(), input.getPhone())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));

        UserResponse userResponse = UserResponse.of(user);
        return ResponseEntity.ok().body(userResponse);
    }

    private String getResetPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    //41  비번 초기화하기
    @GetMapping("/api/user/{id}/password/reset")
    public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));

        //비밀번호 초기화(암호화된 임의의 비번을 전송함)
        String resetPassword = getResetPassword();
        String resetEncryptPassword = getEncryptPassword(resetPassword);
        user.setPassword(resetEncryptPassword);
        userRepository.save(user);

        String message = String.format(
                "[%s]님의 임시 비밀번호가 [%s]로 초기화 되었습니다"
                , user.getUserName()
                , resetPassword
        );
        sendSMS(message);

        return ResponseEntity.ok().build();
    }

    public void sendSMS(String message) {
        System.out.println("[문자 메시지 전송]");
        System.out.println(message);
    }

    //42 내가 좋아요 한 공지사항 보기
    @GetMapping("/api/user/{id}/notice/like")
    public List<Notice> likeNotice(@PathVariable Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));

        List<Notice> noticeLikeList = noticeRepository.findByUser(user);

        return noticeLikeList;
    }

    //43 사용자 이메일과 비번을 확인
//    @PostMapping("/api/user/login")
//    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByEmail(userLogin.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));
//
//        if (!PasswordUtil.equalPassword(userLogin.getPassword(), user.getPassword())) {
//            throw new PasswordNotMatchException(" 비밀번호가 일치하지 않습니다");
//        }
//
//        return ResponseEntity.ok().build();
//    }

    //44, 45    43에 이어 토큰발행, 유효기한추가
    @PostMapping("/api/user/login")
    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));

        if (!PasswordUtil.equalPassword(userLogin.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException(" 비밀번호가 일치하지 않습니다");
        }

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime); //Date타입을 넣어줘야해서 java.utill로 변환

        String token = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("zerobase".getBytes()));

        return ResponseEntity.ok().body(UserLoginToken.builder().token(token).build());
    }

    //46 토큰 재발행(외부 노출로인한 기존토큰 무효화, 새 토큰 발행)
    @PatchMapping("/api/user/login")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {

        String token = request.getHeader("F-TOKEN");
        String email = "";
        try {
            email = JWT.require(Algorithm.HMAC512("zerobase".getBytes()))
                    .build()
                    .verify(token)
                    .getIssuer();
        } catch (SignatureVerificationException e) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다");
        }


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다"));

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(1);
        Timestamp expiredDate = Timestamp.valueOf(localDateTime);

        String newToken = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("zerobase".getBytes()));

        return ResponseEntity.ok().body(UserLoginToken.builder().token(newToken).build());
    }

    //47 토큰삭제요청 api작성하기
    @DeleteMapping("/api/user/login")
    public ResponseEntity<?> removeToken(@RequestHeader("F-TOKEN") String token) {
        String email = "";

        try {
            email = JwtUtil.getIssuer(token);
        } catch (SignatureVerificationException e) {
            return new ResponseEntity<>("토큰 정보가 정확하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }


}