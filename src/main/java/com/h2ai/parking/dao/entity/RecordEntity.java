package com.h2ai.parking.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordEntity {
    @JsonProperty(value = "fields")
    private fieldsEntity fields;

    public fieldsEntity getFields() {
        return fields;
    }

    public void setFields(fieldsEntity fields) {
        this.fields = fields;
    }
}
