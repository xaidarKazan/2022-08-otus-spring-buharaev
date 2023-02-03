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
import ru.otus.homework.domain.nosql.Book;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class BookToSql {

    public final StepBuilderFactory stepBuilderFactory;

//    public final CommentSqlRepository repo;

    @Bean
    public Step bookToSqlStep(ItemStreamReader<Book> bookMongoItemReader, ItemWriter<ru.otus.homework.domain.sql.Book> bookJpaItemWriter) {
        return stepBuilderFactory.get("bookToSqlStep")
                .<Book, ru.otus.homework.domain.sql.Book>chunk(2)
                .reader(bookMongoItemReader)
                .processor(bookToSqlProcessor())
                .writer(bookJpaItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Book> bookMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Book>()
                .name("bookMongoItemReader")
                .collection("Book")
                .template(mongoTemplate)
                .jsonQuery("{ }")
                .sorts(new HashMap<>())
                .targetType(Book.class)
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Book, ru.otus.homework.domain.sql.Book> bookToSqlProcessor() {
//        return book -> {
//            ru.otus.homework.domain.sql.Book bookToSql = book.toSqlDomain();
//            bookToSql.setComments(repo.findByBook(bookToSql));
//            return bookToSql;
//        };
        return book -> book.toSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.sql.Book> bookJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<ru.otus.homework.domain.sql.Book>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}