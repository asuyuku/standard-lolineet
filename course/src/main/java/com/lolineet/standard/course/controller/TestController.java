package com.lolineet.standard.course.controller;

import com.lolineet.standard.starter.exception.LolineetException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")

public class TestController {
    @Value("${qqq:000}")
    private String qqq;
    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public Map<String,Object> test() {
        Map<String,Object> json = new HashMap<>();
        throw new LolineetException(500,"测试异常");
//        json.put("code",200);
//        json.put("msg","success");
//        json.put("data",qqq);
//        return json;
    }
}
