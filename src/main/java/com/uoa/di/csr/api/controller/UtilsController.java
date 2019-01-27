package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.model.request.PopulateCitizensRequest;
import com.uoa.di.csr.api.service.CitizenFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("utils")
public class UtilsController {

    private final CitizenFaker citizenFaker;

    @Autowired
    public UtilsController(CitizenFaker citizenFaker) {
        this.citizenFaker = citizenFaker;
    }

    @PostMapping("populate-citizens")
    public void populateCitizens(@RequestBody PopulateCitizensRequest populateCitizensRequest) {
        citizenFaker.populateCitizens(populateCitizensRequest.getMaxUpvotes());
    }
}
