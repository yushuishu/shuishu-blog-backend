package com.shuishu.blog.common.domain.user.entity.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-29 22:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：用户基础信息表
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_user")
@Comment(value = "用户表")
public class User extends BasePO {
    @Serial
    private static final long serialVersionUID = 7399511491764319903L;

    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "用户id")
    private Long userId;

    @Comment("昵称")
    @Column(nullable = false, unique = true)
    private String nickname;

    @Comment("个人简介")
    private String userAbout;

    @Comment("头像")
    @Column(nullable = false)
    private String userPhoto;

    @Comment("地址")
    private String userAddress;

    @Comment("行业id")
    private Long industryId;

    @Comment("用户过期true：没有过期  false：过期")
    @Column(nullable = false)
    private Boolean userIsAccountNonExpired;

    @Comment("用户锁定true：没有锁定  false：被锁定")
    @Column(nullable = false)
    private Boolean userIsAccountNonLocked;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Comment("最后一次登录时间")
    private Date userLastLoginDate;

    @Comment("同时登录客户端的人数:最小1最大50（默认1）")
    @Column(nullable = false)
    private Integer userMaxLoginClientNumber;

}
