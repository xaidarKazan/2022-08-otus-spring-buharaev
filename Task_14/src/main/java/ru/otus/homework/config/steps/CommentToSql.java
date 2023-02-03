package ru.otus.homework.config.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework.domain.nosql.Comment;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class CommentToSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step commentToSqlStep(ItemStreamReader<Comment> commentMongoItemReader, ItemWriter<ru.otus.homework.domain.sql.Comment> commentJpaItemWriter) {
        return stepBuilderFactory.get("commentToSqlStep")
                .<Comment, ru.otus.homework.domain.sql.Comment>chunk(2)
                .reader(commentMongoItemReader)
                .processor(commentToSqlProcessor())
                .writer(commentJpaItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Comment> commentMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Comment>()
                .name("commentMongoItemReader")
                .collection("Comment")
                .template(mongoTemplate)
                .jsonQuery("{ }")
                .sorts(new HashMap<>())
                .targetType(Comment.class)
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Comment, ru.otus.homework.domain.sql.Comment> commentToSqlProcessor() {
        return comment -> comment.toSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.sql.Comment> commentJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<ru.otus.homework.domain.sql.Comment>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}