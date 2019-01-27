package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.model.response.CitizenPerTotalUpvotes;
import com.uoa.di.csr.api.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("citizens")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping("/most-active-citizens")
    public List<CitizenPerTotalUpvotes> getMostActiveCitizens(@RequestParam(name = "limit", required = false) Integer limit) {
        return citizenService.getMostActiveCitizens(limit);
    }

    @GetMapping("/most-active-citizens-regarding-wards")
    public List<CitizenPerTotalUpvotes> getMostActiveCitizensRegardingWards(@RequestParam(name = "limit", required = false) Integer limit) {
        return citizenService.getMostActiveCitizensRegardingWards(limit);
    }

    @GetMapping("/upvoted-wards-by-full-name")
    public List<Citizen> getTotalUpvotedWardsByName(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) {
        return citizenService.getCitizenByFullName(firstName, lastName);
    }

}
