package org.example.repository.entity;

import lombok.Data;

@Data
public class WeatherBean {

    private String name;
    private TempBean main;
    private WindBean wind;

}
