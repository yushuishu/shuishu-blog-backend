package com.shuishu.blog.common.domain.article.entity.po;


import com.shuishu.blog.common.config.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-10 13:09
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文章
 * <p></p>
 */
@Setter
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ss_article")
@Comment(value = "文章表")
public class Article extends BasePO {
    @Id
    @GeneratedValue(generator = "CustomIdGenerator")
    @GenericGenerator(name = "CustomIdGenerator", strategy = "com.shuishu.blog.common.config.id.CustomIdGenerator")
    @Comment(value = "文章id")
    private Long articleId;

    @Comment("文章名称")
    private String articleTitle;

    @Comment("文章Markdown")
    private String articleMarkdown;

    @Comment("文章HTML")
    private String articleHtml;

    @Comment("文章字数（减少查询）")
    private Long articleWordCount;

    @Comment("文章简要说明")
    private String articleBriefDescription;

    @Comment("文章浏览量")
    private Integer articleBrowseCount;

    @Comment("文章赞点量")
    private Integer articleUpvote;

    @Comment("文章封面图")
    private String articleCoverImg;

    @Comment("阅读时长（分钟）")
    private Integer articleBrowseTime;

    @Comment("涉及行业或领域，以 | 分割")
    private String industryName;

    @Comment("文章动态权重值（定时任务更新权重值）")
    private Integer articleVariableWeightValue;

    @Comment("文章发布 true:发布 false:取消发布")
    private Boolean articlePublish;

    @Comment("到期删除时间")
    private Boolean articleDelete;

}
