package com.eform.graphqlpractice.model;


public class VehicleWrapper {
    private String type;
    private String modelCode;
    private String brandName;

    public VehicleWrapper(String type, String modelCode, String brandName) {
        this.type = type;
        this.modelCode = modelCode;
        this.brandName = brandName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
