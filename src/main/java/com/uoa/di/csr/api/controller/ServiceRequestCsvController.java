package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.service.SpecificServiceRequestResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("csv")
public class ServiceRequestCsvController {

    private final SpecificServiceRequestResolver serviceRequestResolver;

    @Autowired
    public ServiceRequestCsvController(SpecificServiceRequestResolver serviceRequestResolver) {
        this.serviceRequestResolver = serviceRequestResolver;
    }

    @GetMapping("/load/{csvFileName}")
    public ResponseEntity loadServiceRequestsFromCsv(@PathVariable("csvFileName") String csvFileName) {
        return serviceRequestResolver.parseAndLoadFromCsv(csvFileName);
    }

}
