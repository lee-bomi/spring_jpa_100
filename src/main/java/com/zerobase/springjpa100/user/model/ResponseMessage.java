package com.zerobase.springjpa100.user.model;

import com.zerobase.springjpa100.notice.model.ResponseMessageHeader;
import com.zerobase.springjpa100.user.entity.User;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private ResponseMessageHeader header;
    private Object data;

    public static ResponseMessage fail(String message) {

        return ResponseMessage.builder()
                .header(ResponseMessageHeader.builder()
                        .result(false)
                        .resultCode("")
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build())
                .data(null)
                .build();
    }

    public static ResponseMessage success(Object data) {

        return ResponseMessage.builder()
                .header(ResponseMessageHeader.builder()
                        .result(true)
                        .resultCode("")
                        .message("")
                        .status(HttpStatus.OK.value())
                        .build())
                .data(data)
                .build();
    }
}
