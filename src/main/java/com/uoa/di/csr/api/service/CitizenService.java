package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.model.response.CitizenPerTotalUpvotes;

import java.util.List;

public interface CitizenService {

    void saveCitizen(Citizen citizen);

    void saveCitizens(List<Citizen> citizens);

    List<CitizenPerTotalUpvotes> getMostActiveCitizens(Integer limit);

    List<CitizenPerTotalUpvotes> getMostActiveCitizensRegardingWards(Integer limit);

    List<Citizen> getCitizenByFullName(String firstName, String lastName);
}
