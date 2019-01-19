package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsv;
import lombok.Getter;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;

@Getter
@Setter
public class SanitationCodeCsv extends ServiceRequestCsv {

    @CsvBindByName(column = "What is the Nature of this Code Violation?")
    private String natureOfThisCodeViolation;

    public String getNatureOfThisCodeViolation() {
        return natureOfThisCodeViolation;
    }

    public void setNatureOfThisCodeViolation(String natureOfThisCodeViolation) {
        this.natureOfThisCodeViolation = natureOfThisCodeViolation;
    }
}
