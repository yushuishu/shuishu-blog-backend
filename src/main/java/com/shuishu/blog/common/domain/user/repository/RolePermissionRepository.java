package com.shuishu.blog.common.domain.user.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.user.entity.po.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2023-01-01 0:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Repository
public interface RolePermissionRepository extends BaseRepository<RolePermission, Long> {
    /**
     * 查询角色权限关联信息
     *
     * @param roleId 角色id
     * @return 角色权限关联信息
     */
    List<RolePermission> findAllByRoleId(Long roleId);

    /**
     * 查询角色权限关联信息
     *
     * @param permissionId 权限id
     * @return -
     */
    List<RolePermission> findAllByPermissionId(Long permissionId);

    /**
     * 删除角色权限关联信息
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);

}
