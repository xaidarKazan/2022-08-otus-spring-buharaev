package ru.otus.homework.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public Job jobOnMigrationSqlToNosql(Step authorToNoSqlStep, Step genreToNoSqlStep, Step bookToNoSqlStep, Step commentToNoSqlStep) {
        return jobBuilderFactory.get("jobOnMigrationSqlToNosql")
                .start(authorToNoSqlStep)
                .next(genreToNoSqlStep)
                .next(bookToNoSqlStep)
                .next(commentToNoSqlStep)
                .build();
    }

    @Bean
    public Job jobOnMigrationNoSqlToSql(Step authorToSqlStep, Step genreToSqlStep, Step bookToSqlStep, Step commentToSqlStep) {
        return jobBuilderFactory.get("jobOnMigrationNoSqlToSql")
                .start(authorToSqlStep)
                .next(genreToSqlStep)
                .next(bookToSqlStep)
                .next(commentToSqlStep)
                .build();
    }
}