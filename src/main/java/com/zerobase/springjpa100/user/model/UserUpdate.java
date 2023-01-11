package com.zerobase.springjpa100.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {

    @Size(max = 20, message = "폰번호는 20자리 이내로 필요합니다")
    @NotBlank(message = "폰번호는 필수 항목 입니다")
    private String phone;
}
