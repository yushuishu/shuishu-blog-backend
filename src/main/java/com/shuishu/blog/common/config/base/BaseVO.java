package com.shuishu.blog.common.config.base;


import jakarta.persistence.MappedSuperclass;


import java.io.Serializable;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-24 18:29
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：视图对象(View Object), 展示层
 */
@MappedSuperclass
public class BaseVO<T extends BasePO> implements Serializable {
}
