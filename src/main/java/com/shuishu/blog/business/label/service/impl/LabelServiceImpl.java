package com.shuishu.blog.business.label.service.impl;


import com.shuishu.blog.business.label.service.LabelService;
import com.shuishu.blog.common.domain.label.dsl.LabelDsl;
import com.shuishu.blog.common.domain.label.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：谁书-ss
 * @date ：2023-04-03 22:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：标签
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelDsl labelDsl;

}
