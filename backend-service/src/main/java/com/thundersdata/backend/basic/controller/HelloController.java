package com.thundersdata.backend.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.thundersdata.backend.basic.service.GreeterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


/**
 * raw调用示例接口
 * @author wanghaibo
 */
@Api(tags = "示例接口")
@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private GreeterService greeterService;


    /**
     * raw传参的demo方法
     * 此方法的调用方法为ajax调用,header的accessToken校验之后会把userId,和loginType写入requestQueryParams,
     *  在形参中按照demo方法写上参数springboot会自动注入参数
     * jsonObject为raw方式传入json格式的参数
     * @param jsonObject
     * @param userId
     * @param loginType
     * @return
     */
    @ApiOperation(value = "用户请求示例方法", notes="userId,loginType为网关注入,所以不需要体现在文档上")
    @PostMapping("demo")
    public String demo(@RequestBody JSONObject jsonObject,@ApiIgnore() Integer userId,@ApiIgnore() Integer loginType) {

        String message = jsonObject.getString("message");

        return "Hello:" + greeterService.sayHello(message)+ "; userId:"+userId + "; loginType:"+loginType;
    }
}
