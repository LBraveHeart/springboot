package com.example.springboot.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @date 2019年5月7日16:15:35
 * @author liuyy
 * 测试类
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(){

        return "helloWorld";
    }

    @RequestMapping("hello1")
    public String hello1(){

        return "helloWorld1";
    }
    @RequestMapping("testShow")
    public String testShow(){

        return "testShow";
    }
    @RequestMapping("testShowData")
    @ResponseBody
    public String testShowData() throws IOException {

        File file = new File("D:\\source\\汇智明德\\20190507\\2019-14砍石自然拼读（中）学习软件V1.0\\1-5.json");
        String s = FileUtils.readFileToString(file, "utf-8");
        return s;
    }

}
