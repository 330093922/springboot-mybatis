package com.thundersdata.backend.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrc
 * @Classname QueryWeatherController
 * @Description TODO
 * @Date 2020/2/18 18:46
 */
@Api(tags = "天气查询接口")
@RestController
@RequestMapping("QueryWeather")
public class QueryWeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "天气查询接口", notes = "返回最近7天天气预报")
    @GetMapping
    public String QueryWeather() {
        String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + "淄博";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);

        if (200 == responseEntity.getStatusCodeValue()) {
            return responseEntity.getBody();
        } else {
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }
}
