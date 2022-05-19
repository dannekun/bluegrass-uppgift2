package com.example.bluegrass.bluegrass.uppgift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2022-05-19
 * Time: 16:58
 * Project: bluegrass-uppgift
 * Copyright: MIT
 */

@Service
public class WeatherService {

    @Autowired
    WeatherDataRepositry weatherDataRepositry;

    List<WeatherData> list = new ArrayList<>();





}
