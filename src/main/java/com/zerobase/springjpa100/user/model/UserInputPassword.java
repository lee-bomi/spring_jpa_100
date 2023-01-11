package com.zerobase.springjpa100.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInputPassword {

    @NotBlank(message = "현재 비번은 필수항복입니다")
    private String password;

    @Size(min = 4, max = 20, message = "비번 자리수는 4-20자리 입니다")
    @NotBlank(message = "신규 비번은 필수항목입니다")
    private String newPassword;
}
