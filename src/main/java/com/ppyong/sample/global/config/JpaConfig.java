package com.ppyong.sample.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public AuditorAware<String> auditorAware() {
        // 추후 security 설정으로 대체
        return () -> Optional.of("userId");
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
