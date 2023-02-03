package ru.otus.homework.config.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework.domain.sql.Comment;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class CommentToNoSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step commentToNoSqlStep(ItemStreamReader<Comment> commentItemStreamReader, ItemWriter<ru.otus.homework.domain.nosql.Comment> commentMongoItemWriter) {
        return stepBuilderFactory.get("commentToNoSqlStep")
                .<Comment, ru.otus.homework.domain.nosql.Comment>chunk(2)
                .reader(commentItemStreamReader)
                .processor(commentToNoSqlProcessor())
                .writer(commentMongoItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Comment> commentJpaItemReader(EntityManagerFactory entityManagerFactory) {
        return  new JpaCursorItemReaderBuilder<Comment>()
                .name("commentJpaItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select c from Comment c ")
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Comment, ru.otus.homework.domain.nosql.Comment> commentToNoSqlProcessor() {
        return comment -> comment.toNoSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.nosql.Comment> commentMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<ru.otus.homework.domain.nosql.Comment>()
                .collection("Comment")
                .template(mongoTemplate)
                .build();
    }
}