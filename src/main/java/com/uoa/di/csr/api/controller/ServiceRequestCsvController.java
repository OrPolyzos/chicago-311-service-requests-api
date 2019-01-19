package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.service.SpecificServiceRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRequestCsvController {

    @Autowired
    private SpecificServiceRequestResolver serviceRequestResolver;

    @GetMapping("load-service-requests/{csvFileName}")
    public synchronized ResponseEntity loadServiceRequests(@PathVariable("csvFileName") String csvFileName) {
        return serviceRequestResolver.parseAndLoadFromCsv(csvFileName);
    }
}
