package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import org.springframework.stereotype.Component;

@Component
public class SessionHelper {

    public Citizen getSessionCitizen() {
        Citizen citizen = new Citizen();
        citizen.setAddress("SomeAddress");
        citizen.setFirstName("SomeFName");
        citizen.setLastName("SomeLName");
        return citizen;
    }
}
