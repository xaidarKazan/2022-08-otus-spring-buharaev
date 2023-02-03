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
import ru.otus.homework.domain.sql.Genre;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class GenreToNoSql {

    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step genreToNoSqlStep(ItemStreamReader<Genre> genreJpaItemReader, ItemWriter<ru.otus.homework.domain.nosql.Genre> genreMongoItemWriter) {
        return stepBuilderFactory.get("genreToNoSqlStep")
                .<Genre, ru.otus.homework.domain.nosql.Genre>chunk(2)
                .reader(genreJpaItemReader)
                .processor(genreToNoSqlProcessor())
                .writer(genreMongoItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Genre> genreJpaItemReader(EntityManagerFactory entityManagerFactory) {
        return  new JpaCursorItemReaderBuilder<Genre>()
                .name("genreJpaItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select g from Genre g ")
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Genre, ru.otus.homework.domain.nosql.Genre> genreToNoSqlProcessor() {
        return genre -> genre.toNoSqlDomain();
    }

    @Bean
    @StepScope
    public ItemWriter<ru.otus.homework.domain.nosql.Genre> genreMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<ru.otus.homework.domain.nosql.Genre>()
                .collection("Genre")
                .template(mongoTemplate)
                .build();
    }
}