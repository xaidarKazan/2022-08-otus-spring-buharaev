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
import ru.otus.homework.domain.nosql.Genre;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class GenreToSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step genreToSqlStep(ItemStreamReader<Genre> genreMongoItemReader, ItemWriter<ru.otus.homework.domain.sql.Genre> genreJpaItemWriter) {
        return stepBuilderFactory.get("genreToSqlStep")
                .<Genre, ru.otus.homework.domain.sql.Genre>chunk(2)
                .reader(genreMongoItemReader)
                .processor(genreToSqlProcessor())
                .writer(genreJpaItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Genre> genreMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Genre>()
                .name("genreMongoItemReader")
                .collection("Genre")
                .template(mongoTemplate)
                .jsonQuery("{ }")
                .sorts(new HashMap<>())
                .targetType(Genre.class)
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Genre, ru.otus.homework.domain.sql.Genre> genreToSqlProcessor() {
        return genre -> genre.toSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.sql.Genre> genreJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<ru.otus.homework.domain.sql.Genre>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}