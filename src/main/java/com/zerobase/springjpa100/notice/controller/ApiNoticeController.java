package com.zerobase.springjpa100.notice.controller;

import com.zerobase.springjpa100.notice.entity.Notice;
import com.zerobase.springjpa100.notice.exception.NoticeNotFoundException;
import com.zerobase.springjpa100.notice.model.NoticeInput;
import com.zerobase.springjpa100.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;

//    @GetMapping("/api/notice")
//    public String helloNotice() {  //rest형식의 게시판 목록에 대한 요청처리하는 api
//
//        return "공지사항입니다";
//    }

//    @GetMapping("/api/notice2")
//    public List<NoticeModel> helloNotice2() {  //rest형식의 게시판 목록에 대한 요청처리하는 api
//
//        LocalDateTime regDate = LocalDateTime.of(2023, 1, 5, 12, 30);
//        List<NoticeModel> notices = new ArrayList<>();
//
//        NoticeModel build1 = NoticeModel.builder()
//                .id(1)
//                .title("공지사항1")
//                .contents("공지사항입니다1")
//                .regDt(regDate)
//                .build();
//
//        notices.add(build1);
//
//        NoticeModel build2 = NoticeModel.builder()
//                .id(2)
//                .title("공지사항2")
//                .contents("공지사항입니다2")
//                .regDt(regDate)
//                .build();
//
//        notices.add(build2);
//
//        System.out.println(notices);
//
//        return notices;
//    }

    @GetMapping("/api/notice/count")
    public int helloNotice() {  // 정수형 리턴하기

        int num = 30;

        return num;
    }

//    @PostMapping("/api/notice")
//    public NoticeModel register(HttpServletRequest request) {
//        String title = request.getParameter("title");
//        String contents = request.getParameter("contents");
//
//        return NoticeModel.builder()
//                .id(1)
//                .title(title)
//                .contents(contents)
//                .build();
//
//    }

    //11
//    @PostMapping("/api/notice")
//    public NoticeModel register(@RequestParam String title, @RequestParam String contents) {
//
//        return NoticeModel.builder()
//                .id(1)
//                .title(title)
//                .contents(contents)
//                .build();
//
//    }

    //12
//    @PostMapping("/api/notice")
//    public NoticeModel register(NoticeModel noticeModel) {
//
//        System.out.println(noticeModel + " ------------ ");
//
//        noticeModel.setId(2);
//        noticeModel.setRegDt(LocalDateTime.now());
//        return noticeModel;
//    }

    //13
//    @PostMapping("/api/notice3")
//    public NoticeModel register(@RequestBody NoticeModel noticeModel) {
//
//        noticeModel.setId(3);
//        noticeModel.setRegDt(LocalDateTime.now());
//        return noticeModel;
//    }

    //14
//    @PostMapping("/api/notice")
//    public Notice register(@RequestBody NoticeInput noticeInput) {
//
//        Notice notice = Notice.builder()
//                .title(noticeInput.getTitle())
//                .contents(noticeInput.getContents())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        Notice saveNotice = noticeRepository.save(notice);
//
//        return saveNotice;
//    }

//15
@PostMapping("/api/notice4")
public Notice register(@RequestBody NoticeInput noticeInput) {

    Notice notice = Notice.builder()
            .title(noticeInput.getTitle())
            .contents(noticeInput.getContents())
            .regDate(LocalDateTime.now())
            .hits(0)
            .likes(0)
            .build();

    Notice saveNotice = noticeRepository.save(notice);

    return saveNotice;
}

    //16
//    @GetMapping("/api/notice/{id}")
//    public Optional<Notice> getNotice(@PathVariable Long id) {
//
//        Optional<Notice> notice = noticeRepository.findById(id);
//
//        if (notice.isPresent()) {
//            return notice;
//        }
//        return null;
//    }

    //17
    @PutMapping("/api/notice")
    public Notice getNotice(@RequestBody Notice notice) {

        Optional<Notice> byId = noticeRepository.findById(notice.getId());
        if (byId.isEmpty()) {
            return null;
        }

        Notice notice1 = byId.get();
        notice1.setUdtDate(LocalDateTime.now());
        Notice save = noticeRepository.save(notice1);
        return save;
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //18
//    @PutMapping("/api/notice/{id}")
//    public Notice getNotice(@RequestBody NoticeInput input, @PathVariable Long id) {
//
//        Notice notice = noticeRepository.findById(id)
//                .orElseThrow(() -> new NoticeNotFoundException("공지사항에 글이 존재하지않습니다"));
//
//        notice.setUdtDate(LocalDateTime.now());
//        notice.setTitle(input.getTitle());
//        notice.setContents(input.getContents());
//        Notice save = noticeRepository.save(notice);
//
//        return save;
//    }

    //19
//    @PutMapping("/api/notice/{id}")
//    public Notice getNotice(@RequestBody NoticeInput input, @PathVariable Long id) {
//
//        Notice notice = noticeRepository.findById(id)
//                .orElseThrow(() -> new NoticeNotFoundException("공지사항에 글이 존재하지않습니다"));
//
//        notice.setUdtDate(LocalDateTime.now());
//        notice.setTitle(input.getTitle());
//        notice.setContents(input.getContents());
//        notice.setUdtDate(LocalDateTime.now());
//        Notice save = noticeRepository.save(notice);
//
//        return save;
//    }

    //20
    //조회수만 올린다(부분적인 갱신! 수정아님 주의)
    @PatchMapping("/api/notices4/{id}/hits")
    public void noticeHits(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항에 글이 존재하지않습니다"));

        notice.setHits(notice.getHits() + 1);
        noticeRepository.save(notice);
    }


}
