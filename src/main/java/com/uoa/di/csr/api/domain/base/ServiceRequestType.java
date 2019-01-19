package com.uoa.di.csr.api.domain.base;

public enum ServiceRequestType {

    ABANDONED_VEHICLE_COMPLAINT("Abandoned Vehicle Complaint"),
    ALLEY_LIGHT_OUT("Alley Light Out"),
    STREET_LIGHTS_ALL_OUT("Street Lights - All/Out"),
    STREET_LIGHT_ONE_OUT("Street Light - 1/Out"),
    STREET_LIGHT_OUT("Street Light Out"),
    GARBAGE_CART("Garbage Cart Black Maintenance/Replacement"),
    RODENT_BAITING("Rodent Baiting/Rat Complaint"),
    POT_HOLE("Pot Hole in Street"),
    GRAFFITI_REMOVAL("Graffiti Removal"),
    TREE_DEBRIS("Tree Debris"),
    TREE_TRIMS("Tree Trim"),
    SANITATION_CODE_COMPLAINT("Sanitation Code Violation"),
    VACANT_AND_ABANDONED_BUILDING("Vacant/Abandoned Building"),
    DEFAULT_SERVICE_REQUEST_TYPE("N/A");
    private String value;

    ServiceRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ServiceRequestType reverseValue(String actualValue) {
        if (actualValue.equals(ABANDONED_VEHICLE_COMPLAINT.getValue())) {
            return ABANDONED_VEHICLE_COMPLAINT;
        } else if (actualValue.equals(ALLEY_LIGHT_OUT.getValue())) {
            return ALLEY_LIGHT_OUT;
        } else if (actualValue.equals(STREET_LIGHTS_ALL_OUT.getValue())) {
            return STREET_LIGHTS_ALL_OUT;
        } else if (actualValue.equals(STREET_LIGHT_ONE_OUT.getValue()) || actualValue.equals(STREET_LIGHT_OUT.getValue())) {
            return STREET_LIGHT_ONE_OUT;
        } else if (actualValue.equals(GARBAGE_CART.getValue())) {
            return GARBAGE_CART;
        } else if (actualValue.equals(RODENT_BAITING.getValue())) {
            return RODENT_BAITING;
        } else if (actualValue.equals(POT_HOLE.getValue())) {
            return POT_HOLE;
        } else if (actualValue.equals(GRAFFITI_REMOVAL.getValue())) {
            return GRAFFITI_REMOVAL;
        } else if (actualValue.equals(TREE_DEBRIS.getValue())) {
            return TREE_DEBRIS;
        } else if (actualValue.equals(TREE_TRIMS.getValue())) {
            return TREE_TRIMS;
        } else if (actualValue.equals(SANITATION_CODE_COMPLAINT.getValue())) {
            return SANITATION_CODE_COMPLAINT;
        } else if (actualValue.equals(VACANT_AND_ABANDONED_BUILDING.getValue())) {
            return VACANT_AND_ABANDONED_BUILDING;
        } else {
            return DEFAULT_SERVICE_REQUEST_TYPE;
        }
    }
}
