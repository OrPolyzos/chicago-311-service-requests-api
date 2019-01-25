package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.converter.MongoDateConverter;
import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;


public class ServiceRequestCustomRepositoryImpl implements ServiceRequestCustomRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoDateConverter mongoDateConverter;

    @Autowired
    public ServiceRequestCustomRepositoryImpl(MongoTemplate mongoTemplate, MongoDateConverter mongoDateConverter) {
        this.mongoTemplate = mongoTemplate;
        this.mongoDateConverter = mongoDateConverter;
    }


    @Override
    public List<TotalServiceRequestsPerSrType> getTotalServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime));
        GroupOperation groupOperation = group("srType").count().as("totalServiceRequests");
        ProjectionOperation projectionOperation = project("totalServiceRequests").and("srType").previousOperation();

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                Aggregation.sort(DESC, "totalServiceRequests"));
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TotalServiceRequestsPerSrType.class).getMappedResults();
    }

    @Override
    public List<TotalServiceRequestsPerCreationDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(where("srType").is(serviceRequestType).andOperator(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime)));
        ProjectionOperation projectionOperation = project()
                .andExpression("year(creationDateTime)").as("year")
                .andExpression("month(creationDateTime)").as("month")
                .andExpression("dayOfMonth(creationDateTime)").as("day");
        GroupOperation groupOperation = group(fields().and("year").and("month").and("day"))
                .count().as("totalServiceRequests");

        Aggregation aggregation = newAggregation(
                matchOperation,
                projectionOperation,
                groupOperation);
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TotalServiceRequestsPerCreationDay.class).getMappedResults();
    }

    @Override
    public List<ZipCodesPerSrType> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDay) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(creationDay.atStartOfDay(), creationDay.plusDays(1).atStartOfDay()));
        GroupOperation groupOperation = group("srType").addToSet("zipCode").as("zipCodes");
        ProjectionOperation projectionOperation = project().and("_id").as("srType").and("zipCodes").size().as("zipCodes");
        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sort(DESC, "zipCodes"),
                limit(3));
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, ZipCodesPerSrType.class).getMappedResults();
    }

    @Override
    public List<TotalServiceRequestsPerWard> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType) {
        MatchOperation matchOperation = match(where("srType").is(serviceRequestType));
        GroupOperation groupOperation = group("ward").count().as("totalServiceRequests");
        ProjectionOperation projectionOperation = project().and("_id").as("ward").and("totalServiceRequests").as("totalServiceRequests");
        SortOperation sortOperation = sort(ASC, "totalServiceRequests");
        LimitOperation limitOperation = limit(3);
        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation,
                limitOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TotalServiceRequestsPerWard.class).getMappedResults();
    }

    @Override
    public List<AvgCompletionTimePerSrType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime));
        ProjectionOperation projectionOperation = project()
                .andInclude("srType")
                .andExpression("completionDateTime - creationDateTime").as("completionTime");
        GroupOperation groupOperation = group("srType").avg("completionTime").as("averageCompletionTimeInMillis");
        ProjectionOperation namingProjectionOperation = project().and("_id").as("srType").andInclude("averageCompletionTimeInMillis");
        Aggregation aggregation = newAggregation(
                matchOperation,
                projectionOperation,
                groupOperation,
                namingProjectionOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, AvgCompletionTimePerSrType.class).getMappedResults();
    }

    @Override
    public List<TotalServiceRequestsPerSrType> getMostCommonServiceRequestTypeInBoundingBox(double x1, double x2, double y1, double y2) {
        MatchOperation matchOperation = match(where("geoLocation").within(new GeoJsonPolygon(
                new GeoJsonPoint(x1, y1),
                new GeoJsonPoint(x1, y2),
                new GeoJsonPoint(x2, y2),
                new GeoJsonPoint(x2, y1),
                new GeoJsonPoint(x1, y1))));
        GroupOperation groupOperation = group("srType").count().as("totalServiceRequests");
        ProjectionOperation projectionOperation = project().and("_id").as("srType").andInclude("totalServiceRequests");
        SortOperation sortOperation = sort(DESC, "totalServiceRequests");
        LimitOperation limitOperation = limit(1);

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation,
                limitOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TotalServiceRequestsPerSrType.class).getMappedResults();
    }

    @Override
    public List<ServiceRequest> getFiftyMostUpvotedServiceRequestsByCreationDate(LocalDate creationDate) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(creationDate.atStartOfDay(), creationDate.plusDays(1).atStartOfDay()));
        SortOperation sortOperation = sort(DESC, "upvotersIds");
        Aggregation aggregation = newAggregation(
                matchOperation,
                sortOperation,
                limit(50));
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, ServiceRequest.class).getMappedResults();
    }

    @Override
    public List<CitizenIdPerTotalUpvotes> getFiftyMostActiveCitizens() {
        ProjectionOperation projectionOperation = project().andInclude("citizenId").and("serviceRequestsIds").size().as("totalUpvotes");
        SortOperation sortOperation = sort(DESC, "totalUpvotes");
        Aggregation aggregation = newAggregation(
                projectionOperation,
                sortOperation,
                limit(50));
        return mongoTemplate.aggregate(aggregation, Citizen.class, CitizenIdPerTotalUpvotes.class).getMappedResults();
    }


    private Criteria getLocalDateTimeInRangeCriteria(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return where("creationDateTime")
                .gte(mongoDateConverter.localDateTimeToDate(startDateTime))
                .lt(mongoDateConverter.localDateTimeToDate(endDateTime));
    }
}
