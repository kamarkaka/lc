package com.kamarkaka.cloudkitchen;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonOrder {
    @JsonProperty
    String id;

    @JsonProperty
    String name;

    @JsonProperty
    String temp;

    @JsonProperty
    float shelfLife;

    @JsonProperty
    float decayRate;
}
