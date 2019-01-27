package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.model.response.CitizenPerTotalUpvotes;

import java.util.List;

public interface CitizenCustomRepository {

    List<CitizenPerTotalUpvotes> getMostActiveCitizens(Integer limit);

    List<CitizenPerTotalUpvotes> getMostActiveCitizensRegardingWards(Integer limit);

}