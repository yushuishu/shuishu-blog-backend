package com.shuishu.blog.common.domain.monitor.repository;


import com.shuishu.blog.common.config.jdbc.BaseRepository;
import com.shuishu.blog.common.domain.monitor.entity.po.ApiLogRecord;
import org.springframework.stereotype.Repository;

/**
 * @author ：谁书-ss
 * @date ：2023-05-02 17:26
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：系统api日志
 * <p></p>
 */
@Repository
public interface ApiLogRecordRepository extends BaseRepository<ApiLogRecord, Long> {


}
