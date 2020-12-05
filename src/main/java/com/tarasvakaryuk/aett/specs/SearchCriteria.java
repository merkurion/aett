package com.tarasvakaryuk.aett.specs;

import lombok.Data;

@Data
public class SearchCriteria {

    private final String key;
    private final Object value;
    private final SearchOperation operation;

}
