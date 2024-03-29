package com.shuishu.blog.common.config.jdbc;


import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shuishu.blog.common.config.id.IdGenerate;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ：谁书-ss
 * @Date ：2022-12-25 12:14
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 */
@Configuration
public class JpaQueryConfiguration {
    /**
     * id 默认生成器
     * @return idGenerate
     */
    @Bean
    public IdGenerate idGenerate() {
        return new IdGenerate(0, 0);
    }

    @Resource
    @PersistenceContext
    public EntityManager entityManager;


    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }
}
