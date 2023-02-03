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
import ru.otus.homework.domain.sql.Book;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class BookToNoSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step bookToNoSqlStep(ItemStreamReader<Book> bookItemStreamReader, ItemWriter<ru.otus.homework.domain.nosql.Book> bookMongoItemWriter) {
        return stepBuilderFactory.get("bookToNoSqlStep")
                .<Book, ru.otus.homework.domain.nosql.Book>chunk(2)
                .reader(bookItemStreamReader)
                .processor(bookToNoSqlProcessor())
                .writer(bookMongoItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Book> bookJpaItemReader(EntityManagerFactory entityManagerFactory) {
        return  new JpaCursorItemReaderBuilder<Book>()
                .name("bookJpaItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select b from Book b ")
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Book, ru.otus.homework.domain.nosql.Book> bookToNoSqlProcessor() {
        return book -> book.toNoSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.nosql.Book> bookMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<ru.otus.homework.domain.nosql.Book>()
                .collection("Book")
                .template(mongoTemplate)
                .build();
    }
}