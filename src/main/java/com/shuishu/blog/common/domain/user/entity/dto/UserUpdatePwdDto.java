package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuZhenFeng
 * @date 2024/3/14 11:29
 */
@Schema(description = "更新用户账号密码dto")
@Setter
@Getter
@ToString
public class UserUpdatePwdDto {
    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Schema(description = "确认密码")
    private String ackPassword;
}
