package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionHelper {

    @Autowired
    private CitizenRepository citizenRepository;

    public Citizen getSessionCitizen() {
        return citizenRepository.findById("5c4db8d0efb664404040d4bf").get();
    }
}
