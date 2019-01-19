package com.uoa.di.csr.api.service;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.uoa.di.csr.api.converter.*;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
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

@Service
public class SpecificServiceRequestResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SpecificServiceRequestResolver.class);

    private static final int MILLISECONDS_TO_WAIT = 5;

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
            LOG.info("Starting parsing...");
            switch (csvFileName) {
                case ABANDONED_VEHICLES:
                    List<AbandonedVehicleCsv> abandonedVehicleCsvList = transformServiceRequests(csvFileName, AbandonedVehicleCsv.class).parse();
                    abandonedVehicleCsvList
                            .stream()
                            .map(abandonedVehicleConverter)
                            .forEach(this::saveAndWait);
                    break;
                case GARBAGE_CARTS:
                    List<GarbageCartCsv> garbageCartCsvList = transformServiceRequests(csvFileName, GarbageCartCsv.class).parse();
                    garbageCartCsvList
                            .stream()
                            .map(garbageCartConverter)
                            .forEach(this::saveAndWait);
                    break;
                case RODENT_BAITING:
                    List<RodentBaitingCsv> rodentBaitingCsvList = transformServiceRequests(csvFileName, RodentBaitingCsv.class).parse();
                    rodentBaitingCsvList
                            .stream()
                            .map(rodentBaitingConverter)
                            .forEach(this::saveAndWait);
                    break;
                case POT_HOLES:
                    List<PotHoleCsv> potHoleCsvList = transformServiceRequests(csvFileName, PotHoleCsv.class).parse();
                    potHoleCsvList
                            .stream()
                            .map(potHolesConverter)
                            .forEach(this::saveAndWait);
                    break;
                case GRAFFITI_REMOVAL:
                    List<GraffitiRemovalCsv> graffitiRemovalCsvList = transformServiceRequests(csvFileName, GraffitiRemovalCsv.class).parse();
                    graffitiRemovalCsvList
                            .stream()
                            .map(graffitiRemovalConverter)
                            .forEach(this::saveAndWait);
                    break;
                case TREE_DEBRIS:
                    List<TreeDebrisCsv> treeDebrisCsvList = transformServiceRequests(csvFileName, TreeDebrisCsv.class).parse();
                    treeDebrisCsvList
                            .stream()
                            .map(treeDebrisConverter)
                            .forEach(this::saveAndWait);
                    break;
                case TREE_TRIMS:
                    List<TreeTrimsCsv> treeTrimsCsvList = transformServiceRequests(csvFileName, TreeTrimsCsv.class).parse();
                    treeTrimsCsvList
                            .stream()
                            .map(treeTrimsConverter)
                            .forEach(this::saveAndWait);
                    break;
                case SANITATION_CODE:
                    List<SanitationCodeCsv> sanitationCodeCsvList = transformServiceRequests(csvFileName, SanitationCodeCsv.class).parse();
                    sanitationCodeCsvList
                            .stream()
                            .map(sanitationCodeConverter)
                            .forEach(this::saveAndWait);
                    break;
                default: //alley-lights-out, street-lights-all-out, street-lights-one-out
                    List<ServiceRequestCsv> serviceRequestCsvList = transformServiceRequests(csvFileName, ServiceRequestCsv.class).parse();
                    serviceRequestCsvList
                            .stream()
                            .map(serviceRequestConverter)
                            .forEach(this::saveAndWait);
            }
            LOG.info("Successfully saved Service Requests");
        });
        return ResponseEntity.ok().build();
    }

    private void saveAndWait(ServiceRequest sR) {
        try {
            serviceRequestService.saveServiceRequest(sR);
            Thread.sleep(MILLISECONDS_TO_WAIT);
        } catch (Exception ex) {
            LOG.error("Failed to save Service Request with SrNumber: {}", sR.getSrNumber());
        }
    }


    @SuppressWarnings("unchecked")
    private CsvToBean transformServiceRequests(String csvFileName, Class<? extends ServiceRequestCsv> csvClass) {
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
