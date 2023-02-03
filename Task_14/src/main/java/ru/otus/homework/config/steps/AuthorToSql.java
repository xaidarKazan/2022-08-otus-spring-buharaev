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
import ru.otus.homework.domain.nosql.Author;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class AuthorToSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step authorToSqlStep(ItemStreamReader<Author> authorMongoItemReader, ItemWriter<ru.otus.homework.domain.sql.Author> authorJpaItemWriter) {
        return stepBuilderFactory.get("authorToSqlStep")
                .<Author, ru.otus.homework.domain.sql.Author>chunk(2)
                .reader(authorMongoItemReader)
                .processor(authorToSqlProcessor())
                .writer(authorJpaItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Author> authorMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Author>()
                .name("authorMongoItemReader")
                .collection("Author")
                .template(mongoTemplate)
                .jsonQuery("{ }")
                .sorts(new HashMap<>())
                .targetType(Author.class)
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Author, ru.otus.homework.domain.sql.Author> authorToSqlProcessor() {
        return author -> author.toSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.sql.Author> authorJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<ru.otus.homework.domain.sql.Author>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}