package com.shuishu.blog.common.domain.industry.entity.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.shuishu.blog.common.config.base.BaseVO;
import com.shuishu.blog.common.domain.industry.entity.po.Industry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-30 12:46
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业vo
 * <p></p>
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustryVO extends BaseVO<Industry> {
    private Long industryId;

    private Long industryName;

    private Integer industrySort;

    private Date createDate;

    private Date updateDate;

    private Long updateUserId;
}
