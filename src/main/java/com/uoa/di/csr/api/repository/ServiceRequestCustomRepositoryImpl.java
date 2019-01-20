package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class ServiceRequestCustomRepositoryImpl implements ServiceRequestCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ServiceRequestCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(where("creationDateTime").gt(startDateTime).andOperator(where("creationDateTime").lt(endDateTime)));
        GroupOperation groupOperation = group("srType").count().as("totalServiceRequests");
        ProjectionOperation projectionOperation = project("totalServiceRequests").and("srType").previousOperation();

        Aggregation agg = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sort(Sort.Direction.DESC, "totalServiceRequests"));
        AggregationResults<TotalServiceRequestsPerType> groupResults = mongoTemplate.aggregate(agg, ServiceRequest.class, TotalServiceRequestsPerType.class);
        return groupResults.getMappedResults();
    }


}
