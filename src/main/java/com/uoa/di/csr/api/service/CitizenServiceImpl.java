package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.model.response.CitizenPerTotalUpvotes;
import com.uoa.di.csr.api.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public void saveCitizen(Citizen citizen) {
        citizenRepository.save(citizen);
    }

    @Override
    public void saveCitizens(List<Citizen> citizens) {
        citizenRepository.saveAll(citizens);
    }

    @Override
    public List<CitizenPerTotalUpvotes> getMostActiveCitizens(Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 50;
        return citizenRepository.getMostActiveCitizens(limit);
    }

    @Override
    public List<CitizenPerTotalUpvotes> getMostActiveCitizensRegardingWards(Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 50;
        return citizenRepository.getMostActiveCitizensRegardingWards(limit);
    }

    @Override
    public List<Citizen> getCitizenByFullName(String firstName, String lastName) {
        return citizenRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }
}
