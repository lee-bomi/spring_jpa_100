package com.zerobase.springjpa100.user.model;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    @NotBlank(message = "이메일은 필수 항목 입니다")
    @Email(message = "이메일 형식에 맞게 입력해 주세요")
    private String email;

    @NotBlank(message = "이름은 필수 항목 입니다")
    private String userName;

    @Size(min = 4, message = "비밀번호는 4자리 이상 필요합니다")
    @NotBlank(message = "비밀번호는 필수 항목 입니다")
    private String password;

    @Size(max = 20, message = "폰번호는 20자리 이내로 필요합니다")
    @NotBlank(message = "폰번호는 필수 항목 입니다")
    private String phone;

}
