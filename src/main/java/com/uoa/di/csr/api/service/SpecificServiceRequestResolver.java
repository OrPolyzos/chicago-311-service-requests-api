package com.uoa.di.csr.api.service;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.uoa.di.csr.api.converter.*;
import com.uoa.di.csr.api.model.csv.*;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SpecificServiceRequestResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SpecificServiceRequestResolver.class);

    private static final String ABANDONED_VEHICLES = "abandoned-vehicles";
    private static final String GARBAGE_CARTS = "garbage-carts";
    private static final String RODENT_BAITING = "rodent-baiting";
    private static final String POT_HOLES = "pot-holes";
    private static final String GRAFFITI_REMOVAL = "graffiti-removal";
    private static final String TREE_DEBRIS = "tree-debris";
    private static final String TREE_TRIMS = "tree-trims";
    private static final String SANITATION_CODE = "sanitation-code";


    private static String CSV_FILE_EXTENSION = ".csv";

    @Value("${csr.csv.files.base.folder}")
    private String csvBaseFolder;

    private final ServiceRequestService serviceRequestService;
    private final ServiceRequestConverter serviceRequestConverter;
    private final TreeTrimsConverter treeTrimsConverter;

    private final AbandonedVehicleConverter abandonedVehicleConverter;
    private final GarbageCartConverter garbageCartConverter;
    private final PotHolesConverter potHolesConverter;
    private final RodentBaitingConverter rodentBaitingConverter;
    private final GraffitiRemovalConverter graffitiRemovalConverter;
    private final TreeDebrisConverter treeDebrisConverter;
    private final SanitationCodeConverter sanitationCodeConverter;

    @Autowired
    public SpecificServiceRequestResolver(ServiceRequestService serviceRequestService,
                                          ServiceRequestConverter serviceRequestConverter,
                                          TreeTrimsConverter treeTrimsConverter,
                                          AbandonedVehicleConverter abandonedVehicleConverter,
                                          GarbageCartConverter garbageCartConverter,
                                          PotHolesConverter potHolesConverter,
                                          RodentBaitingConverter rodentBaitingConverter,
                                          GraffitiRemovalConverter graffitiRemovalConverter,
                                          TreeDebrisConverter treeDebrisConverter,
                                          SanitationCodeConverter sanitationCodeConverter
    ) {
        this.serviceRequestService = serviceRequestService;
        this.serviceRequestConverter = serviceRequestConverter;
        this.treeTrimsConverter = treeTrimsConverter;
        this.garbageCartConverter = garbageCartConverter;
        this.abandonedVehicleConverter = abandonedVehicleConverter;
        this.potHolesConverter = potHolesConverter;
        this.rodentBaitingConverter = rodentBaitingConverter;
        this.graffitiRemovalConverter = graffitiRemovalConverter;
        this.treeDebrisConverter = treeDebrisConverter;
        this.sanitationCodeConverter = sanitationCodeConverter;
    }

    @SuppressWarnings("unchecked")
    public synchronized ResponseEntity<String> parseAndLoadFromCsv(String csvFileName) {
        CompletableFuture.runAsync(() -> {
            if (csvFileName.contains(ABANDONED_VEHICLES)) {
                List<AbandonedVehicleCsv> abandonedVehicleCsvList = transformServiceRequests(csvFileName, AbandonedVehicleCsv.class).parse();
                serviceRequestService.saveServiceRequests(abandonedVehicleCsvList.stream().map(abandonedVehicleConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(GARBAGE_CARTS)) {
                List<GarbageCartCsv> garbageCartCsvList = transformServiceRequests(csvFileName, GarbageCartCsv.class).parse();
                serviceRequestService.saveServiceRequests(garbageCartCsvList.stream().map(garbageCartConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(RODENT_BAITING)) {
                List<RodentBaitingCsv> rodentBaitingCsvList = transformServiceRequests(csvFileName, RodentBaitingCsv.class).parse();
                serviceRequestService.saveServiceRequests(rodentBaitingCsvList.stream().map(rodentBaitingConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(POT_HOLES)) {
                List<PotHoleCsv> potHoleCsvList = transformServiceRequests(csvFileName, PotHoleCsv.class).parse();
                serviceRequestService.saveServiceRequests(potHoleCsvList.stream().map(potHolesConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(GRAFFITI_REMOVAL)) {
                List<GraffitiRemovalCsv> graffitiRemovalCsvList = transformServiceRequests(csvFileName, GraffitiRemovalCsv.class).parse();
                serviceRequestService.saveServiceRequests(graffitiRemovalCsvList.stream().map(graffitiRemovalConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(TREE_DEBRIS)) {
                List<TreeDebrisCsv> treeDebrisCsvList = transformServiceRequests(csvFileName, TreeDebrisCsv.class).parse();
                serviceRequestService.saveServiceRequests(treeDebrisCsvList.stream().map(treeDebrisConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(TREE_TRIMS)) {
                List<TreeTrimsCsv> treeTrimsCsvList = transformServiceRequests(csvFileName, TreeTrimsCsv.class).parse();
                serviceRequestService.saveServiceRequests(treeTrimsCsvList.stream().map(treeTrimsConverter).collect(Collectors.toList()));
            } else if (csvFileName.contains(SANITATION_CODE)) {
                List<SanitationCodeCsv> sanitationCodeCsvList = transformServiceRequests(csvFileName, SanitationCodeCsv.class).parse();
                serviceRequestService.saveServiceRequests(sanitationCodeCsvList.stream().map(sanitationCodeConverter).collect(Collectors.toList()));
            } else {
                //alley-lights-out, street-lights-all-out, street-lights-one-out
                List<ServiceRequestCsv> serviceRequestCsvList = transformServiceRequests(csvFileName, ServiceRequestCsv.class).parse();
                serviceRequestService.saveServiceRequests(serviceRequestCsvList.stream().map(serviceRequestConverter).collect(Collectors.toList()));
            }
            LOG.info("Successfully saved Service Requests");
        });
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("unchecked")
    private CsvToBean transformServiceRequests(String csvFileName, Class<? extends ServiceRequestCsv> csvClass) {
        LOG.info("Starting parsing...");
        Path path = new File(csvBaseFolder.concat(csvFileName).concat(CSV_FILE_EXTENSION)).toPath();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HeaderColumnNameMappingStrategy<ServiceRequestCsv> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(csvClass);
        return new CsvToBeanBuilder(Objects.requireNonNull(bufferedReader))
                .withType(csvClass)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }
}
