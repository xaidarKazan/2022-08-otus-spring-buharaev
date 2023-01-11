package ru.otus.homeWork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.DbSequence;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService{

    private final MongoOperations mongoOperations;

    public String getNextSequence(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("sequenceNumber", 1l);
        DbSequence dbSequence = mongoOperations.findAndModify(query, update,
                                            FindAndModifyOptions.options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(dbSequence) ? String.valueOf(dbSequence.getSequenceNumber()) : "1";
    }
}