package com.example.keycloakauthbackend.dashboard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class DashboardViewDTO {
    String id;
    String name;

    @JsonCreator
    public DashboardViewDTO(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
