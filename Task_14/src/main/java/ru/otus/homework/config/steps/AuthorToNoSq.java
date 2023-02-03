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
import ru.otus.homework.domain.sql.Author;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class AuthorToNoSq {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step authorToNoSqlStep(ItemStreamReader<Author> authorItemStreamReader, ItemWriter<ru.otus.homework.domain.nosql.Author> authorMongoItemWriter) {
        return stepBuilderFactory.get("authorToNoSqlStep")
                .<Author, ru.otus.homework.domain.nosql.Author>chunk(2)
                .reader(authorItemStreamReader)
                .processor(authorToNoSqlProcessor())
                .writer(authorMongoItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Author> authorJpaItemReader(EntityManagerFactory entityManagerFactory) {
        return  new JpaCursorItemReaderBuilder<Author>()
                .name("authorJpaItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select a from Author a ")
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Author, ru.otus.homework.domain.nosql.Author> authorToNoSqlProcessor() {
        return author -> author.toNoSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.nosql.Author> authorMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<ru.otus.homework.domain.nosql.Author>()
                .collection("Author")
                .template(mongoTemplate)
                .build();
    }
}