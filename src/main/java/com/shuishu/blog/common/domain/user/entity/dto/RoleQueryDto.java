package com.shuishu.blog.common.domain.user.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-18 13:03
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：查询角色条件
 * <p></p>
 */
@Schema(description = "查询角色信息条件dto")
@Setter
@Getter
@ToString
public class RoleQueryDto {
    @Schema(description = "名称/code/描述")
    private String keyword;

    @Schema(description = "操作编辑删除权限：true可以；false不可以")
    private Boolean roleOperatePower;

}
