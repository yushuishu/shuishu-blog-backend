package com.shuishu.blog.business.article.service.impl;


import com.shuishu.blog.business.article.service.ArticleService;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.config.base.PageVO;
import com.shuishu.blog.common.config.exception.BusinessException;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.domain.article.dsl.ArticleDsl;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleAddDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticlePublishDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleQueryDto;
import com.shuishu.blog.common.domain.article.entity.dto.ArticleUpdateDto;
import com.shuishu.blog.common.domain.article.entity.po.Article;
import com.shuishu.blog.common.domain.article.entity.vo.ArticleVo;
import com.shuishu.blog.common.domain.article.repository.ArticleRepository;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-06 20:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleDsl articleDsl;



    @Override
    public void addArticle(ArticleAddDto articleAddDto) {
        if (ObjectUtils.isEmpty(articleAddDto.getLabelNameList())) {
            throw new BusinessException("文章标签不能为空");
        }
        if (articleAddDto.getArticleTitle().length() > 50) {
            throw new BusinessException("文章标题长度不能超过50个字符");
        }
        if (articleAddDto.getArticleMarkdown().length() > 10000000) {
            throw new BusinessException("文章内容不能超过1000万个字符");
        }
        if (articleAddDto.getArticleBriefDescription().length() < 30 || articleAddDto.getArticleBriefDescription().length() > 300) {
            throw new BusinessException("文章简要说明要求大于30个字符，不超过300个字符");
        }

        Article article = articleAddDto.toPo(Article.class);
        // 获取当前用户
        UserInfoVo userInfoVo = SpringSecurityUtils.getUserInfoVo();
        article.setCreateUserId(userInfoVo.getUserId());
        article.setUpdateUserId(userInfoVo.getUserId());
        articleRepository.save(article);
    }

    @Override
    public void publishArticle(ArticlePublishDto articlePublishDto) {
        Article article = articleRepository.findByArticleId(articlePublishDto.getArticleId());
        Objects.requireNonNull(article, "文章不存在");
        if (Boolean.TRUE.equals(article.getPublish()) && Boolean.TRUE.equals(articlePublishDto.getPublish())) {
            return;
        }
        if (Boolean.FALSE.equals(article.getPublish()) && Boolean.FALSE.equals(articlePublishDto.getPublish())) {
            return;
        }
        article.setPublish(articlePublishDto.getPublish());
        articleRepository.saveAndFlush(article);
    }

    @Override
    public PageVO<ArticleVo> findArticlePage(ArticleQueryDto articleQueryDto, PageDTO pageDTO) {
        return null;
    }

    @Override
    public ArticleVo findArticleDetails(Long articleId) {
        return null;
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {

    }

    @Override
    public void deleteArticle(Long articleId) {

    }


}
