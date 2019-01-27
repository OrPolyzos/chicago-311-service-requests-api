package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.model.response.CitizenPerTotalUpvotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


public class CitizenCustomRepositoryImpl implements CitizenCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CitizenCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CitizenPerTotalUpvotes> getMostActiveCitizens(Integer limit) {
        ProjectionOperation projectionOperation = project()
                .and("citizenId").as("id")
                .and("upvotedSrIds").size().as("totalUpvotes");
        return getMostActiveCitizensWithTotalUpvotes(projectionOperation, limit);
    }

    @Override
    public List<CitizenPerTotalUpvotes> getMostActiveCitizensRegardingWards(Integer limit) {
        ProjectionOperation projectionOperation = project()
                .and("citizenId").as("id")
                .and("upvotedWards").size().as("totalUpvotes");
        return getMostActiveCitizensWithTotalUpvotes(projectionOperation, limit);
    }


    private List<CitizenPerTotalUpvotes> getMostActiveCitizensWithTotalUpvotes(ProjectionOperation projectionOperation, Integer limit) {
        SortOperation sortOperation = sort(DESC, "totalUpvotes");
        Aggregation aggregation = newAggregation(
                projectionOperation,
                sortOperation,
                limit(limit));
        return mongoTemplate.aggregate(aggregation, Citizen.class, CitizenPerTotalUpvotes.class).getMappedResults();
    }
}
