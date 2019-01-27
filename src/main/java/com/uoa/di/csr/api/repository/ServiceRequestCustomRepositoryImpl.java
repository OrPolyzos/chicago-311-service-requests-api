package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.converter.MongoDateConverter;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
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
    public void saveAllServiceRequests(List<ServiceRequest> serviceRequests) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, ServiceRequest.class);
        serviceRequests.forEach(bulkOperations::insert);
        bulkOperations.execute();
    }

    @Override
    public List<TypePerTotalRequests> getTotalRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime));
        GroupOperation groupOperation = group("srType").count().as("totalRequests");
        ProjectionOperation projectionOperation = project()
                .andInclude("totalRequests")
                .and("_id").as("type");
        SortOperation sortOperation = sort(DESC, "totalRequests");

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation);
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TypePerTotalRequests.class).getMappedResults();
    }

    @Override
    public List<CreationDayPerTotalRequests> getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(where("srType").is(serviceRequestType).andOperator(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime)));
        ProjectionOperation projectionOperation = project()
                .andExpression("year(creationDateTime)").as("year")
                .andExpression("month(creationDateTime)").as("month")
                .andExpression("dayOfMonth(creationDateTime)").as("day");
        GroupOperation groupOperation = group(fields().and("year").and("month").and("day"))
                .count().as("totalRequests");

        Aggregation aggregation = newAggregation(
                matchOperation,
                projectionOperation,
                groupOperation);
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, CreationDayPerTotalRequests.class).getMappedResults();
    }

    @Override
    public List<TypePerZipCodes> getMostCommonRequestTypesPerZipCodesByCreationDate(LocalDate creationDay, Integer limit) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(creationDay.atStartOfDay(), creationDay.plusDays(1).atStartOfDay()));
        GroupOperation groupOperation = group("srType").addToSet("zipCode").as("zipCodes");
        ProjectionOperation projectionOperation = project()
                .and("_id").as("type")
                .and("zipCodes").size().as("zipCodes");
        SortOperation sortOperation = sort(DESC, "zipCodes");
        LimitOperation limitOperation = limit(limit);

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation,
                limitOperation);
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TypePerZipCodes.class).getMappedResults();
    }

    @Override
    public List<WardPerTotalRequests> getLeastCommonWardsByType(String serviceRequestType, Integer limit) {
        MatchOperation matchOperation = match(where("srType").is(serviceRequestType));
        GroupOperation groupOperation = group("ward").count().as("totalServiceRequests");
        ProjectionOperation projectionOperation = project()
                .and("_id").as("ward")
                .and("totalServiceRequests").as("totalRequests");
        SortOperation sortOperation = sort(ASC, "totalRequests");
        LimitOperation limitOperation = limit(limit);

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation,
                limitOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, WardPerTotalRequests.class).getMappedResults();
    }

    @Override
    public List<AvgTimePerType> getAvgTimePerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(startDateTime, endDateTime));
        ProjectionOperation projectionOperation = project()
                .andInclude("srType")
                .andExpression("completionDateTime - creationDateTime").as("completionTime");
        GroupOperation groupOperation = group("srType").avg("completionTime").as("averageCompletionTimeInMillis");
        ProjectionOperation namingProjectionOperation = project()
                .and("_id").as("type")
                .andInclude("averageCompletionTimeInMillis");

        Aggregation aggregation = newAggregation(
                matchOperation,
                projectionOperation,
                groupOperation,
                namingProjectionOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, AvgTimePerType.class).getMappedResults();
    }

    @Override
    public List<TypePerTotalRequests> getMostCommonTypesInBoundingBox(double x1, double x2, double y1, double y2, Integer limit) {
        MatchOperation matchOperation = match(where("geoLocation").within(new GeoJsonPolygon(
                new GeoJsonPoint(x1, y1),
                new GeoJsonPoint(x1, y2),
                new GeoJsonPoint(x2, y2),
                new GeoJsonPoint(x2, y1),
                new GeoJsonPoint(x1, y1))));
        GroupOperation groupOperation = group("srType").count().as("totalRequests");
        ProjectionOperation projectionOperation = project()
                .and("_id").as("type")
                .andInclude("totalRequests");
        SortOperation sortOperation = sort(DESC, "totalRequests");
        LimitOperation limitOperation = limit(limit);

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation,
                sortOperation,
                limitOperation
        );
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, TypePerTotalRequests.class).getMappedResults();
    }

    @Override
    public List<RequestPerTotalUpvotes> getMostUpvotedRequestsByCreationDate(LocalDate creationDate, Integer limit) {
        MatchOperation matchOperation = match(getLocalDateTimeInRangeCriteria(creationDate.atStartOfDay(), creationDate.plusDays(1).atStartOfDay()));
        ProjectionOperation projectionOperation = project()
                .and("srId").as("id")
                .and("upvotersIds").size().as("totalUpvotes");
        SortOperation sortOperation = sort(DESC, "totalUpvotes");
        LimitOperation limitOperation = limit(limit);
        Aggregation aggregation = newAggregation(
                matchOperation,
                projectionOperation,
                sortOperation,
                limitOperation);
        return mongoTemplate.aggregate(aggregation, ServiceRequest.class, RequestPerTotalUpvotes.class).getMappedResults();
    }

    @Override
    public List<RequestPerSamePhoneNumbersUsed> getRequestsWithSamePhoneNumbersUsed() {
        ProjectionOperation initialProjection = project()
                .andInclude("srId")
                .and("upvotersIds").size().as("totalUpvotes")
                .and("upvotersTelephoneNumbers").size().as("totalPhoneNumbersUsed");
        ProjectionOperation expressionProjection = project()
                .and("srId").as("id")
                .andExpression("totalUpvotes - totalPhoneNumbersUsed").as("samePhoneNumbersUsed");
        MatchOperation matchOperation = match(where("samePhoneNumbersUsed").gt(0));

        Aggregation agg = newAggregation(
                initialProjection,
                expressionProjection,
                matchOperation
        );
        return mongoTemplate.aggregate(agg, ServiceRequest.class, RequestPerSamePhoneNumbersUsed.class).getMappedResults();
    }


    private Criteria getLocalDateTimeInRangeCriteria(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return where("creationDateTime")
                .gte(mongoDateConverter.localDateTimeToDate(startDateTime))
                .lt(mongoDateConverter.localDateTimeToDate(endDateTime));
    }
}
